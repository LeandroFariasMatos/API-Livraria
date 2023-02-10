package Livraria.service;

import Livraria.Mapper.EditoraMapper;
import Livraria.domain.Editora;
import Livraria.exceptions.BadRequestException;
import Livraria.repository.EditoraRepository;
import Livraria.request.EditoraPostRequestBody;
import Livraria.request.EditoraPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EditoraService {
    private final EditoraRepository editoraRepository;

    public Page<Editora> findAll(Pageable pageable) {
        return editoraRepository.findAll(pageable);
    }

    public Page<Editora> findByNome(Pageable pageable, String nome) {
        return editoraRepository.findByNomeEditora(pageable,nome);
    }

    public Editora salvar(EditoraPostRequestBody editoraPostRequestBody) {
        return editoraRepository.save(EditoraMapper.INSTANCE.toEditora(editoraPostRequestBody));
    }

    public void atualizar(EditoraPutRequestBody editoraPutRequestBody) {
        Editora editoraSalvo = findByIdOrElse(editoraPutRequestBody.getId());
        editoraSalvo.setNomeEditora(editoraPutRequestBody.getNomeEditora());
        editoraRepository.save(editoraSalvo);
    }

    public Editora findByIdOrElse(UUID id){
        return editoraRepository.findById(id).orElseThrow(()-> new BadRequestException("Id Editor não encontrado"));
    }

    public void deletar(UUID id) {
        editoraRepository.deleteById(id);
    }
}
