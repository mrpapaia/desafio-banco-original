package com.mrpapaia.desafio.service;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrpapaia.desafio.dto.GraphDTO;
import com.mrpapaia.desafio.dto.PathDTO;

@SpringBootTest
class DistanceServiceTests {
	@Autowired
	private DistanceService distanceService;
	private GraphDTO graphDTO;
	

	@BeforeEach
	public void setup() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		graphDTO = mapper.readValue(graphJson, GraphDTO.class);
	}

	@Test
	@DisplayName(value = "Test method getDistanceForPath where path=[A, D]")
	void getDistanceForPathAD() {
		graphDTO.setPath(Arrays.asList("A", "D"));
		PathDTO pathDTOResponse = distanceService.getDistanceForPath(graphDTO);
		assertEquals(5, pathDTOResponse.getDistance().intValue());
	}

	@Test
	@DisplayName(value = "Test method getDistanceForPath where path=[A, D,C]")
	void getDistanceForPathADC() {
		graphDTO.setPath(Arrays.asList("A", "D", "C"));
		PathDTO pathDTOResponse = distanceService.getDistanceForPath(graphDTO);
		assertEquals(13, pathDTOResponse.getDistance().intValue());
	}

	@Test
	@DisplayName(value = "Test method getDistanceForPath where path=[A, E, B, C, D]")
	void getDistanceForPathAEBCD() {
		graphDTO.setPath(Arrays.asList("A", "E", "B", "C", "D"));
		PathDTO pathDTOResponse = distanceService.getDistanceForPath(graphDTO);
		assertEquals(22, pathDTOResponse.getDistance().intValue());
	}

	@Test
	@DisplayName(value = "Test method getDistanceForPath where path=[A, E, D]")
	void getDistanceForPathAED() {
		graphDTO.setPath(Arrays.asList("A", "E", "D"));
		PathDTO pathDTOResponse = distanceService.getDistanceForPath(graphDTO);
		assertEquals(-1, pathDTOResponse.getDistance().intValue());
	}

	@Test
	@DisplayName(value = "Test method getDistanceForPath where path=[A, B,C]")
	void getDistanceForPathABC() {
		graphDTO.setPath(Arrays.asList("A", "B", "C"));
		PathDTO pathDTOResponse = distanceService.getDistanceForPath(graphDTO);
		assertEquals(9, pathDTOResponse.getDistance().intValue());
	}

	@Test
	@DisplayName(value = "Test method getMinimalPath where path A to C")
	void getMinimalPathAtoC() {		
		PathDTO pathDTOResponse = distanceService.getMinimalPath(graphDTO, "A", "C");
		assertEquals(9, pathDTOResponse.getDistance().intValue());
		assertEquals(Arrays.asList("A", "B", "C"), pathDTOResponse.getPath());
	}
	@Test
	@DisplayName(value = "Test method getMinimalPath where path B to B")
	void getMinimalPathCtoC() {		
		PathDTO pathDTOResponse = distanceService.getMinimalPath(graphDTO, "B", "B");
		assertEquals(0, pathDTOResponse.getDistance().intValue());
		assertEquals(Arrays.asList("B"), pathDTOResponse.getPath());
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
