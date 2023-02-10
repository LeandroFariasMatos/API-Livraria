package Livraria.config;

import Livraria.Mapper.AluguelMapper;
import Livraria.Mapper.VendasMapper;
import Livraria.domain.*;
import Livraria.exceptions.BadRequestException;
import Livraria.repository.*;
import Livraria.request.*;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationService {
    private final AluguelRepository aluguelRepository;
    private final LivroRepository livroRepository;
    private final VendasRepository vendasRepository;
    private final UsuarioRepository usuarioRepository;
    private final CarteiraRepository carteiraRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final VendasMapper vendasMapper;
    private final AluguelMapper aluguelMapper;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse salvar(UsuarioPostRequestBody usuarioPostRequestBody){
        var carteira = new Carteira();
        Role role;
        if(usuarioPostRequestBody.getRole().equalsIgnoreCase("user")){
            role = Role.USER;
        }else{
            role = Role.ADMIN;
        }
        carteira.setSaldo(0);
        var usuario = Usuario
                .builder()
                .nomeCliente(usuarioPostRequestBody.getNomeCliente())
                .email(usuarioPostRequestBody.getEmail())
                .cpf(usuarioPostRequestBody.getCpf())
                .senha(passwordEncoder.encode(usuarioPostRequestBody.getSenha()))
                .carteira(carteiraRepository.save(carteira))
                .role(role)
                .build();
        usuarioRepository.save(usuario);
        var jwtToken = jwtService.generateToken(usuario);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse atualizar(UsuarioPutRequestBody usuarioPutRequestBody){
        var usuario = usuarioRepository.findById(usuarioPutRequestBody.getId()).orElseThrow(()-> new BadRequestException("Id usuario não encontrado"));
        usuario.setNomeCliente(usuarioPutRequestBody.getNomeCliente());
        usuario.setEmail(usuarioPutRequestBody.getEmail());
        usuario.setCpf(usuarioPutRequestBody.getCpf());
        usuario.setSenha(passwordEncoder.encode(usuarioPutRequestBody.getSenha()));
        usuarioRepository.save(usuario);
        var jwtToken = jwtService.generateToken(usuario);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse logar(UsuarioLoginRequestBody usuarioLoginRequestBody){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuarioLoginRequestBody.getEmail(),
                        usuarioLoginRequestBody.getSenha()
                )
        );

        var usuario = usuarioRepository.findByEmail(usuarioLoginRequestBody.getEmail());
        if(usuario.getNomeCliente().isEmpty()){
            throw new BadRequestException("Email não encontrado");
        }
        var jwtToken = jwtService.generateToken(usuario);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse depositar(UsuarioDepositarRequestBody usuarioDepositarRequestBody) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuarioDepositarRequestBody.getEmail(),
                        usuarioDepositarRequestBody.getSenha())
        );
        var usuarioSalvo = usuarioRepository.findByEmail(usuarioDepositarRequestBody.getEmail());

        Carteira carteira = usuarioSalvo.getCarteira();
        carteira.setSaldo(usuarioDepositarRequestBody.getSaldo() + carteira.getSaldo());
        usuarioSalvo.setCarteira(carteira);
        usuarioRepository.save(usuarioSalvo);
        var jwtToken = jwtService.generateToken(usuarioSalvo);

        return AuthenticationResponse.builder().token(jwtToken).build();

    }

    public AuthenticationResponse vendas(VendasPostRequestBody vendasPostRequestBody) {
        var usuario = usuarioRepository.findByEmail(vendasPostRequestBody.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        vendasPostRequestBody.getEmail(),
                        vendasPostRequestBody.getSenha())
        );
        Vendas vendas = vendasMapper.toVendas(vendasPostRequestBody);
        var saldoAtual = usuario.getCarteira().getSaldo();
        if(saldoAtual < vendas.getPreco()) {
            throw new BadRequestException("Saldo Insuficiente");
        }
        vendasRepository.save(vendas);
        usuario.getCarteira().setSaldo(saldoAtual - vendas.getPreco());
        usuarioRepository.save(usuario);
        var jwtToken = jwtService.generateToken(usuario);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }

    public AuthenticationResponse aluguel(AluguelPostRequestBody aluguelPostRequestBody) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        aluguelPostRequestBody.getEmail(),
                        aluguelPostRequestBody.getSenha())
        );
        var usuario = usuarioRepository.findByEmail(aluguelPostRequestBody.getEmail());
        var livro = livroRepository.findByNomeLivro(aluguelPostRequestBody.getLivro());
        var saldoAtual = usuario.getCarteira().getSaldo();
        Aluguel aluguel = aluguelMapper.toAluguel(aluguelPostRequestBody);
        LocalDate diaAtual = LocalDate.now();
        LocalDate diaDaDevolucao = aluguel.getDataDevolucao().toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
        long tempoAlugadoEmDias = ChronoUnit.DAYS.between(diaAtual,diaDaDevolucao );
        var precoAluguel = livro.getPrecoAluguelPorDia() *tempoAlugadoEmDias;
        if(livro.getStatus() == 1){
            throw new BadRequestException("Livro está alugado no momento");
        }
        if(saldoAtual < precoAluguel) {
            throw new BadRequestException("Saldo Insuficiente");
        }
        livro.setStatus(1);
        livroRepository.save(livro);
        usuario.getCarteira().setSaldo(saldoAtual - precoAluguel);
        usuarioRepository.save(usuario);
        aluguel.setPreco(precoAluguel);
        aluguelRepository.save(aluguel);
        var jwtToken = jwtService.generateToken(usuario);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
