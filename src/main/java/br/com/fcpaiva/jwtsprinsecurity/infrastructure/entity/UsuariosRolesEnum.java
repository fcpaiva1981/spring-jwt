package br.com.fcpaiva.jwtsprinsecurity.infrastructure.entity;

import lombok.Getter;

@Getter
public enum UsuariosRolesEnum {
    ADMIN("admin"),
    USER("user");

    private String role;

    UsuariosRolesEnum(String role) {
        this.role = role;
    }

}
