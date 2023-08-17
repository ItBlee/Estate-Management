package com.itblee.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter {

    @Autowired
    private ModelMapper modelMapper;

    protected <E> E convert(Object object, Class<E> convertTo) {
        if (object == null)
            return null;
        if (object.getClass() == convertTo)
            return convertTo.cast(object);
        return modelMapper.map(object, convertTo);
    }

    protected <K, V> List<K> convert(Collection<V> collection, Class<K> convertTo) {
        return collection.stream()
                .map(v -> convert(v, convertTo))
                .collect(Collectors.toList());
    }

    public <T> void merge(T source, T destination) {
        modelMapper.map(source, destination);
    }

}
