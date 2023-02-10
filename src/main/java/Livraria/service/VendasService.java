package Livraria.service;

import Livraria.Mapper.LivroMapper;
import Livraria.Mapper.UsuarioMapper;
import Livraria.repository.VendasRepository;
import Livraria.request.VendasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendasService {
    private final VendasRepository vendasRepository;
    private final UsuarioMapper usuarioMapper;
    private final LivroMapper livroMapper;

    public Page<VendasDTO> findAll() {
        List<VendasDTO> vendasDTOList = vendasRepository.findAll()
                .stream()
                .map(vendas -> new VendasDTO(
                        vendas.getId(),
                        usuarioMapper.toUsuarioDTO(vendas.getUsuario()),
                        livroMapper.toLivroDTO(vendas.getLivro()),
                        vendas.getPreco()
                )).collect(Collectors.toList());
        return new PageImpl<>(vendasDTOList);
    }

    public Page<VendasDTO> findByUsuario(String nome) {
        List<VendasDTO> vendasDTOList = vendasRepository.findByUsuarioNomeCliente(nome)
                .stream()
                .map(vendas -> new VendasDTO(
                        vendas.getId(),
                        usuarioMapper.toUsuarioDTO(vendas.getUsuario()),
                        livroMapper.toLivroDTO(vendas.getLivro()),
                        vendas.getPreco()
                )).collect(Collectors.toList());
        return new PageImpl<>(vendasDTOList);
    }

}
