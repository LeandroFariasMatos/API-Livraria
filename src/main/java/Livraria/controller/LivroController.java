package Livraria.controller;

import Livraria.domain.Livro;
import Livraria.request.LivroPostRequestBody;
import Livraria.request.LivroPutRequestBody;
import Livraria.service.LivroService;
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
public class LivroController {
    private final LivroService livroService;

    @GetMapping("/livro")
    public ResponseEntity<Page<Livro>> findAll(Pageable pageable){
        Page<Livro> livroPage = livroService.findAll(pageable);
        return new ResponseEntity<>(livroPage, HttpStatus.OK);
    }

    @GetMapping("/livro/nome")
    public ResponseEntity<Page<Livro>> findByNome(Pageable pageable, @RequestParam String nome){
        Page<Livro> livroPage = livroService.findByNome(pageable,nome);
        return new ResponseEntity<>(livroPage,HttpStatus.OK);
    }
    @GetMapping("/livro/genero")
    public ResponseEntity<Page<Livro>> findByGenero(Pageable pageable, @RequestParam String genero){
        Page<Livro> livroPage = livroService.findByGenero(pageable,genero);
        return new ResponseEntity<>(livroPage,HttpStatus.OK);
    }

    @GetMapping("/livro/editora")
    public ResponseEntity<Page<Livro>> findByEditora(Pageable pageable, @RequestParam String editora){
        Page<Livro> livroPage = livroService.findByEditora(pageable,editora);
        return new ResponseEntity<>(livroPage,HttpStatus.OK);
    }

    @GetMapping("/livro/autor")
    public ResponseEntity<Page<Livro>> findByAutor(Pageable pageable, @RequestParam String autor){
        Page<Livro> livroPage = livroService.findByAutor(pageable,autor);
        return new ResponseEntity<>(livroPage,HttpStatus.OK);
    }

    @GetMapping("/livro/livraria")
    public ResponseEntity<Page<Livro>> findByLivraria(Pageable pageable, @RequestParam String livraria){
        Page<Livro> livroPage = livroService.findByLivraria(pageable,livraria);
        return new ResponseEntity<>(livroPage,HttpStatus.OK);
    }

    @PostMapping("/livro/salvar")
    public ResponseEntity<Livro> salvar(@RequestBody LivroPostRequestBody livroPostRequestBody){
        return new ResponseEntity<>(livroService.salvar(livroPostRequestBody),HttpStatus.CREATED);
    }

    @PutMapping("/livro/atualizar")
    public ResponseEntity<Void> atualizar(@RequestBody LivroPutRequestBody livroPutRequestBody){
        livroService.atualizar(livroPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/livro/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        livroService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
