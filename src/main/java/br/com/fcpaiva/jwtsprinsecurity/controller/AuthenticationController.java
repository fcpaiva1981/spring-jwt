package br.com.fcpaiva.jwtsprinsecurity.controller;

import br.com.fcpaiva.jwtsprinsecurity.infrastructure.dto.AuthenticationDto;
import br.com.fcpaiva.jwtsprinsecurity.infrastructure.dto.LoginResponseDto;
import br.com.fcpaiva.jwtsprinsecurity.infrastructure.dto.UsuariosDto;
import br.com.fcpaiva.jwtsprinsecurity.infrastructure.entity.UsuariosEntity;
import br.com.fcpaiva.jwtsprinsecurity.infrastructure.repository.UsuariosRepository;
import br.com.fcpaiva.jwtsprinsecurity.infrastructure.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        var usernamePassaWord = new UsernamePasswordAuthenticationToken(authenticationDto.username(), authenticationDto.password());
        var auth = authenticationManager.authenticate(usernamePassaWord);
        var token = tokenService.generateToken((UsuariosEntity) auth.getPrincipal());
        
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/registrar-usuario")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid UsuariosDto usuariosDto) {
        Optional<UsuariosEntity> usuarioExiste = this.usuariosRepository.findByUsername(usuariosDto.username());

         if (usuarioExiste.isPresent()) {
          return ResponseEntity.badRequest().build();
         }

         String encryptedPassaword = new BCryptPasswordEncoder().encode(usuariosDto.password());
         UsuariosEntity usuariosEntity = new UsuariosEntity(usuariosDto.username(),encryptedPassaword, usuariosDto.role());

         this.usuariosRepository.saveAndFlush(usuariosEntity);

         return ResponseEntity.ok(usuariosEntity);
    }
}
