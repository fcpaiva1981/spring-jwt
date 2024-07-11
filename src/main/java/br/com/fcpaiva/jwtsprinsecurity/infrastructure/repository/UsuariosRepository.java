package br.com.fcpaiva.jwtsprinsecurity.infrastructure.repository;

import br.com.fcpaiva.jwtsprinsecurity.infrastructure.entity.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<UsuariosEntity,Long> {
    Optional<UsuariosEntity> findByUsername(String username);
}
