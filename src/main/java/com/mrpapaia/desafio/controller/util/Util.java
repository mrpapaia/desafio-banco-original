package com.mrpapaia.desafio.controller.util;

import java.util.List;

import com.mrpapaia.desafio.model.Vertex;

public class Util {
	public static String concatPaths(List<Vertex> paths) {

		String pathSrt = "";

		for (Vertex v : paths) {
			pathSrt += v.getName();
		}

		return pathSrt;
	}
}
