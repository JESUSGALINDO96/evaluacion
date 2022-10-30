package com.mx.liberty.evaluacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.liberty.evaluacion.dto.UsuarioDTO;
import com.mx.liberty.evaluacion.model.Usuario;
import com.mx.liberty.evaluacion.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	
	@Operation(summary = "Guarda registro de Usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario guardado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content) })  
	@PostMapping("guardaUsuario")
	public ResponseEntity<String> guarda(@RequestBody Usuario usuario) {
		try {
			  usuarioService.save(usuario);
			  return new ResponseEntity<String>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@Operation(summary = "Muestra la lista de Usuarios")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuarios encontrados", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Usuarios no encontrados", content = @Content) }) // @formatter:on
	@GetMapping("getAll")
	public ResponseEntity<List<Usuario>> getAll() {
		try {
			List<Usuario> usuarios =  usuarioService.getAll();
 			return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@Operation(summary = "Actualiza el registro de Usuarios")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Actualización realizada correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content) }) // @formatter:on
	@PutMapping("actualiza")
	public ResponseEntity<String> actualiza(@RequestBody Usuario usuario) {
		try {
			usuarioService.update(usuario);
 			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@Operation(summary = "Eliminar el registro de Usuarios")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Registro eliminado con éxito", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content) }) // @formatter:on	
	@PutMapping("elimina")
	public ResponseEntity<String> elimina(@Parameter Integer id) {
		try {
			usuarioService.desactiva(id);
 			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
}
