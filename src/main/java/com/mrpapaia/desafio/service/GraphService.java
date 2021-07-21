package com.mrpapaia.desafio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrpapaia.desafio.controller.exception.ResourceNotFoundException;
import com.mrpapaia.desafio.dto.EdgeDTO;
import com.mrpapaia.desafio.dto.GraphDTO;
import com.mrpapaia.desafio.model.Edge;
import com.mrpapaia.desafio.model.Graph;
import com.mrpapaia.desafio.model.Vertex;
import com.mrpapaia.desafio.repository.GraphRepository;

@Service
public class GraphService {
	private ObjectMapper mapper = new ObjectMapper();
	static final String ROOT_URI = "http://localhost:8080";
	@Autowired
	private GraphRepository graphRepository;
	
	
	public GraphService() {
		
	}
	public Long addGraph(GraphDTO graphDTO) {
		 Graph graph=new Graph();
		setListVertex(graph,graphDTO.getData());
		setListEdge(graph,graphDTO.getData());
		graphRepository.save(graph);
		return graph.getKey();
	}

	
	public Graph findGraphById(Long id) {
		Graph graph=graphRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Pessoa Fisica não encontradao"));
		
		
		return graph;
	}
	public void setListVertex(Graph graph,List<EdgeDTO> edgeDTOList) {

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

	public Boolean vertexExist(Graph graph,Vertex newVertex) {
		for (Vertex vertex : graph.getListVertex()) {
			if (vertex.getName().equals(newVertex.getName())) {
				return true;
			}
		}
		return false;
	}

	public void setListEdge(Graph graph,List<EdgeDTO> edgeDTOList) {
		List<Edge> newListEdge = new ArrayList<Edge>();
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

	public Boolean edgeExist(Graph graph, Edge newEdge) {
		for (Edge edge : graph.getListEdge()) {
			if (edge.getSource().getName().equals(newEdge.getSource().getName())
					&& edge.getTarget().getName().equals(newEdge.getTarget().getName())
					&& edge.getDistance().equals(newEdge.getDistance())) {
				return true;
			}
		}
		return false;
	}
	
	public Vertex getVertexByName(Graph graph,String vertexName) {
		for (Vertex vertex: graph.getListVertex()) {
			if (vertex.getName().equals(vertexName)) {
				return vertex;
			}
		}
		return null;
	}
	
	
}
