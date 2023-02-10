package Livraria.service;


import Livraria.Mapper.LivroMapper;
import Livraria.domain.Livro;
import Livraria.exceptions.BadRequestException;
import Livraria.repository.LivroRepository;
import Livraria.request.LivroPostRequestBody;
import Livraria.request.LivroPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public Page<Livro> findAll(Pageable pageable) {
        return livroRepository.findAll(pageable);
    }

    public Page<Livro> findByNome(Pageable pageable, String nome) {
        return livroRepository.findByNomeLivro(pageable,nome);
    }

    public Page<Livro> findByGenero(Pageable pageable, String genero) {
        return livroRepository.findByGeneroLivro(pageable,genero);
    }

    public Page<Livro> findByEditora(Pageable pageable, String editora) {
        return livroRepository.findByEditoraId(pageable,editora);
    }

    public Page<Livro> findByAutor(Pageable pageable, String autor) {
        return livroRepository.findByAutorId(pageable,autor);
    }

    public Page<Livro> findByLivraria(Pageable pageable, String livraria) {
        return livroRepository.findByLivrariaId(pageable,livraria);
    }

    public Livro salvar(LivroPostRequestBody livroPostRequestBody) {
        return livroRepository.save(livroMapper.toLivro(livroPostRequestBody));
    }

    public void atualizar(LivroPutRequestBody livroPutRequestBody) {
        Livro livroSalvo = findByIdOrElse(livroPutRequestBody.getId());
        livroSalvo.setNomeLivro(livroPutRequestBody.getNomeLivro());
        livroSalvo.setGeneroLivro(livroPutRequestBody.getGeneroLivro());
        livroSalvo.setPrecoVenda(livroPutRequestBody.getPrecoVenda());
        livroSalvo.setPrecoAluguelPorDia(livroPutRequestBody.getPrecoAluguelPorDia());
        livroRepository.save(livroSalvo);
    }

    public Livro findByIdOrElse(UUID id){
        return livroRepository.findById(id).orElseThrow(()-> new BadRequestException("Id Livro não encontrado"));
    }

    public void deletar(UUID id) {
        livroRepository.deleteById(id);
    }
}
