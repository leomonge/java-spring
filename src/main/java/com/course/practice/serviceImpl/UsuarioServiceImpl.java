package com.course.practice.serviceImpl;

import com.course.practice.entity.Usuario;
import com.course.practice.entity.UsuarioRol;
import com.course.practice.repo.RolRepo;
import com.course.practice.repo.UsuarioRepo;
import com.course.practice.service.UsuarioService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepo usuarioRepo;
    private final RolRepo rolRepo;

    public UsuarioServiceImpl(UsuarioRepo usuarioRepo, RolRepo rolRepo) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
    }

    @Override
    public List<Usuario> obtenerUsuarios(){
        return usuarioRepo.findAll();
    }

    @Override
    @Transactional
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepo.findByUsuario(usuario.getUsuario());
        if (usuarioLocal != null) {
            System.out.println("Existe el user!!!");
            throw new Exception("Ya existe ese usuario");
        } else {
            for (UsuarioRol x : usuarioRoles){
                rolRepo.save(x.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepo.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String usuario){
        return usuarioRepo.findByUsuario(usuario);
    }

    @Override
    public String eliminarUsuario(Integer id){
        try{
            usuarioRepo.deleteById(id);
            return "Eliminado con éxito";
        }catch (Exception e){
            return "Ocuarrió un error al eliminar";
        }
    }

    @Override
    public Usuario editarUsuario(Usuario usuario, Integer id){
        Usuario user = usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException());

        user.setUsuario(usuario.getUsuario());
        user.setPassword(usuario.getPassword());
        user.setPerfil(usuario.getPerfil());
        user.setTel(usuario.getTel());
        user.setCreadoEl(usuario.getCreadoEl());
        user.setEnable(usuario.getEnable());
        user.setUsuarioRoles(usuario.getUsuarioRoles());
        user.setEmail(usuario.getEmail());
        user.setNombre(usuario.getNombre());

        Usuario actualizado = usuarioRepo.save(user);
        return actualizado;
    }
}
