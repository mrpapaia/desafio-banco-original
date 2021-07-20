package com.mrpapaia.desafio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.xml.bind.v2.model.core.ID;
@Entity
@Table(name="tb_vertex")
public class Vertex {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private ID key;
	@Column(name = "name")
	private String name;
	
	@OneToMany
    @Nullable
	private List<Edge> listInputEdge;
	@OneToMany
    @Nullable
	private List<Edge> listOutputEdge;

	public Vertex(String name) {
		this.name = name;
		this.listInputEdge = new ArrayList<Edge>();
		this.listOutputEdge = new ArrayList<Edge>();
	}

	public ID getKey() {
		return key;
	}

	public void setKey(ID key) {
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
		return "Vertex [name=" + name + ", listInputEdge=" + listInputEdge + ", listOutputEdge=" + listOutputEdge + "]";
	}
	
	

}
