package Livraria.Mapper;

import Livraria.domain.Autor;
import Livraria.request.AutorPostRequestBody;
import Livraria.request.AutorPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AutorMapper {
    public static final AutorMapper INSTANCE = Mappers.getMapper(AutorMapper.class);
    public abstract Autor toAutor(AutorPostRequestBody autorPostRequestBody);
    public abstract Autor toAutor(AutorPutRequestBody autorPutRequestBody);
}
