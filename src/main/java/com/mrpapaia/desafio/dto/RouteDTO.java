package com.mrpapaia.desafio.dto;

public class RouteDTO {
	private String route;
	private Integer stops;
	public RouteDTO() {
		
	}
	public RouteDTO(String route, Integer stops) {
		super();
		this.route = route;
		this.stops = stops;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public Integer getStops() {
		return stops;
	}
	public void setStops(Integer stops) {
		this.stops = stops;
	}
	@Override
	public String toString() {
		return "RouteDTO [route=" + route + ", stops=" + stops + "]";
	}
	
	

}
