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
	
	

	public GraphService(GraphRepository graphRepository) {		
		this.graphRepository = graphRepository;
	}

	public GraphDTO addGraph(GraphDTO graphDTO) {
		Graph graph = GraphUtil.initGraph(graphDTO);
		GraphDTO responseGraph=ToDTO.graphToDTO(graphRepository.save(graph)) ;
		return responseGraph;
	}

	
	public GraphDTO findGraphById(Long id) {
		Graph graph=graphRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not Found"));
		return ToDTO.graphToDTO(graph);
	}	
}
