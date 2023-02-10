package Livraria.repository;

import Livraria.domain.Usuario;
import Livraria.request.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Usuario findByEmail(String email);

    List<UsuarioDTO> findByNomeCliente(String nome);
    @Query("SELECT u FROM Usuario u WHERE u.nomeCliente=(:nome)")
    Usuario findByNomeClienteReturnUsuario(String nome);
}
