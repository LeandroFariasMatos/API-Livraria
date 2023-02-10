package Livraria.Mapper;

import Livraria.domain.Livro;
import Livraria.domain.Usuario;
import Livraria.domain.Vendas;
import Livraria.repository.LivroRepository;
import Livraria.repository.UsuarioRepository;
import Livraria.request.VendasDTO;
import Livraria.request.VendasPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VendasMapper {
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;

    public Vendas toVendas(VendasPostRequestBody vendasPostRequestBody){
        Livro livro = livroRepository.findByNomeLivro(vendasPostRequestBody.getLivro());
        return Vendas.builder()
                .usuario(usuarioRepository.findByEmail(vendasPostRequestBody.getEmail()))
                .livro(livro)
                .preco(livro.getPrecoVenda())
                .build();
    }





}
