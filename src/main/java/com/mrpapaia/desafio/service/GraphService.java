package com.mrpapaia.desafio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrpapaia.desafio.controller.exception.ResourceNotFoundException;
import com.mrpapaia.desafio.dto.EdgeDTO;
import com.mrpapaia.desafio.dto.GraphDTO;
import com.mrpapaia.desafio.model.Edge;
import com.mrpapaia.desafio.model.Graph;
import com.mrpapaia.desafio.model.Vertex;
import com.mrpapaia.desafio.repository.GraphRepository;
import com.mrpapaia.desafio.service.util.GraphUtil;
@Service
public class GraphService {
	@Autowired
	private GraphRepository graphRepository;
	
	
	public GraphService() {		
	}
	
	public Long addGraph(GraphDTO graphDTO) {
		 Graph graph=new Graph();
		 GraphUtil.setListVertex(graph,graphDTO.getData());
		 GraphUtil.setListEdge(graph,graphDTO.getData());
		graphRepository.save(graph);
		return graph.getKey();
	}

	
	public Graph findGraphById(Long id) {
		Graph graph=graphRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Pessoa Fisica não encontradao"));
		return graph;
	}	
}
