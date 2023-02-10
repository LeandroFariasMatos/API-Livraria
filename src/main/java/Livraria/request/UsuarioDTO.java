package Livraria.request;

import Livraria.domain.Carteira;
import Livraria.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private UUID id;
    private String nomeCliente;
    private String email;
    private String cpf;
    private Carteira carteira;
    private Role role;
}
