package Livraria.request;

import Livraria.domain.Livro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendasDTO {
    private UUID id;
    private UsuarioDTO usuarioDTO;
    private LivroDTO livro;
    private double preco;
}
