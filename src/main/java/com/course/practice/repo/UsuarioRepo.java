package com.course.practice.repo;

import com.course.practice.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
    Usuario findByUsuario(String usuario);
}
