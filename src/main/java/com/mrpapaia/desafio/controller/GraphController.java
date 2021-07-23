package com.mrpapaia.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.mrpapaia.desafio.controller.exception.ResourceNotFoundException;
import com.mrpapaia.desafio.dto.GraphDTO;
import com.mrpapaia.desafio.dto.PathDTO;

import com.mrpapaia.desafio.dto.RoutesDTO;

import com.mrpapaia.desafio.service.DistanceService;
import com.mrpapaia.desafio.service.GraphService;
import com.mrpapaia.desafio.service.RouteService;

@RestController
@RequestMapping("/")
public class GraphController {
	@Autowired
	private GraphService graphService;

	@Autowired
	private DistanceService distanceService;

	@Autowired
	private RouteService routeService;

	@PostMapping(value = "/graph")
	public ResponseEntity<GraphDTO> addNewGraph(@RequestBody GraphDTO graphDTO) {

		GraphDTO responseBody = graphService.addGraph(graphDTO);
		return new ResponseEntity<GraphDTO>(responseBody, HttpStatus.CREATED);
	}

	@GetMapping(value = "/graph/{graphId}")
	public ResponseEntity<GraphDTO> getGraphById(@PathVariable Long graphId) {
		GraphDTO graphDTO = null;
		try {
			graphDTO = graphService.findGraphById(graphId);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(graphDTO);
	}

	@PostMapping(value = "/routes/from/{town1}/to/{town2}")
	public ResponseEntity<RoutesDTO> getAllPaths(@RequestBody GraphDTO graphDTO, @PathVariable String town1,
			@PathVariable String town2, @RequestParam(value = "maxStops", required = false) Integer maxStops) {

		RoutesDTO reponseBody = routeService.getAllRoutesBetweenTwoVertexInGraph(graphDTO, town1, town2, maxStops);

		return ResponseEntity.ok(reponseBody);
	}

	@PostMapping(value = "/routes/{graphId}/from/{town1}/to/{town2}")
	public ResponseEntity<RoutesDTO> getAllPathsByGraphId(@PathVariable Long graphId, @PathVariable String town1,
			@PathVariable String town2, @RequestParam(value = "maxStops", required = false) Integer maxStops) {
		GraphDTO graphDTO = null;
		try {
			graphDTO = graphService.findGraphById(graphId);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		RoutesDTO reponseBody = routeService.getAllRoutesBetweenTwoVertexInGraph(graphDTO, town1, town2, maxStops);
		return ResponseEntity.ok(reponseBody);

	}

	@PostMapping(value = "/distance")
	public ResponseEntity<PathDTO> findPathSize(@RequestBody GraphDTO graphDTO) {
		PathDTO responseBody = distanceService.getDistanceForPath(graphDTO);
		return ResponseEntity.ok(responseBody);
	}

	@PostMapping(value = "/distance/{graphId}")
	public ResponseEntity<PathDTO> findPathSizeByGraphId(@PathVariable Long graphId, @RequestBody PathDTO path) {
		GraphDTO graphDTO = null;
		try {
			graphDTO = graphService.findGraphById(graphId);
			graphDTO.setPath(path.getPath());
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		PathDTO reponseBody = distanceService.getDistanceForPath(graphDTO);
		return ResponseEntity.ok(reponseBody);
	}

	@PostMapping(value = "/distance/from/{town1}/to/{town2}")
	public ResponseEntity<PathDTO> getMinimalPath(@RequestBody GraphDTO data, @PathVariable String town1,
			@PathVariable String town2) {
		PathDTO reponseBody = distanceService.getMinimalPath(data, town1, town2);
		return ResponseEntity.ok(reponseBody);
	}

	@PostMapping(value = "/distance/{graphId}/from/{town1}/to/{town2}")
	public ResponseEntity<PathDTO> getMinimalPathByGraphId(@PathVariable Long graphId, @PathVariable String town1,
			@PathVariable String town2) {
		GraphDTO graphDTO = null;
		try {
			graphDTO = graphService.findGraphById(graphId);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		PathDTO reponseBody = distanceService.getMinimalPath(graphDTO, town1, town2);
		return ResponseEntity.ok(reponseBody);

	}

}
