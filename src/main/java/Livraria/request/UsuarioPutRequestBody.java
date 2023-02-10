package Livraria.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPutRequestBody {
    private UUID id;
    private String nomeCliente;
    private String email;
    private String cpf;
    private String senha;
}
