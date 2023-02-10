package Livraria.controller;

import Livraria.config.AuthenticationResponse;
import Livraria.config.AuthenticationService;
import Livraria.request.*;
import Livraria.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("livraria")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final AuthenticationService authenticationService;

    @GetMapping("/usuario")
    public ResponseEntity<Page<UsuarioDTO>> findAll(){
        Page<UsuarioDTO> usuarioDTOPage = usuarioService.findAll();
        return new ResponseEntity<>(usuarioDTOPage, HttpStatus.OK);
    }

    @GetMapping("/usuario/nome")
    public ResponseEntity<Page<UsuarioDTO>> findByNome(@RequestParam String nome){
        Page<UsuarioDTO> usuarioDTOPage = usuarioService.findByNome(nome);
        return new ResponseEntity<>(usuarioDTOPage,HttpStatus.OK);
    }

    @PostMapping("/usuario/salvar")
    public ResponseEntity<AuthenticationResponse> salvar(@RequestBody UsuarioPostRequestBody usuarioPostRequestBody){
        return new ResponseEntity<>(authenticationService.salvar(usuarioPostRequestBody),HttpStatus.CREATED);
    }

    @PostMapping("/usuario/logar")
    public ResponseEntity<AuthenticationResponse> logar(@RequestBody UsuarioLoginRequestBody usuarioLoginRequestBody) {
        return new ResponseEntity<>(authenticationService.logar(usuarioLoginRequestBody), HttpStatus.OK);
    }

    @PostMapping("/usuario/logado/venda")
    public ResponseEntity<AuthenticationResponse> vendas(@RequestBody VendasPostRequestBody vendasPostRequestBody){
        return new ResponseEntity<>(authenticationService.vendas(vendasPostRequestBody),HttpStatus.OK);
    }

    @PostMapping("/usuario/logado/aluguel")
    public ResponseEntity<AuthenticationResponse> aluguel(@RequestBody AluguelPostRequestBody aluguelPostRequestBody){
        return new ResponseEntity<>(authenticationService.aluguel(aluguelPostRequestBody),HttpStatus.OK);
    }

    @PutMapping("/usuario/atualizar")
    public ResponseEntity<AuthenticationResponse> atualizar(@RequestBody UsuarioPutRequestBody usuarioPutRequestBody){
        return new ResponseEntity<>(authenticationService.atualizar(usuarioPutRequestBody),HttpStatus.NO_CONTENT);
    }


    @PutMapping("/usuario/depositar")
    public ResponseEntity<AuthenticationResponse> depositar(@RequestBody UsuarioDepositarRequestBody usuarioDepositarRequestBody){
        return new ResponseEntity<>(authenticationService.depositar(usuarioDepositarRequestBody),HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/usuario/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        usuarioService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
