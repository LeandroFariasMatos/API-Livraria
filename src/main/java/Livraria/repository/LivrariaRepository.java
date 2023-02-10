package Livraria.repository;

import Livraria.domain.Livraria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LivrariaRepository extends JpaRepository<Livraria, UUID> {
    Optional<Livraria> findByNomeLivraria(String nome);
    Page<Livraria> findByNomeLivraria(Pageable pageable, String nome);
}
