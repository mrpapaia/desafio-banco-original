package com.mrpapaia.desafio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import com.sun.xml.bind.v2.model.core.ID;

@Entity
@Table(name = "tb_edge")
public class Edge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID key;	
	@OneToOne	
	private Vertex source;
	@OneToOne	
	private Vertex target;
	@Column(name = "distance")
	private Integer distance;

	public Edge(Vertex source, Vertex target, Integer distance) {
		super();
		this.source = source;
		this.target = target;
		this.distance = distance;
	}

	public ID getKey() {
		return key;
	}

	public void setKey(ID key) {
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
