package Livraria.Mapper;

import Livraria.domain.Livro;
import Livraria.exceptions.BadRequestException;
import Livraria.repository.AutorRepository;
import Livraria.repository.EditoraRepository;
import Livraria.repository.LivrariaRepository;
import Livraria.request.LivroDTO;
import Livraria.request.LivroPostRequestBody;
import Livraria.request.LivroPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LivroMapper {
    private final AutorRepository autorRepository;
    private final LivrariaRepository livrariaRepository;
    private final EditoraRepository editoraRepository;

    public Livro toLivro(LivroPostRequestBody livroPostRequestBody){
        return Livro
                .builder()
                .nomeLivro(livroPostRequestBody.getNomeLivro())
                .generoLivro(livroPostRequestBody.getGeneroLivro())
                .editora(editoraRepository.findByNomeEditora(livroPostRequestBody.getEditora()).orElseThrow(()->new BadRequestException("Nome da Editora não encontrado")))
                .livraria(livrariaRepository.findByNomeLivraria(livroPostRequestBody.getLivraria()).orElseThrow(()->new BadRequestException("Nome da Livraria não encontrado")))
                .autor(autorRepository.findByNomeAutor(livroPostRequestBody.getAutor()).orElseThrow(()->new BadRequestException("Nome do Autor não encontrado")))
                .precoAluguelPorDia(livroPostRequestBody.getPrecoAluguelPorDia())
                .precoVenda(livroPostRequestBody.getPrecoVenda())
                .status(0)
                .build();
    }

    public LivroDTO toLivroDTO(Livro livro){
        return LivroDTO.builder()
                .id(livro.getId())
                .nome(livro.getNomeLivro())
                .genero(livro.getGeneroLivro())
                .autor(livro.getAutor())
                .livraria(livro.getLivraria())
                .editora(livro.getEditora())
                .build();
    }

}
