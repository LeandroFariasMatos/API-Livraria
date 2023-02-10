package Livraria.Mapper;

import Livraria.domain.Carteira;
import Livraria.domain.Usuario;
import Livraria.exceptions.BadRequestException;
import Livraria.repository.CarteiraRepository;
import Livraria.request.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {
    private final CarteiraRepository carteiraRepository;

    public Usuario toUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .id(usuarioDTO.getId())
                .nomeCliente(usuarioDTO.getNomeCliente())
                .cpf(usuarioDTO.getCpf())
                .email(usuarioDTO.getEmail())
                .carteira(carteiraRepository.findById(usuarioDTO.getCarteira().getId()).orElseThrow(()-> new BadRequestException("Id Carteira não encontrado")))
                .build();
    }

    public UsuarioDTO toUsuarioDTO(Usuario usuario){
        return UsuarioDTO
                .builder()
                .id(usuario.getId())
                .nomeCliente(usuario.getNomeCliente())
                .cpf(usuario.getCpf())
                .email(usuario.getEmail())
                .carteira(usuario.getCarteira())
                .role(usuario.getRole())
                .build();
    }
}
