package com.course.practice.serviceImpl;

import com.course.practice.entity.Rol;
import com.course.practice.repo.RolRepo;
import com.course.practice.service.RolService;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService {
    private final RolRepo rolRepo;
    public RolServiceImpl(RolRepo rolRepo) {
        this.rolRepo = rolRepo;
    }

    @Override
    public Rol obtenerPorId(Integer id){
        Rol rol = rolRepo.obtenerPorId(id);
        return rol;
    }
}
