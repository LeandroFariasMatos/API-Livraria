package Livraria.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AluguelPostRequestBody {
    private String email;
    private String senha;
    private String livro;
    private String dataDevolucao;
}
