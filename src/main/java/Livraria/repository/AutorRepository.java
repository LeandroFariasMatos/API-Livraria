package Livraria.repository;

import Livraria.domain.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {
    Optional<Autor> findByNomeAutor(String nome);

    Page<Autor> findByNomeAutor(Pageable pageable, String nome);

}
