package com.course.practice;

import com.course.practice.entity.Rol;
import com.course.practice.entity.Usuario;
import com.course.practice.entity.UsuarioRol;
import com.course.practice.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
		System.out.println("--> Usuario actual: " + System.getProperty("user.name"));
		System.out.println("--> Sistema Opertaivo: " + System.getProperty("os.name"));
		System.out.println("--> Arquitectura: " + System.getProperty("os.arch"));
	}

	@Autowired
	UsuarioService usuarioService;
//	@Override
//	public void run(String... args) throws Exception {
//		Usuario usuario = new Usuario();
//		usuario.setNombre("Obed Navarrete");
//		usuario.setUsuario("obed.navad");
//		usuario.setPassword("1234");
//		usuario.setEmail("ndiaz@co");
//		usuario.setTel("88998877");
//		usuario.setPerfil("foto.png");
//		Rol rol = new Rol(
//				1,
//				"USER",
//				null
//		);
//		Set<UsuarioRol> usuarioRoles = new HashSet<>();
//		UsuarioRol usuarioRol = new UsuarioRol();
//		usuarioRol.setRol(rol);
//		usuarioRol.setUsuario(usuario);
//		usuarioRoles.add(usuarioRol);
//		Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuarioRoles);
//		System.out.println(usuarioGuardado);
//	}
}
