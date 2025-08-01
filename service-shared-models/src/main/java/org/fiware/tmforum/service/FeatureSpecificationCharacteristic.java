package org.fiware.tmforum.service;

import lombok.Data;
import org.fiware.tmforum.common.domain.TimePeriod;
import org.fiware.tmforum.resource.CharacteristicValue;
import org.fiware.tmforum.resource.FeatureSpecificationCharacteristicRelationship;

import java.net.URI;
import java.util.List;


@Data
public class FeatureSpecificationCharacteristic {

    private String tmfId;
    private String description;
    private Boolean configurable;
    private Boolean extensible;
    private Boolean isUnique;
    private Integer maxCardinality;
    private Integer minCardinality;
    private String name;
    private String regex;
    private String valueType;
    private List<CharacteristicValue> featureSpecCharacteristicValue;
    private List<FeatureSpecificationCharacteristicRelationship> featureSpecCharRelationship;
    private TimePeriod validFor;
    private String atBaseType;
    private URI atSchemaLocation;
    private String atType;
    private String atValueSchemaLocation;
}
