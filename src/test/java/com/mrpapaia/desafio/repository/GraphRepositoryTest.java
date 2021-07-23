package com.mrpapaia.desafio.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mrpapaia.desafio.model.Graph;
@SpringBootTest
class GraphRepositoryTest {
	@Autowired
	private GraphRepository graphRepository;
	

	@Test
	void insertGraph() {
		Graph graph = new Graph();
		
		graphRepository.save(graph);
		Integer countGraph =  (int) graphRepository.count();
		assertEquals(1, countGraph);
	}
	
	@Test
	void getGraph() {
		Graph graph = new Graph();		
		graphRepository.save(graph);
		Graph graphR = graphRepository.getOne((long) 1);	
		assertEquals(1, graphR.getKey());
	}

}
