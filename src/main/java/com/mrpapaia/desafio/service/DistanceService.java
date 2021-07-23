package com.mrpapaia.desafio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mrpapaia.desafio.dto.GraphDTO;
import com.mrpapaia.desafio.dto.PathDTO;
import com.mrpapaia.desafio.model.Edge;
import com.mrpapaia.desafio.model.Graph;
import com.mrpapaia.desafio.model.Vertex;
import com.mrpapaia.desafio.service.util.GraphUtil;
import com.mrpapaia.desafio.service.util.ToDTO;

@Service
public class DistanceService {

	public PathDTO getDistanceForPath(GraphDTO graphDTO) {
		PathDTO pathDTO= new PathDTO();
		if(startEqualEnd(graphDTO.getPath())) {
			pathDTO.setDistance(0);
			return pathDTO;
		}
		Graph graph = new Graph();
		GraphUtil.setListVertex(graph, graphDTO.getData());
		GraphUtil.setListEdge(graph, graphDTO.getData());		
		List<Vertex> path = new ArrayList<Vertex>();
		for (String vertexName : graphDTO.getPath()) {
			path.add(GraphUtil.getVertexByName(graph, vertexName));
		}
		if(!GraphUtil.getAllRoutesBetweenTwoVertex(path.get(0), path.get(path.size()-1),null).contains(path)) {
			pathDTO.setDistance(-1);
			return pathDTO;
		}		
				
		pathDTO.setDistance(getDistance(path));
		return pathDTO;
	}
	private boolean startEqualEnd(List<String> path) {
		return path.get(0).equals(path.get(path.size()-1));
	}
	

	public PathDTO getMinimalPath(GraphDTO graphDTO, String town1, String town2) {
		PathDTO pathDTO= new PathDTO();
		if(town1.equals(town2)) {
			pathDTO.setDistance(0);
			pathDTO.setPath(new ArrayList<String>());
			pathDTO.getPath().add(town1);
			return pathDTO;
		}
		Graph graph = new Graph();
		GraphUtil.setListVertex(graph, graphDTO.getData());
		GraphUtil.setListEdge(graph, graphDTO.getData());
		
		List<Vertex> minPath=GraphUtil.getMinimalRouteBetweenTwoVertex(GraphUtil.getVertexByName(graph, town1),
				GraphUtil.getVertexByName(graph, town2));
		if(minPath.isEmpty()) {
			pathDTO.setDistance(0);
			return pathDTO;
		}
	return ToDTO.pathToDTO(minPath);

	}

	public Integer getDistance(List<Vertex> path) {

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
}
