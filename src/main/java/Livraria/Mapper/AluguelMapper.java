package Livraria.Mapper;

import Livraria.domain.Aluguel;
import Livraria.repository.LivroRepository;
import Livraria.repository.UsuarioRepository;
import Livraria.request.AluguelPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class AluguelMapper {
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;
    public Aluguel toAluguel(AluguelPostRequestBody aluguelPostRequestBody) {
        SimpleDateFormat formato =  new SimpleDateFormat("dd/MM/yyyy");
        Date dataDevolucao = null;
        try {
            dataDevolucao = formato.parse(aluguelPostRequestBody.getDataDevolucao());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Aluguel
                .builder()
                .usuario(usuarioRepository.findByEmail(aluguelPostRequestBody.getEmail()))
                .livro(livroRepository.findByNomeLivro(aluguelPostRequestBody.getLivro()))
                .dataDevolucao(dataDevolucao).build();

    }
}
