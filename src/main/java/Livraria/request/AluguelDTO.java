package Livraria.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AluguelDTO {
    private UUID id;
    private LivroDTO livroDTO;
    private UsuarioDTO usuarioDTO;
    private double preco;
    private Date dataDevolucao;
}
