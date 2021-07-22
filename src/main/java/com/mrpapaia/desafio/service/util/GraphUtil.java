package com.mrpapaia.desafio.service.util;

import java.util.List;

import com.mrpapaia.desafio.dto.EdgeDTO;
import com.mrpapaia.desafio.model.Edge;
import com.mrpapaia.desafio.model.Graph;
import com.mrpapaia.desafio.model.Vertex;

public class GraphUtil {
	public static void setListVertex(Graph graph,List<EdgeDTO> edgeDTOList) {
		Vertex newVertex;
		for (EdgeDTO edgeDto : edgeDTOList) {
			newVertex = new Vertex(edgeDto.getSource());
			if (!vertexExist(graph,newVertex)) {
				graph.getListVertex().add(newVertex);
			}
			newVertex = new Vertex(edgeDto.getTarget());
			if (!vertexExist(graph,newVertex)) {
				graph.getListVertex().add(newVertex);
			}
		}

	}

	public static Boolean vertexExist(Graph graph,Vertex newVertex) {
		for (Vertex vertex : graph.getListVertex()) {
			if (vertex.getName().equals(newVertex.getName())) {
				return true;
			}
		}
		return false;
	}

	public static void setListEdge(Graph graph,List<EdgeDTO> edgeDTOList) {		
		Edge newEdge;
		Vertex source;
		Vertex target;
		for (EdgeDTO edgeDto : edgeDTOList) {
			source= getVertexByName(graph,edgeDto.getSource());
			target= getVertexByName(graph,edgeDto.getTarget());
			
			if(source!=null && target!=null) {
				
				newEdge = new Edge(source,target,edgeDto.getDistance());
				if(!edgeExist(graph,newEdge)) {
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
	
	public static Vertex getVertexByName(Graph graph,String vertexName) {
		for (Vertex vertex: graph.getListVertex()) {
			if (vertex.getName().equals(vertexName)) {
				return vertex;
			}
		}
		return null;
	}
}
