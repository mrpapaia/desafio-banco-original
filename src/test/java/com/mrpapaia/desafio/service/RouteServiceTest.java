package com.mrpapaia.desafio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrpapaia.desafio.dto.GraphDTO;

import com.mrpapaia.desafio.dto.RoutesDTO;

@SpringBootTest
public class RouteServiceTest {
	@Autowired
	private RouteService routeService;
	private GraphDTO graphDTO;
	

	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		graphDTO = mapper.readValue(graphJson, GraphDTO.class);
	}

	@Test
	@DisplayName(value = "Test method getAllRoutesBetweenToVertexInGraph where route A to C maxStops =4")
	void getAllRoutesBetweenToVertexInGraphAtoC() {

		RoutesDTO routesDTOResponse = routeService.getAllRoutesBetweenTwoVertexInGraph(graphDTO, "A", "B", 4);
		assertEquals(4, routesDTOResponse.getRoutes().size());
	}
	@Test
	@DisplayName(value = "Test method getAllRoutesBetweenToVertexInGraph where route C to C maxStops =3")
	void getAllRoutesBetweenToVertexInGraphCtoC() {

		RoutesDTO routesDTOResponse = routeService.getAllRoutesBetweenTwoVertexInGraph(graphDTO, "C", "C", 3);
		assertEquals(1, routesDTOResponse.getRoutes().size());
	}
	private String graphJson = "{\n" + "	\"data\": [\n" + "		{\n" + "			\"source\": \"A\",\n"
			+ "			\"target\": \"B\",\n" + "			\"distance\": 5\n" + "		},\n" + "		{\n"
			+ "			\"source\": \"B\",\n" + "			\"target\": \"C\",\n" + "			\"distance\": 4\n"
			+ "		},\n" + "		{\n" + "			\"source\": \"C\",\n" + "			\"target\": \"D\",\n"
			+ "			\"distance\": 8\n" + "		},\n" + "		{\n" + "			\"source\": \"D\",\n"
			+ "			\"target\": \"C\",\n" + "			\"distance\": 8\n" + "		},\n" + "		{\n"
			+ "			\"source\": \"D\",\n" + "			\"target\": \"E\",\n" + "			\"distance\": 6\n"
			+ "		},\n" + "		{\n" + "			\"source\": \"A\",\n" + "			\"target\": \"D\",\n"
			+ "			\"distance\": 5\n" + "		},\n" + "		{\n" + "			\"source\": \"C\",\n"
			+ "			\"target\": \"E\",\n" + "			\"distance\": 2\n" + "		},\n" + "		{\n"
			+ "			\"source\": \"E\",\n" + "			\"target\": \"B\",\n" + "			\"distance\": 3\n"
			+ "		},\n" + "		{\n" + "			\"source\": \"A\",\n" + "			\"target\": \"E\",\n"
			+ "			\"distance\": 7\n" + "		}\n" + "	]\n" + "}";
}
