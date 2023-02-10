package Livraria.service;

import Livraria.Mapper.LivroMapper;
import Livraria.Mapper.UsuarioMapper;
import Livraria.repository.AluguelRepository;
import Livraria.request.AluguelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AluguelService {
    private final AluguelRepository aluguelRepository;
    private final UsuarioMapper usuarioMapper;
    private final LivroMapper livroMapper;

    public Page<AluguelDTO> findAll() {
        List<AluguelDTO> aluguelDTOList = aluguelRepository.findAll()
                .stream()
                .map(aluguel -> new AluguelDTO(
                        aluguel.getId(),
                        livroMapper.toLivroDTO(aluguel.getLivro()),
                        usuarioMapper.toUsuarioDTO(aluguel.getUsuario()),
                        aluguel.getPreco(),
                        aluguel.getDataDevolucao()
                )).collect(Collectors.toList());
        return new PageImpl<>(aluguelDTOList);
    }

    public Page<AluguelDTO> findByUsuarioNome(String nome) {
        List<AluguelDTO> aluguelDTOList = aluguelRepository.findByUsuario(nome)
                .stream()
                .map(aluguel -> new AluguelDTO(
                        aluguel.getId(),
                        livroMapper.toLivroDTO(aluguel.getLivro()),
                        usuarioMapper.toUsuarioDTO(aluguel.getUsuario()),
                        aluguel.getPreco(),
                        aluguel.getDataDevolucao()
                )).collect(Collectors.toList());
        return new PageImpl<>(aluguelDTOList);
    }
}
