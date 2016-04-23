package com.edgardo.localiza.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.edgardo.localiza.model.entity.Tecnicos;

@Repository
public interface TecnicosRepository extends JpaRepository<Tecnicos, Integer>, JpaSpecificationExecutor<Tecnicos>{

}
