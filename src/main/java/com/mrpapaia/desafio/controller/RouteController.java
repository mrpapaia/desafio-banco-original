package com.mrpapaia.desafio.controller;

import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mrpapaia.desafio.controller.exception.ResourceNotFoundException;
import com.mrpapaia.desafio.controller.util.ToDTO;
import com.mrpapaia.desafio.dto.GraphDTO;
import com.mrpapaia.desafio.dto.RoutesDTO;
import com.mrpapaia.desafio.model.Graph;
import com.mrpapaia.desafio.model.Vertex;
import com.mrpapaia.desafio.service.GraphService;
import com.mrpapaia.desafio.service.RouteService;
import com.mrpapaia.desafio.controller.util.ToDTO;

@RestController
@RequestMapping("/routes")
public class RouteController {

	@Autowired
	private RouteService routeService;
	@Autowired
	private GraphService graphService;

	@PostMapping(value = "/from/{town1}/to/{town2}")
	public ResponseEntity<RoutesDTO> encontrarCaminhos(@RequestBody GraphDTO data, @PathVariable String town1,
			@PathVariable String town2, @RequestParam(value = "maxStops", required = false) Integer maxStops) {

		return ResponseEntity.ok(
				ToDTO.routesToDTO(routeService.getAllRoutesBetweenToVertexInNewGraph(data, town1, town2, maxStops)));
	}

	@PostMapping(value = "/{graphId}/from/{town1}/to/{town2}")
	public ResponseEntity<RoutesDTO> getGraphById(@PathVariable Long graphId, @PathVariable String town1,
			@PathVariable String town2, @RequestParam(value = "maxStops", required = false) Integer maxStops) {
		Graph graph = null;
		try {
			graph = graphService.findGraphById(graphId);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(
				ToDTO.routesToDTO(routeService.getAllRoutesBetweenToVertexInExistedGraph(graph, town1, town2, maxStops)));

	}

}
