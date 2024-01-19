package net.steelcode.api.services.converters;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<E, DTO> {

    public abstract E fromDTO(DTO dto);

    public abstract DTO fromEntity(E e);

    public List<E> fromDTOs(List<DTO> dtos) {
        return dtos.stream().map(this::fromDTO).collect(Collectors.toList());
    }
    public List<DTO> fromEntities(List<E> entities) {
        return entities.stream().map(this::fromEntity).collect(Collectors.toList());
    }
}
