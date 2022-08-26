package com.course.practice.serviceImpl;

import com.course.practice.entity.Usuario;
import com.course.practice.repo.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired UsuarioRepo usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepo.findByUsuario(username);
        if (usuario == null){
            throw new UsernameNotFoundException("Usuario No Encontrado!");
        }
        return usuario;
    }
}
