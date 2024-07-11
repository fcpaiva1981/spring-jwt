package br.com.fcpaiva.jwtsprinsecurity.infrastructure.security;

import br.com.fcpaiva.jwtsprinsecurity.infrastructure.entity.UsuariosEntity;
import br.com.fcpaiva.jwtsprinsecurity.infrastructure.repository.UsuariosRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private  TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null) {
            var username = tokenService.validateToken(token);
            Optional<UsuariosEntity> usuario = usuariosRepository.findByUsername(username);
            if (usuario.isPresent()) {
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null ) {
            return null;
        }

        return authHeader.replace("Bearer ", "");
    }
}
