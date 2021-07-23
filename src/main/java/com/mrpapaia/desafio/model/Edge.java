package com.mrpapaia.desafio.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "tb_edge")
public class Edge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long key;	
	@OneToOne	
	private Vertex source;
	@OneToOne	
	private Vertex target;
	@Column(name = "distance")
	private Integer distance;

	public Edge() {
		
	}

	public Edge(Vertex source, Vertex target, Integer distance) {
		
		this.source = source;
		this.target = target;
		this.distance = distance;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getTarget() {
		return target;
	}

	public void setTarget(Vertex target) {
		this.target = target;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Edge [source=" + source.getName() + ", target=" + target.getName() + " distance=" + distance + "]";
	}

}
