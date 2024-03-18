package domain;

public interface DTOAssembler<DTO, Entity> {

    Entity toEntity(DTO dto);

    DTO toDTO(Entity entity);
}
