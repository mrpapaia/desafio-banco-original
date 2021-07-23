package com.mrpapaia.desafio.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrpapaia.desafio.controller.exception.ResourceNotFoundException;
import com.mrpapaia.desafio.dto.GraphDTO;

import com.mrpapaia.desafio.model.Graph;

import com.mrpapaia.desafio.repository.GraphRepository;
import com.mrpapaia.desafio.service.util.GraphUtil;
import com.mrpapaia.desafio.service.util.ToDTO;
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

	
	public GraphDTO findGraphById(Long id) {
		Graph graph=graphRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("não encontrado"));
		return ToDTO.graphToDTO(graph);
	}	
}
