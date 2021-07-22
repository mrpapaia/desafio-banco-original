package com.mrpapaia.desafio.controller.util;

import com.mrpapaia.desafio.dto.EdgeDTO;
import com.mrpapaia.desafio.dto.GraphDTO;
import com.mrpapaia.desafio.model.Edge;
import com.mrpapaia.desafio.model.Graph;

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
}
