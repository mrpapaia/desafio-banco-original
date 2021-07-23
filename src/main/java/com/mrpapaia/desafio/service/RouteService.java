package com.mrpapaia.desafio.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Service;

import com.mrpapaia.desafio.dto.GraphDTO;
import com.mrpapaia.desafio.dto.RoutesDTO;
import com.mrpapaia.desafio.model.Graph;
import com.mrpapaia.desafio.model.Vertex;
import com.mrpapaia.desafio.service.util.GraphUtil;
import com.mrpapaia.desafio.service.util.ToDTO;

@Service
public class RouteService {

	public RoutesDTO getAllRoutesBetweenToVertexInGraph(GraphDTO graphDTO, String town1, String town2,
			Integer maxStops) {
		Graph graph = new Graph();
		GraphUtil.setListVertex(graph, graphDTO.getData());
		GraphUtil.setListEdge(graph, graphDTO.getData());
		
		return ToDTO.routesToDTO( getAllRoutesBetweenToVertex(GraphUtil.getVertexByName(graph, town1),
				GraphUtil.getVertexByName(graph, town2), maxStops));

	}



	public List<Stack<Vertex>> getAllRoutesBetweenToVertex(Vertex begin, Vertex end, Integer maxStops) {
		Stack<Vertex> visited = new Stack<Vertex>();
		List<Stack<Vertex>> paths = new ArrayList<Stack<Vertex>>();
		visited.add(begin);
		GraphUtil.dfs(visited, end, paths);
		if (maxStops != null)
			paths.removeIf(p -> p.size() > maxStops + 1);
		if(paths.isEmpty()) {
			paths.add(visited);
		}
		return paths;

	}

	
	
	

}
