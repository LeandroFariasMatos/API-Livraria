package Livraria.Mapper;

import Livraria.domain.Livraria;
import Livraria.request.LivrariaPostRequestBody;
import Livraria.request.LivrariaPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class LivrariaMapper {
    public static final LivrariaMapper INSTANCE = Mappers.getMapper(LivrariaMapper.class);

    public abstract Livraria toLivraria(LivrariaPostRequestBody livrariaPostRequestBody);
    public abstract Livraria toLivraria(LivrariaPutRequestBody livrariaPutRequestBody);
}
