package org.fiware.tmforum.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.fiware.tmforum.common.domain.Entity;
import org.fiware.tmforum.common.domain.TimePeriod;
import org.fiware.tmforum.service.CharacteristicValueSpecification;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductSpecificationCharacteristic extends Entity {

	private String tmfId;
	private Boolean configurable;
	private String description;
	private Boolean extensible;
	private Boolean isUnique;
	private Integer maxCardinality;
	private Integer minCardinality;
	private String name;
	private String regex;
	private String valueType;
	private List<ProductSpecificationCharacteristicRelationship> productSpecCharRelationship;
	private List<CharacteristicValueSpecification> productSpecCharacteristicValue;
	private TimePeriod validFor;
	private String atValueSchemaLocation;
}
