package org.fiware.tmforum.common.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.wistefan.mapping.UnmappedProperty;
import io.github.wistefan.mapping.annotations.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Abstract super class for all entities to be created
 */
@EqualsAndHashCode
@RequiredArgsConstructor
public abstract class Entity {

	@JsonIgnore
	public static final String ADDITIONAL_PROPERTIES_KEY = "additionalProperties";

	/**
	 * When sub-classing, this defines the super-class
	 */
	@Getter(onMethod = @__({
			@AttributeGetter(value = AttributeType.PROPERTY, targetName = "atBaseType", embedProperty = true)}))
	@Setter(onMethod = @__({
			@AttributeSetter(value = AttributeType.PROPERTY, targetName = "atBaseType", fromProperties = true)}))
	String atBaseType;

	/**
	 * A URI to a JSON-Schema file that defines additional attributes and relationships
	 */
	@Getter(onMethod = @__({
			@AttributeGetter(value = AttributeType.PROPERTY, targetName = "atSchemaLocation", embedProperty = true)}))
	@Setter(onMethod = @__({
			@AttributeSetter(value = AttributeType.PROPERTY, targetName = "atSchemaLocation", fromProperties = true, targetClass = URI.class)}))
	URI atSchemaLocation;

	/**
	 * When sub-classing, this defines the sub-class entity name
	 */
	@Getter(onMethod = @__({
			@AttributeGetter(value = AttributeType.PROPERTY, targetName = "atType", embedProperty = true)}))
	@Setter(onMethod = @__({
			@AttributeSetter(value = AttributeType.PROPERTY, targetName = "atType", fromProperties = true)}))
	String atType;

	@JsonIgnore
	public String getEntityState() {
		return "default";
	}

	@JsonIgnore
	@Setter(onMethod = @__({@UnmappedPropertiesSetter}))
	@Getter(onMethod = @__({@UnmappedPropertiesGetter}))
	private List<UnmappedProperty> additionalProperties;

	public void addAdditionalProperties(String propertyKey, Object value) {
		UnmappedProperty ap = new UnmappedProperty(propertyKey, value);
		if (this.additionalProperties == null) {
			this.additionalProperties = new ArrayList<>();
		}
		this.additionalProperties.add(ap);
	}

	/**
	 * Support for Jackson JSON serialization when entity is nested in PROPERTY_LIST
	 * Converts additionalProperties to a Map for @JsonAnyGetter
	 * Excludes @baseType, @schemaLocation, and @type as they have explicit getters
	 */
	@JsonAnyGetter
	public Map<String, Object> getAdditionalPropertiesAsMap() {
		if (this.additionalProperties == null || this.additionalProperties.isEmpty()) {
			return new HashMap<>();
		}
		return this.additionalProperties.stream()
				.filter(prop -> !prop.getName().equals("atBaseType")
						&& !prop.getName().equals("atSchemaLocation")
						&& !prop.getName().equals("atType"))
				.collect(Collectors.toMap(
						UnmappedProperty::getName,
						UnmappedProperty::getValue,
						(existing, replacement) -> replacement
				));
	}

	/**
	 * Support for Jackson JSON deserialization when entity is nested in PROPERTY_LIST
	 * Converts Map entries to additionalProperties for @JsonAnySetter
	 */
	@JsonAnySetter
	public void setAdditionalPropertyFromJson(String key, Object value) {
		addAdditionalProperties(key, value);
	}

}
