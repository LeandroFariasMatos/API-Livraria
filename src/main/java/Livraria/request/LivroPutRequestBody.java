package Livraria.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivroPutRequestBody {

   private UUID id;
   private String nomeLivro;
   private String generoLivro;
   private double precoAluguelPorDia;
   private double precoVenda;
   private int status;


}
