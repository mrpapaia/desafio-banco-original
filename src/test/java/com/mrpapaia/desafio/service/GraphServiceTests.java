package com.mrpapaia.desafio.service;


import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrpapaia.desafio.dto.GraphDTO;
import com.mrpapaia.desafio.model.Graph;
import com.mrpapaia.desafio.repository.GraphRepository;
import com.mrpapaia.desafio.service.util.GraphUtil;
import com.mrpapaia.desafio.controller.exception.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GraphServiceTests {

	private GraphDTO graphDTO;
	private Graph graphResponse;
	@MockBean
	private GraphRepository graphRepository;
	@Autowired
	private GraphService graphService;

	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		graphDTO = mapper.readValue(graphJson, GraphDTO.class);
		graphResponse = GraphUtil.initGraph(graphDTO);
		graphResponse.setKey(1L);
	}

	@Test
	@DisplayName(value = "Test method addGraph where success")
	void addGraphTest() {

		when(graphRepository.save(Mockito.any(Graph.class))).thenReturn(graphResponse);
		GraphDTO graphDTOResponse = graphService.addGraph(graphDTO);
		assertEquals(1, graphDTOResponse.getId().intValue());
	}

	@Test
	@DisplayName(value = "Test method findGraphByIdTest where success")
	void findGraphByIdTestWhereSuccess() {

		when(graphRepository.findById(1L)).thenReturn(Optional.of(graphResponse));
		GraphDTO graphResponse = graphService.findGraphById(1L);
		assertEquals(1, graphResponse.getId().intValue());
	}

	@Test
	@DisplayName(value = "Test method findGraphByIdTest where not found graph")
	void findGraphByIdTestWhereNotFound() {

		when(graphRepository.findById(1L)).thenThrow(new ResourceNotFoundException("Not Found") {
		});

		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
			graphRepository.findById(1L);
		});
		String expectedMessage = "Not Found";

		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
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
