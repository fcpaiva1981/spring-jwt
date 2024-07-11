package br.com.fcpaiva.jwtsprinsecurity.infrastructure.service;

import br.com.fcpaiva.jwtsprinsecurity.infrastructure.entity.UsuariosEntity;
import br.com.fcpaiva.jwtsprinsecurity.infrastructure.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuariosEntity> usuariosEntity = usuariosRepository.findByUsername(username);

        if (usuariosEntity.isPresent()) {
            return usuariosEntity.get();
        }

        return null;
    }
}
