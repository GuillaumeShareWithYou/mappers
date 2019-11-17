package com.training.training.mapper;

import com.training.training.dto.Date;
import com.training.training.dto.Date2;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper(componentModel="spring")
public interface DateMapper {

    @Mapping(source = "missionStatus", target = "mission")
    Date map(Date2 date2);

    @InheritInverseConfiguration
    @Mapping(target = "count", constant = "2")
    Date2 map(Date date2);
}
