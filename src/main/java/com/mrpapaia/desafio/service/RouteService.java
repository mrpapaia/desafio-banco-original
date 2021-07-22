package com.mrpapaia.desafio.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Service;

import com.mrpapaia.desafio.dto.GraphDTO;
import com.mrpapaia.desafio.dto.RoutesDTO;
import com.mrpapaia.desafio.model.Edge;
import com.mrpapaia.desafio.model.Graph;
import com.mrpapaia.desafio.model.Vertex;
import com.mrpapaia.desafio.service.util.GraphUtil;

@Service
public class RouteService {

	public List<Stack<Vertex>> getAllRoutesBetweenToVertexInNewGraph(GraphDTO graphDTO, String town1, String town2,
			Integer maxStops) {
		Graph graph = new Graph();
		GraphUtil.setListVertex(graph, graphDTO.getData());
		GraphUtil.setListEdge(graph, graphDTO.getData());
		return getAllRoutesBetweenToVertex(GraphUtil.getVertexByName(graph, town1),
				GraphUtil.getVertexByName(graph, town2), maxStops);

	}
	public List<Stack<Vertex>> getAllRoutesBetweenToVertexInExistedGraph(Graph graph, String town1, String town2,
			Integer maxStops) {	
		return getAllRoutesBetweenToVertex(GraphUtil.getVertexByName(graph, town1),
				GraphUtil.getVertexByName(graph, town2), maxStops);

	}
	public List<Stack<Vertex>> getAllRoutesBetweenToVertex(Vertex begin, Vertex end, Integer maxStops) {
		Stack<Vertex> visited = new Stack<Vertex>();
		List<Stack<Vertex>> paths = new ArrayList<Stack<Vertex>>();
		visited.add(begin);
		dfs(visited, end, paths);
		if (maxStops != null)
			paths.removeIf(p -> p.size() > maxStops + 1);		
		return paths;

	}

	private void dfs(Stack<Vertex> visited, Vertex end, List<Stack<Vertex>> paths) {
		List<Vertex> vertexList = adjacentVertex(visited.get(visited.size() - 1));

		for (Vertex vertex : vertexList) {

			if (visited.contains(vertex)) {
				continue;
			}

			if (vertex.equals(end)) {
				visited.add(vertex);
				paths.add((Stack<Vertex>) visited.clone());
				visited.pop();
				break;
			}
		}
		for (Vertex vertex : vertexList) {
			if (visited.contains(vertex) || vertex.equals(end)) {
				continue;
			}

			visited.push(vertex);
			dfs(visited, end, paths);
			visited.pop();
		}
	}

	public List<Vertex> adjacentVertex(Vertex vertex) {
		List<Vertex> adjacentVertex = new LinkedList<Vertex>();
		for (Edge edge : vertex.getListOutputEdge()) {
			adjacentVertex.add(edge.getTarget());
		}
		return adjacentVertex;
	}

	public List<Vertex> bfs(Vertex start) {
		List<Vertex> listVertex = new ArrayList<Vertex>();
		List<Vertex> visited = new ArrayList<Vertex>();
		List<Vertex> queue = new ArrayList<Vertex>();
		visited.add(start);
		queue.add(start);
		while (queue.size() > 0) {
			for (Edge edge : queue.get(0).getListOutputEdge()) {
				Vertex next = edge.getTarget();
				if (!visited.contains(next)) {
					listVertex.add(next);
					visited.add(next);
					queue.add(next);
				}
			}

			queue.remove(0);
		}
		return listVertex;

	}

}
