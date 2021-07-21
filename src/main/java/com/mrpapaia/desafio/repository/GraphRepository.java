package com.mrpapaia.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrpapaia.desafio.model.Graph;

public interface GraphRepository extends JpaRepository<Graph, Long>{

}
