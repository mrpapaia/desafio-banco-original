package com.mrpapaia.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.mrpapaia.desafio.controller.exception.ResourceNotFoundException;
import com.mrpapaia.desafio.dto.EdgeDTO;
import com.mrpapaia.desafio.dto.GraphDTO;
import com.mrpapaia.desafio.model.Edge;
import com.mrpapaia.desafio.model.Graph;
import com.mrpapaia.desafio.service.GraphService;
import com.mrpapaia.desafio.controller.util.ToDTO;

@RestController
@RequestMapping("/graph")
public class GraphController {
	@Autowired
	private GraphService graphService;

	@PostMapping
	public ResponseEntity<GraphDTO> addNewGraph(@RequestBody GraphDTO graphDTO) {
		graphDTO.setId(graphService.addGraph(graphDTO));
		return new ResponseEntity<GraphDTO>(graphDTO, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{graphId}")
	public ResponseEntity<GraphDTO> getGraphById(@PathVariable Long graphId) {
		Graph graph = null;
		try {
			graph = graphService.findGraphById(graphId);

		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(ToDTO.graphToDTO(graph));

	}

	
}
