package Livraria.service;

import Livraria.request.UsuarioDTO;
import Livraria.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Page<UsuarioDTO> findAll() {
        List<UsuarioDTO> usuarioDTOList = usuarioRepository.findAll()
                .stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getId(),
                        usuario.getNomeCliente(),
                        usuario.getEmail(),
                        usuario.getCpf(),
                        usuario.getCarteira(),
                        usuario.getRole()
                )).collect(Collectors.toList());
        return new PageImpl<>(usuarioDTOList);

    }


    public Page<UsuarioDTO> findByNome(String nome) {
        List<UsuarioDTO> usuarioDTOList = usuarioRepository.findByNomeCliente(nome)
                .stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getId(),
                        usuario.getNomeCliente(),
                        usuario.getEmail(),
                        usuario.getCpf(),
                        usuario.getCarteira(),
                        usuario.getRole()
                )).collect(Collectors.toList());
        return new PageImpl<>(usuarioDTOList);
    }


    public void deletar(UUID id) {
        usuarioRepository.deleteById(id);
    }
}
