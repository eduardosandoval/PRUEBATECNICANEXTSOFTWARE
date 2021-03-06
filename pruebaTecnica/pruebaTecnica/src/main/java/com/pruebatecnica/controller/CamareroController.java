package com.pruebatecnica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.pruebatecnica.entity.Camarero;
import com.pruebatecnica.service.CamareroService;

import com.google.gson.Gson;
@Component
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class CamareroController {
	Gson gson = new Gson();

	@Autowired
	CamareroService camareroService;

	@GetMapping(value = "/getCamareros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCamareros() {
		try {
			List<Camarero> response = camareroService.getCamareros();
			String JSON = gson.toJson(response);

			System.out.println(response);
			return new ResponseEntity<>(JSON, HttpStatus.OK);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping(value = "/getCamarerosId/{idCamarero}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCamarerosId(@PathVariable int idCamarero) {
		try {
			List<Camarero> response = camareroService.getCamarerosId(idCamarero);
			String JSON = gson.toJson(response);

			System.out.println(response);
			return new ResponseEntity<>(JSON, HttpStatus.OK);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	@PostMapping(value = "/saveCamarero", produces = MediaType.APPLICATION_JSON_VALUE,consumes =  MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<?> saveCamarero(@RequestBody Camarero camarero) {
		try {
			int respuesta = camareroService.saveCamarero(camarero);

			return new ResponseEntity<>(respuesta, HttpStatus.OK);

			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
		

	}
}
