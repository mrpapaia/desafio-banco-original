package com.mrpapaia.desafio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="tb_graph")
public class Graph {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long key;
	 @OneToMany(cascade=CascadeType.PERSIST)
	    @JoinColumn(nullable = true)
	private List<Vertex> listVertex;
	 @OneToMany(cascade=CascadeType.PERSIST)
	    @JoinColumn(nullable = true)
	private List<Edge> listEdge;

	public Graph() {
		this.listVertex = new ArrayList<Vertex>();
		this.listEdge = new ArrayList<Edge>();
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
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

	

}
