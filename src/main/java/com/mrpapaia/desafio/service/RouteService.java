package com.mrpapaia.desafio.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mrpapaia.desafio.dto.GraphDTO;
import com.mrpapaia.desafio.dto.RouteDTO;
import com.mrpapaia.desafio.model.Edge;
import com.mrpapaia.desafio.model.Graph;
import com.mrpapaia.desafio.model.Vertex;
import com.mrpapaia.desafio.service.util.GraphUtil;

@Service
public class RouteService {

	public String getRoutes(GraphDTO graphDTO, String town1, String town2, Integer maxStops) {

		Graph graph = new Graph();
		GraphUtil.setListVertex(graph, graphDTO.getData());
		GraphUtil.setListEdge(graph, graphDTO.getData());
		return teste(GraphUtil.getVertexByName(graph, town1), GraphUtil.getVertexByName(graph, town2));

	}

	public String teste(Vertex begin, Vertex end) {
		List<Vertex> listVertex = bfs(begin);

		LinkedList<Vertex> visited = new LinkedList<Vertex>();
		List<LinkedList<Vertex>> paths = new LinkedList<>();
		visited.add(begin);
		depthFirst(visited, end, paths);
		System.out.println(paths);
		return null;

	}

	private void depthFirst(LinkedList<Vertex> visited, Vertex end, List<LinkedList<Vertex>> paths) {
		List<Vertex> nodes = adjacentVertex(visited.getLast());

		for (Vertex node : nodes) {

			if (visited.contains(node)) {
				continue;
			}

			if (node.equals(end)) {
				visited.add(node);
				paths.add((LinkedList<Vertex>) visited.clone());
				visited.removeLast();
				break;
			}
		}
		for (Vertex node : nodes) {
			if (visited.contains(node) || node.equals(end)) {
				continue;
			}

			visited.addLast(node);
			;
			depthFirst(visited, end, paths);
			visited.removeLast();
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
