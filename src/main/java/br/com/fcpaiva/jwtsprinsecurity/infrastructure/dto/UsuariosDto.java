package br.com.fcpaiva.jwtsprinsecurity.infrastructure.dto;

import br.com.fcpaiva.jwtsprinsecurity.infrastructure.entity.UsuariosRolesEnum;


public record UsuariosDto(String username, String password, UsuariosRolesEnum role) {
}
