package com.course.practice.repo;

import com.course.practice.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface RolRepo extends JpaRepository<Rol, Integer> {

    @Query(value = "select * from rol where id = :id", nativeQuery = true)
    Rol obtenerPorId(@RequestParam(name = "id") Integer id);
}
