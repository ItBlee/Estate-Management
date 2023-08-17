package com.itblee.converter;

import com.itblee.repository.enums.RentType;
import com.itblee.utils.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class RentTypeConverter implements AttributeConverter<List<RentType>, String> {

    @Override
    public String convertToDatabaseColumn(List<RentType> attribute) {
        return attribute.stream()
                .map(RentType::getCode)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<RentType> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(","))
                .filter(StringUtils::isNotBlank)
                .map(RentType::valueOf)
                .collect(Collectors.toList());
    }



}
