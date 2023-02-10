package Livraria.controller;

import Livraria.domain.Editora;
import Livraria.domain.Livraria;
import Livraria.request.LivrariaPostRequestBody;
import Livraria.request.LivrariaPutRequestBody;
import Livraria.service.LivrariaService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("livraria")
@RequiredArgsConstructor
public class LivrariaController {
    private final LivrariaService livrariaService;

    @GetMapping
    public ResponseEntity<Page<Livraria>> findAll(Pageable pageable){
        Page<Livraria> livrariaPage = livrariaService.findAll(pageable);
        return new ResponseEntity<>(livrariaPage, HttpStatus.OK);
    }

    @GetMapping("/nome")
    public ResponseEntity<Page<Livraria>> findByNome(Pageable pageable, @RequestParam String nome){
        Page<Livraria> livrariaPage = livrariaService.findByNome(pageable,nome);
        return new ResponseEntity<>(livrariaPage,HttpStatus.OK);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Livraria> salvar(@RequestBody LivrariaPostRequestBody livrariaPostRequestBody){
        return new ResponseEntity<>(livrariaService.salvar(livrariaPostRequestBody),HttpStatus.OK);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Void> atualizar(@RequestBody LivrariaPutRequestBody livrariaPutRequestBody){
        livrariaService.atualizar(livrariaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        livrariaService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
