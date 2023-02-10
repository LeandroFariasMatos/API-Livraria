package Livraria.repository;

import Livraria.domain.Editora;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, UUID> {
    Optional<Editora> findByNomeEditora(String nome);
    Page<Editora> findByNomeEditora(Pageable pageable, String nome);
}
