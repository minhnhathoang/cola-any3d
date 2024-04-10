package domain;

public interface COAssembler<CO, Entity> {

    Entity toEntity(CO dto);

    CO toCO(Entity entity);
}
