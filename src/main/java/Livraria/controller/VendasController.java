package Livraria.controller;

import Livraria.domain.Vendas;
import Livraria.repository.VendasRepository;
import Livraria.request.VendasDTO;
import Livraria.service.VendasService;
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
public class VendasController {
    private final VendasService vendasService;

    @GetMapping("/vendas")
    public ResponseEntity<Page<VendasDTO>> findAll(){
        Page<VendasDTO> vendasDTOpage = vendasService.findAll();
        return new ResponseEntity<>(vendasDTOpage, HttpStatus.OK);
    }

    @GetMapping("/vendas/usuario")
    public ResponseEntity<Page<VendasDTO>> findByUsuario(@RequestParam String nome){
        Page<VendasDTO> vendasDTOPage =vendasService.findByUsuario(nome);
        return new ResponseEntity<>(vendasDTOPage,HttpStatus.OK);
    }
}
