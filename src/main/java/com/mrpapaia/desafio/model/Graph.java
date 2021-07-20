package com.mrpapaia.desafio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.xml.bind.v2.model.core.ID;
@Entity
@Table(name="tb_graph")
public class Graph {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID key;
	 @OneToMany
	    @JoinColumn(nullable = true)
	private List<Vertex> listVertex;
	 @OneToMany
	    @JoinColumn(nullable = true)
	private List<Edge> listEdge;

	public Graph() {
		this.listVertex = new ArrayList<Vertex>();
		this.listEdge = new ArrayList<Edge>();
	}

	public ID getKey() {
		return key;
	}

	public void setKey(ID key) {
		this.key = key;
	}

	public List<Vertex> getListVertex() {
		return listVertex;
	}

	public void setListVertex(List<Vertex> listVertex) {
		this.listVertex = listVertex;
	}

	public List<Edge> getListEdge() {
		return listEdge;
	}

	public void setListEdge(List<Edge> listEdge) {
		this.listEdge = listEdge;
	}

	public void addVertex(Vertex newVertex) {
		if(getVertexByName(newVertex)==null)
		listVertex.add(newVertex);

	}

	public void addEdge(Integer distance, Vertex source, Vertex target) {
		Vertex sourceInList = getVertexByName(source);
		Vertex targetInList = getVertexByName(target);
		Edge newEdged = new Edge(sourceInList, targetInList, distance);
		sourceInList.addOutputEdge(newEdged);
		targetInList.addInputEdge(newEdged);
		listEdge.add(newEdged);

	}

	public Vertex getVertex(Vertex newVertex) {
		for (int i = 0; i < listVertex.size(); i++) {
			if (listVertex.get(i).equals(newVertex)) {
				return listVertex.get(i);
			}
		}
		return null;
	}
	public Vertex getVertexByName(Vertex newVertex) {
		for (int i = 0; i < listVertex.size(); i++) {
			if (listVertex.get(i).getName().equals(newVertex.getName())) {
				return listVertex.get(i);
			}
		}
		return null;
	}

}
