package com.course.practice.service;

import com.course.practice.entity.Usuario;
import com.course.practice.entity.UsuarioRol;

import java.util.List;
import java.util.Set;

public interface UsuarioService {
    List<Usuario> obtenerUsuarios();

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    Usuario obtenerUsuario(String usuario);

    String eliminarUsuario(Integer id);

    Usuario editarUsuario(Usuario usuario, Integer id);
}
