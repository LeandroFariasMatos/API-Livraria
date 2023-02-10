package Livraria.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPostRequestBody {
    private String nomeCliente;
    private String email;
    private String cpf;
    private String senha;
    private String role;
}
