package com.mrpapaia.desafio.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class PathDTO {
	private List<String> path;
	private Integer distance;

	public PathDTO() {
		
	}

	public PathDTO(List<String> path, Integer distance) {
		super();
		this.path = path;
		this.distance = distance;
	}

	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	
	

}
