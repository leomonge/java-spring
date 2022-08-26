package com.course.practice.controller;

import com.course.practice.entity.Rol;
import com.course.practice.entity.Usuario;
import com.course.practice.entity.UsuarioRol;
import com.course.practice.service.RolService;
import com.course.practice.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin("*")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final RolService rolService;
    public UsuarioController(UsuarioService usuarioService, RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    @PostMapping("")
    ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        usuario.setPerfil("default.png");
        //usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
        usuario.setPassword((usuario.getPassword()));

        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = rolService.obtenerPorId(1);

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);

        try{
            return new ResponseEntity<>(usuarioService.guardarUsuario(usuario, usuarioRoles), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("")
    ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerUsuarios());
    }

    @GetMapping("/{usuario}")
    ResponseEntity<Usuario> listarUsuarioPorUsuario(@PathVariable(name = "usuario") String usuario) {
        return ResponseEntity.ok(usuarioService.obtenerUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Object>> eliminarUsuario(@PathVariable(name = "id") Integer id) {
        Map<String, Object> ms = new HashMap<>();
        ms.put("msg", usuarioService.eliminarUsuario(id));
        ms.put("status", HttpStatus.OK);
        return ResponseEntity.ok(ms);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Map<String, Object>> Exceptio(Exception ex){
        Map<String, Object> ms = new HashMap<>();
        ms.put("time", new Date());
        ms.put("cause", HttpStatus.INTERNAL_SERVER_ERROR);
        ms.put("msg", "error");
        ms.put("status", 500);
        return new ResponseEntity<>(ms, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario, @PathVariable Integer id) {
        try{
            return new ResponseEntity<>(usuarioService.editarUsuario(usuario, id), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
