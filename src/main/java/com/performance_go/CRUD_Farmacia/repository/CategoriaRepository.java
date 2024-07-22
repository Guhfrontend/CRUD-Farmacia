package com.performance_go.CRUD_Farmacia.repository;

import com.performance_go.CRUD_Farmacia.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findAllByCategoriaContainingIgnoreCase(@Param("categoria") String categoria);
}
