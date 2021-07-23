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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((listEdge == null) ? 0 : listEdge.hashCode());
		result = prime * result + ((listVertex == null) ? 0 : listVertex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Graph other = (Graph) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (listEdge == null) {
			if (other.listEdge != null)
				return false;
		} else if (!listEdge.equals(other.listEdge))
			return false;
		if (listVertex == null) {
			if (other.listVertex != null)
				return false;
		} else if (!listVertex.equals(other.listVertex))
			return false;
		return true;
	}

	

}
