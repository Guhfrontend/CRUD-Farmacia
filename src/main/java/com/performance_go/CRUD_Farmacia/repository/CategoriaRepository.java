package com.performance_go.CRUD_Farmacia.repository;

import com.performance_go.CRUD_Farmacia.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
