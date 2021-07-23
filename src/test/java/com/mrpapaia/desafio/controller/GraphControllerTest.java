package com.mrpapaia.desafio.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrpapaia.desafio.controller.exception.ResourceNotFoundException;
import com.mrpapaia.desafio.dto.GraphDTO;
import com.mrpapaia.desafio.dto.PathDTO;
import com.mrpapaia.desafio.dto.RoutesDTO;
import com.mrpapaia.desafio.model.Graph;
import com.mrpapaia.desafio.repository.GraphRepository;
import com.mrpapaia.desafio.service.DistanceService;
import com.mrpapaia.desafio.service.GraphService;
import com.mrpapaia.desafio.service.RouteService;
import com.mrpapaia.desafio.service.util.GraphUtil;
import com.mrpapaia.desafio.service.util.ToDTO;

@WebMvcTest(controllers = GraphController.class)
public class GraphControllerTest {
	@MockBean
	private GraphRepository graphRepository;
	@MockBean
	private GraphService graphService;
	@MockBean
	private RouteService routeService;
	@MockBean
	private DistanceService distanceService;

	private Graph graphResponse;
	@Autowired
	private MockMvc mockMvc;
	private GraphDTO graphDTO;
	private RoutesDTO routesDTO;
	private PathDTO pathDTOdistance;
	private PathDTO pathDTOandDistance;
	private String graphString = "{\n" + "	\"path\": [\n" + "		\"A\",\n" + "		\"B\",\n" + "		\"C\",\n"
			+ "		\"D\"\n" + "	],\n" + "	\"data\": [\n" + "		{\n" + "			\"source\": \"A\",\n"
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

	private String routeAtoCString = "{\n" + "  \"routes\": [\n" + "    {\n" + "      \"route\": \"ABC\",\n"
			+ "      \"stops\": 2\n" + "    },\n" + "    {\n" + "      \"route\": \"ADC\",\n" + "      \"stops\": 2\n"
			+ "    },\n" + "    {\n" + "      \"route\": \"ADEBC\",\n" + "      \"stops\": 4\n" + "    },\n" + "    {\n"
			+ "      \"route\": \"AEBC\",\n" + "      \"stops\": 3\n" + "    }\n" + "  ]\n" + "}";
	private String distanceAtoDString = "{\n" + "  \"distance\": 5\n" + "}";
	private String pathString = "{\n" + "	\"path\": [\n" + "		\"A\",\n" + "		\"D\"\n" + "	]\n" + "}";
	private String pathAndDistanceString="{\n" + 
			"  \"path\": [\n" + 
			"    \"A\",\n" + 
			"    \"B\",\n" + 
			"    \"C\"\n" + 
			"  ],\n" + 
			"  \"distance\": 9\n" + 
			"}";
	
	JSONObject graphJson;
	JSONObject pathJson;

	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException, JSONException {
		graphJson = new JSONObject(graphString);
		pathJson = new JSONObject(pathString);
		ObjectMapper mapper = new ObjectMapper();
		graphDTO = mapper.readValue(graphString, GraphDTO.class);
		graphResponse = GraphUtil.initGraph(graphDTO);
		graphResponse.setKey(1L);
		routesDTO = mapper.readValue(routeAtoCString, RoutesDTO.class);
		pathDTOdistance = mapper.readValue(distanceAtoDString, PathDTO.class);
		pathDTOandDistance= mapper.readValue(pathAndDistanceString, PathDTO.class);
		when(graphRepository.save(Mockito.any(Graph.class))).thenReturn(graphResponse);
		when(graphService.addGraph(Mockito.any(GraphDTO.class))).thenReturn(ToDTO.graphToDTO(graphResponse));
		when(graphRepository.findById(1L)).thenReturn(Optional.of(graphResponse));
		when(graphService.findGraphById(1L)).thenReturn(ToDTO.graphToDTO(graphResponse));
		when(routeService.getAllRoutesBetweenTwoVertexInGraph(Mockito.any(GraphDTO.class), Mockito.eq("A"),
				Mockito.eq("C"), Mockito.eq(4))).thenReturn(routesDTO);
		when(distanceService.getDistanceForPath(Mockito.any(GraphDTO.class))).thenReturn(pathDTOdistance);
		when(distanceService.getMinimalPath(Mockito.any(GraphDTO.class), Mockito.eq("A"),
				Mockito.eq("C"))).thenReturn(pathDTOandDistance);
		when(graphRepository.findById(2L)).thenThrow(new ResourceNotFoundException("Not Found") {
		});
		when(graphService.findGraphById(2L)).thenThrow(new ResourceNotFoundException("Not Found") {
		});
	}

	@Test
	@DisplayName(value = "Test method addGraph where success")
	void addNewGraph() throws JsonParseException, JsonMappingException, IOException, Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/graph").content(graphJson.toString())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}

	@Test
	@DisplayName(value = "Test method getGraphById where success")
	void getGraphById() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/graph/{graphId}", 1).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}

	@Test
	@DisplayName(value = "Test method getGraphById where not found graph")
	void getGraphByIdWhereNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/graph/{graphId}", 2).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

	}

	@Test
	@DisplayName(value = "Test method getAllRoutesBetweenTwoVertexInGraph ")
	void getAllRoutesBetweenTwoVertexInGraph() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/routes/from/{town1}/to/{town2}?maxStops={maxStops}", "A", "C", 4)
				.content(graphJson.toString()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.routes").exists());

	}

	@Test
	@DisplayName(value = "Test method getAllPathsByGraphId ")
	void getAllPathsByGraphId() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/routes/{graphId}/from/{town1}/to/{town2}?maxStops={maxStops}", 1, "A", "C", 4)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.routes").exists());

	}

	@Test
	@DisplayName(value = "Test method getAllPathsByGraphId  Not Found")
	void getAllPathsByGraphIdNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/routes/{graphId}/from/{town1}/to/{town2}?maxStops={maxStops}", 2, "A", "C", 4)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}

	@Test
	@DisplayName(value = "Test method findPathSize ")
	void findPathSize() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/distance").content(graphJson.toString())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.distance").value(5));

	}

	@Test
	@DisplayName(value = "Test method findPathSizeByGraphId ")
	void findPathSizeByGraphId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/distance/{graphId}", 1).content(pathJson.toString())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.distance").value(5));

	}

	@Test
	@DisplayName(value = "Test method findPathSizeByGraphId  Not Found")
	void findPathSizeByGraphIdNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/distance/{graphId}", 2).content(pathJson.toString())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}
	@Test
	@DisplayName(value = "Test method getMinimalPath ")
	void getMinimalPath() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/distance/from/{town1}/to/{town2}", "A", "C")
				.content(graphJson.toString()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.path").exists());

	}

	@Test
	@DisplayName(value = "Test method getMinimalPathByGraphId ")
	void getMinimalPathByGraphId() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("/distance/{graphId}/from/{town1}/to/{town2}", 1, "A", "C")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.path").exists());

	}

	@Test
	@DisplayName(value = "Test method getMinimalPathByGraphId  Not Found")
	void getMinimalPathByGraphIdNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/distance/{graphId}/from/{town1}/to/{town2}", 2, "A", "C")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}
	
	

}
