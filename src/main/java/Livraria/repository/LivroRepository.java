package Livraria.repository;

import Livraria.domain.Livraria;
import Livraria.domain.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {
    Page<Livro> findByNomeLivro(Pageable pageable, String nome);
    Livro findByNomeLivro(String nome);

    Page<Livro> findByGeneroLivro(Pageable pageable, String genero);
    Page<Livro> findByEditoraId(Pageable pageable, String editora);
    Page<Livro> findByAutorId(Pageable pageable,String nomeAutor);


    Page<Livro> findByLivrariaId(Pageable pageable, String livraria);

}
