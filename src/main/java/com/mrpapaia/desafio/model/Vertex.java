package com.mrpapaia.desafio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.Nullable;


@Entity
@Table(name="tb_vertex")
public class Vertex implements Cloneable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long key;
	@Column(name = "name")
	private String name;
	
	@OneToMany(cascade=CascadeType.PERSIST)
    @Nullable
	private List<Edge> listInputEdge;
	@OneToMany(cascade=CascadeType.PERSIST)
    @Nullable
	private List<Edge> listOutputEdge;

	public Vertex() {
		
	}

	public Vertex(String name) {
		this.name = name;
		this.listInputEdge = new ArrayList<Edge>();
		this.listOutputEdge = new ArrayList<Edge>();
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Edge> getListInputEdge() {
		return listInputEdge;
	}

	public void setListInputEdge(List<Edge> listInputEdge) {
		this.listInputEdge = listInputEdge;
	}

	public List<Edge> getListOutputEdge() {
		return listOutputEdge;
	}

	public void setListOutputEdge(List<Edge> listOutputEdge) {
		this.listOutputEdge = listOutputEdge;
	}

	public void addInputEdge(Edge newEdge) {
		listInputEdge.add(newEdge);
	}

	public void addOutputEdge(Edge newEdge) {
		listOutputEdge.add(newEdge);
	}

	@Override
	public String toString() {
		return  name + "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((listInputEdge == null) ? 0 : listInputEdge.hashCode());
		result = prime * result + ((listOutputEdge == null) ? 0 : listOutputEdge.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Vertex other = (Vertex) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (listInputEdge == null) {
			if (other.listInputEdge != null)
				return false;
		} else if (!listInputEdge.equals(other.listInputEdge))
			return false;
		if (listOutputEdge == null) {
			if (other.listOutputEdge != null)
				return false;
		} else if (!listOutputEdge.equals(other.listOutputEdge))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
