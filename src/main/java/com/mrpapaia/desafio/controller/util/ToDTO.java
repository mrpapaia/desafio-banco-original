package com.mrpapaia.desafio.controller.util;

import java.util.List;
import java.util.Stack;

import com.mrpapaia.desafio.dto.EdgeDTO;
import com.mrpapaia.desafio.dto.GraphDTO;
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
			newDTO.getRoutes().add(new RouteDTO(Util.concatPaths(vertex), vertex.size() - 1));
		}
		return newDTO;
	}

}
