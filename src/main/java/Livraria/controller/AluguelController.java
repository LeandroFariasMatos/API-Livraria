package Livraria.controller;

import Livraria.domain.Aluguel;
import Livraria.request.AluguelDTO;
import Livraria.service.AluguelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livraria")
@RequiredArgsConstructor
public class AluguelController {
    private final AluguelService aluguelService;

    @GetMapping("/aluguel")
    public ResponseEntity<Page<AluguelDTO>> findAll(){
        Page<AluguelDTO> aluguelDTOPage = aluguelService.findAll();
        return new ResponseEntity<>(aluguelDTOPage, HttpStatus.OK);
    }

    @GetMapping("/aluguel/usuario")
    public ResponseEntity<Page<AluguelDTO>> findByUsuario(@RequestParam String nome){
        Page<AluguelDTO> aluguelDTOPage = aluguelService.findByUsuarioNome(nome);
        return new ResponseEntity<>(aluguelDTOPage,HttpStatus.OK);
    }
}
