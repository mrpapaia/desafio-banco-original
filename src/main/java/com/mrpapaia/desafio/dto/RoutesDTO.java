package com.mrpapaia.desafio.dto;

import java.util.ArrayList;
import java.util.List;

public class RoutesDTO {
		private List<RouteDTO> routes;

		public RoutesDTO( ){
			
			this.routes = new ArrayList<RouteDTO>();
		}
		public RoutesDTO(List<RouteDTO> routes) {
			super();
			this.routes = routes;
		}
		public List<RouteDTO> getRoutes() {
			return routes;
		}
		public void setRoutes(List<RouteDTO> routes) {
			this.routes = routes;
		}
		

}
