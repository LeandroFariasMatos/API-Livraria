package Livraria.service;

import Livraria.Mapper.LivrariaMapper;
import Livraria.domain.Livraria;
import Livraria.exceptions.BadRequestException;
import Livraria.repository.LivrariaRepository;
import Livraria.repository.LivroRepository;
import Livraria.request.LivrariaPostRequestBody;
import Livraria.request.LivrariaPutRequestBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivrariaService {
    private final LivrariaRepository livrariaRepository;
    private final LivroRepository livroRepository;


    public Page<Livraria> findAll(Pageable pageable) {
        return livrariaRepository.findAll(pageable);
    }

    public Page<Livraria> findByNome(Pageable pageable, String nome) {
        return livrariaRepository.findByNomeLivraria(pageable,nome);
    }

    public Livraria salvar(LivrariaPostRequestBody livrariaPostRequestBody) {
        return livrariaRepository.save(LivrariaMapper.INSTANCE.toLivraria(livrariaPostRequestBody));
    }

    @Transactional
    public void atualizar(LivrariaPutRequestBody livrariaPutRequestBody) {
        Livraria livrariaSalvo = findByIdOrElse(livrariaPutRequestBody.getId());
        livrariaSalvo.setNomeLivraria(livrariaPutRequestBody.getNomeLivraria());
        livrariaRepository.save(livrariaSalvo);
    }

    private Livraria findByIdOrElse(UUID id) {
    return livrariaRepository.findById(id).orElseThrow(()->new BadRequestException("Id Livraria não encontrado"));
    }

    public void deletar(UUID id) {
        livrariaRepository.deleteById(id);
    }
}
