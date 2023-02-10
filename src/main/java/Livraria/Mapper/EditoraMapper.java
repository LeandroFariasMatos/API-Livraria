package Livraria.Mapper;

import Livraria.domain.Editora;
import Livraria.request.EditoraPostRequestBody;
import Livraria.request.EditoraPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class EditoraMapper {
    public static final EditoraMapper INSTANCE = Mappers.getMapper(EditoraMapper.class);

    public abstract Editora toEditora(EditoraPostRequestBody editoraPostRequestBody);
    public abstract Editora toEditora(EditoraPutRequestBody editoraPutRequestBody);


}
