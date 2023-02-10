package Livraria.controller;

import Livraria.domain.Editora;
import Livraria.request.EditoraPostRequestBody;
import Livraria.request.EditoraPutRequestBody;
import Livraria.service.EditoraService;
import jakarta.websocket.server.PathParam;
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
public class EditoraController {
    private final EditoraService editoraService;

    @GetMapping("/editora")
    public ResponseEntity<Page<Editora>> findAll(Pageable pageable){
        Page<Editora> editoraPage = editoraService.findAll(pageable);
        return new ResponseEntity<>(editoraPage, HttpStatus.OK);
    }

    @GetMapping("/editora/nome")
    public ResponseEntity<Page<Editora>> findByNome(Pageable pageable,@RequestParam String nome){
        Page<Editora> editoraPage = editoraService.findByNome(pageable,nome);
        return new ResponseEntity<>(editoraPage,HttpStatus.OK);
    }

    @PostMapping("/editora/salvar")
    public ResponseEntity<Editora> salvar(@RequestBody EditoraPostRequestBody editoraPostRequestBody){
        return new ResponseEntity<>(editoraService.salvar(editoraPostRequestBody),HttpStatus.CREATED);
    }
    
    @PutMapping("/editora/atualizar")
    public ResponseEntity<Void> atualizar(@RequestBody EditoraPutRequestBody editoraPutRequestBody){
        editoraService.atualizar(editoraPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping("/editora/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        editoraService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
