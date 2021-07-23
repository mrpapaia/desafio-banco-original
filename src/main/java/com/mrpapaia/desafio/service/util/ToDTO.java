package com.mrpapaia.desafio.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.mrpapaia.desafio.dto.EdgeDTO;
import com.mrpapaia.desafio.dto.GraphDTO;
import com.mrpapaia.desafio.dto.PathDTO;
import com.mrpapaia.desafio.dto.RouteDTO;
import com.mrpapaia.desafio.dto.RoutesDTO;
import com.mrpapaia.desafio.model.Edge;
import com.mrpapaia.desafio.model.Graph;
import com.mrpapaia.desafio.model.Vertex;

public class ToDTO {
	public static GraphDTO graphToDTO(Graph graph) {
		GraphDTO newDTO = new GraphDTO();
		newDTO.setId(graph.getKey());
		for (Edge edge : graph.getListEdge()) {
			newDTO.getData()
					.add(new EdgeDTO(edge.getSource().getName(), edge.getTarget().getName(), edge.getDistance()));
		}
		return newDTO;
	}

	public static RoutesDTO routesToDTO(List<Stack<Vertex>> paths) {
		RoutesDTO newDTO = new RoutesDTO();
		for (Stack<Vertex> vertex : paths) {
			newDTO.getRoutes().add(new RouteDTO(concatPaths(vertex), vertex.size() - 1));
		}
		return newDTO;
	}

	public static PathDTO pathToDTO(List<Vertex> paths) {
		PathDTO newDTO = new PathDTO();
		newDTO.setPath(new ArrayList<String>());
		for (Vertex vertex : paths) {
			newDTO.getPath().add(vertex.getName());
		}
		newDTO.setDistance(GraphUtil.getDistance(paths));
		return newDTO;
	}
	
	public static String concatPaths(List<Vertex> paths) {

		String pathSrt = "";

		for (Vertex v : paths) {
			pathSrt += v.getName();
		}

		return pathSrt;
	}

}
