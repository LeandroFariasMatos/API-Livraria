package Livraria.service;

import Livraria.Mapper.AutorMapper;
import Livraria.domain.Autor;
import Livraria.exceptions.BadRequestException;
import Livraria.repository.AutorRepository;
import Livraria.request.AutorPostRequestBody;
import Livraria.request.AutorPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {
    private final AutorRepository autorRepository;

    public Page<Autor> findAll(Pageable pageable) {
        return autorRepository.findAll(pageable);
    }

    public Page<Autor> findByNome(Pageable pageable, String nome) {
        return autorRepository.findByNomeAutor(pageable,nome);
    }

    public Autor save(AutorPostRequestBody autorPostRequestBody) {
        return autorRepository.save(AutorMapper.INSTANCE.toAutor(autorPostRequestBody));
    }

    public void atualizar(AutorPutRequestBody autorPutRequestBody) {
        Autor autorSalvo = findByIdOrElse(autorPutRequestBody.getId());
        autorSalvo.setNomeAutor(autorPutRequestBody.getNomeAutor());
        autorRepository.save(autorSalvo);
    }

    public Autor findByIdOrElse(UUID id){
        return autorRepository.findById(id).orElseThrow(()-> new BadRequestException("Id Autor não encontrado"));
    }

    public void deletar(UUID id) {
        autorRepository.deleteById(id);
    }
}
