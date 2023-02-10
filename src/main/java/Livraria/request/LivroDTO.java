package Livraria.request;

import Livraria.domain.Autor;
import Livraria.domain.Editora;
import Livraria.domain.Livraria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {
    private UUID id;
    private String nome;
    private String genero;
    private Autor autor;
    private Editora editora;
    private Livraria livraria;
}
