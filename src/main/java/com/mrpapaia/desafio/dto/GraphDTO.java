package com.mrpapaia.desafio.dto;

import java.util.ArrayList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class GraphDTO {
	private Long id;

	private List<String> path;
	private List<EdgeDTO> data;
	private Integer distance;
	public GraphDTO() {
		data = new ArrayList<EdgeDTO>();
		path = new ArrayList<String>();
	}

	

	public GraphDTO(Long id, List<String> path, List<EdgeDTO> data, Integer distance) {
		
		this.id = id;
		this.path = path;
		this.data = data;
		this.distance = distance;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<EdgeDTO> getData() {
		return data;
	}

	public void setData(List<EdgeDTO> data) {
		this.data = data;
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



	@Override
	public String toString() {
		return "GraphDTO [id=" + id + ", data=" + data + "]";
	}

}
