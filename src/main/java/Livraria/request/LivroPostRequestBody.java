package Livraria.request;

import Livraria.domain.Autor;
import Livraria.domain.Editora;
import Livraria.domain.Livraria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroPostRequestBody {
    private String nomeLivro;
    private String generoLivro;
    private String editora;
    private String autor;
    private String livraria;
    private double precoAluguelPorDia;
    private double precoVenda;
}
