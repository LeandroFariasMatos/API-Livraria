package Livraria.repository;

import Livraria.domain.Vendas;
import Livraria.request.VendasDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, UUID> {
    @Query("SELECT v FROM Vendas v WHERE v.usuario.nomeCliente=(:nome)")
    List<Vendas> findByUsuarioNomeCliente(String nome);
}
