package Livraria.repository;

import Livraria.domain.Aluguel;
import Livraria.request.AluguelDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AluguelRepository extends JpaRepository<Aluguel, UUID> {

    @Query("SELECT a FROM Aluguel a WHERE a.usuario.nomeCliente=(:nome)")
    List<Aluguel> findByUsuario(String nome);
}
