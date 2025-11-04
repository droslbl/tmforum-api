package org.fiware.tmforum.service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.fiware.tmforum.common.domain.Entity;
import org.fiware.tmforum.common.domain.TimePeriod;

@EqualsAndHashCode(callSuper = true)
@Data
public class CharacteristicValueSpecification extends Entity {

    private Boolean isDefault;
    private String rangeInterval;
    private String regex;
    private String unitOfMeasure;
    private Integer valueFrom;
    private Integer valueTo;
    private String valueType;
    private TimePeriod validFor;
    private Object tmfValue;
}
