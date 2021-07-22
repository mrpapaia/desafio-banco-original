package com.mrpapaia.desafio.controller;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mrpapaia.desafio.dto.GraphDTO;

import com.mrpapaia.desafio.service.RouteService;

@RestController
@RequestMapping("/routes")
public class RouteController {
	
	@Autowired
	private RouteService routeService;
	@PostMapping(value = "/from/{town1}/to/{town2}")
    public ResponseEntity<String> encontrarCaminhos(@RequestBody GraphDTO data, @PathVariable String town1, @PathVariable String town2, 
            @RequestParam(value = "maxStops", required=false) Integer maxStops) {		
		
		return ResponseEntity.ok(routeService.getRoutes(data,town1,town2,maxStops));
	}
	  
}
