package com.edgardo.localiza.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.edgardo.localiza.model.entity.Trabajo;

@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Integer>, QueryDslPredicateExecutor<Trabajo>{
	List<Trabajo> findByTipoTrabajoTipoTrabajo(String tipo);
}
