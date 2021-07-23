package com.mrpapaia.desafio.service.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.mrpapaia.desafio.dto.EdgeDTO;
import com.mrpapaia.desafio.dto.GraphDTO;
import com.mrpapaia.desafio.model.Edge;
import com.mrpapaia.desafio.model.Graph;
import com.mrpapaia.desafio.model.Vertex;

public class GraphUtil {
	public static void setListVertex(Graph graph, List<EdgeDTO> edgeDTOList) {
		Vertex newVertex;
		for (EdgeDTO edgeDto : edgeDTOList) {
			newVertex = new Vertex(edgeDto.getSource());
			if (!vertexExist(graph, newVertex)) {
				graph.getListVertex().add(newVertex);
			}
			newVertex = new Vertex(edgeDto.getTarget());
			if (!vertexExist(graph, newVertex)) {
				graph.getListVertex().add(newVertex);
			}
		}
	}

	public static Boolean vertexExist(Graph graph, Vertex newVertex) {
		for (Vertex vertex : graph.getListVertex()) {
			if (vertex.getName().equals(newVertex.getName())) {
				return true;
			}
		}
		return false;
	}

	public static void setListEdge(Graph graph, List<EdgeDTO> edgeDTOList) {
		Edge newEdge;
		Vertex source;
		Vertex target;
		for (EdgeDTO edgeDto : edgeDTOList) {
			source = getVertexByName(graph, edgeDto.getSource());
			target = getVertexByName(graph, edgeDto.getTarget());
			if (source != null && target != null) {
				newEdge = new Edge(source, target, edgeDto.getDistance());
				if (!edgeExist(graph, newEdge)) {
					source.addOutputEdge(newEdge);
					target.addInputEdge(newEdge);
					graph.getListEdge().add(newEdge);
				}
			}
		}
	}

	public static Boolean edgeExist(Graph graph, Edge newEdge) {
		for (Edge edge : graph.getListEdge()) {
			if (edge.getSource().getName().equals(newEdge.getSource().getName())
					&& edge.getTarget().getName().equals(newEdge.getTarget().getName())
					&& edge.getDistance().equals(newEdge.getDistance())) {
				return true;
			}
		}
		return false;
	}

	public static Vertex getVertexByName(Graph graph, String vertexName) {
		for (Vertex vertex : graph.getListVertex()) {
			if (vertex.getName().equals(vertexName)) {
				return vertex;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static void dfs(Stack<Vertex> visited, Vertex end, List<Stack<Vertex>> paths) {
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

	public static List<Vertex> adjacentVertex(Vertex vertex) {
		List<Vertex> adjacentVertex = new LinkedList<Vertex>();
		for (Edge edge : vertex.getListOutputEdge()) {
			adjacentVertex.add(edge.getTarget());
		}
		return adjacentVertex;
	}

	public static List<Vertex> bfs(Vertex start) {
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

	public static List<Stack<Vertex>> getAllRoutesBetweenTwoVertex(Vertex start, Vertex stop, Integer maxStops) {
		Stack<Vertex> visited = new Stack<Vertex>();
		List<Stack<Vertex>> paths = new ArrayList<Stack<Vertex>>();
		visited.add(start);
		GraphUtil.dfs(visited, stop, paths);
		if (maxStops != null)
			paths.removeIf(p -> p.size() > maxStops + 1);

		return paths;
	}

	public static Integer getDistance(List<Vertex> path) {
		Integer distance = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			Vertex v1 = path.get(i);
			Vertex v2 = path.get(i + 1);
			for (Edge edge : v1.getListOutputEdge()) {
				if (edge.getTarget().equals(v2)) {
					distance += edge.getDistance();
					break;
				}
			}
		}
		return distance;
	}

	public static List<Vertex> getMinimalRouteBetweenTwoVertex(Vertex start, Vertex stop) {
		List<Stack<Vertex>> listPaths = getAllRoutesBetweenTwoVertex(start, stop, null);
		List<Vertex> minPath = listPaths.get(0);
		Integer minPathSize = getDistance(listPaths.get(0));
		Integer pathSize;
		for (List<Vertex> path : listPaths) {
			pathSize = getDistance(path);
			if (pathSize < minPathSize) {
				minPath = path;
				minPathSize = pathSize;
			}
		}
		return minPath;
	}

	public static Graph initGraph(GraphDTO graphDTO) {
		Graph graph = new Graph();
		setListVertex(graph, graphDTO.getData());
		setListEdge(graph, graphDTO.getData());
		return graph;
	}
}
