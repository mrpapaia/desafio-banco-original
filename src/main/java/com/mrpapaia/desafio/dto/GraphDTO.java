package com.mrpapaia.desafio.dto;

import java.util.ArrayList;
import java.util.List;

public class GraphDTO {
	private Long id;
	private List<EdgeDTO> data;
	
	
	public GraphDTO() {
		data= new ArrayList<EdgeDTO>();
	}
	public GraphDTO(Long id, List<EdgeDTO> data) {
		
		this.id = id;
		this.data = data;
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
	
	
	
}
