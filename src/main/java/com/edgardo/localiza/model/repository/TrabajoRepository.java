package com.edgardo.localiza.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgardo.localiza.model.entity.Trabajo;

@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Integer>{
	List<Trabajo> findByTipoTrabajoTipoTrabajo(String tipo);
}
