package Livraria.controller;

import Livraria.domain.Autor;
import Livraria.request.AutorPostRequestBody;
import Livraria.request.AutorPutRequestBody;
import Livraria.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("livraria")
@RequiredArgsConstructor
public class AutorController {
    private final AutorService autorService;

    @GetMapping("/autor")
    public ResponseEntity<Page<Autor>> findAll(Pageable pageable){
        Page<Autor> autorPage = autorService.findAll(pageable);
        return new ResponseEntity<>(autorPage, HttpStatus.OK);
    }

    @GetMapping("/autor/nome")
    public ResponseEntity<Page<Autor>> findByNome(Pageable pageable, @RequestParam String nome){
        Page<Autor> autorPage = autorService.findByNome(pageable,nome);
        return new ResponseEntity<>(autorPage,HttpStatus.OK);
    }

    @PostMapping("/autor/salvar")
    public ResponseEntity<Autor> salvar(@RequestBody AutorPostRequestBody autorPostRequestBody){
        return new ResponseEntity<>(autorService.save(autorPostRequestBody),HttpStatus.CREATED);
    }

    @PutMapping("/autor/atualizar")
    public ResponseEntity<Void> atualizar(@RequestBody AutorPutRequestBody autorPutRequestBody){
        autorService.atualizar(autorPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/autor/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        autorService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
