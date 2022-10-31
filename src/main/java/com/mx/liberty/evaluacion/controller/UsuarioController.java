package com.mx.liberty.evaluacion.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Pattern;

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
	public ResponseEntity<String> guarda(@RequestBody UsuarioDTO usuario) {
		try {

			String dir = System.getProperty("user.dir");

			System.out.println("directorio "+dir);
			String validacion = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			Pattern pattern = Pattern.compile(validacion);
			System.out.println("muetsra " + pattern.matcher(usuario.getEmail()).matches());
			if (pattern.matcher(usuario.getEmail()).matches() != true) {
				System.out.println("El email ingresado es válido.");
				return new ResponseEntity<String>("El email ingresado es válido.", HttpStatus.BAD_REQUEST);
			}
	
			boolean accion ;
			File original = new File(usuario.getFotoUsuario());
 	        File destino = new File(dir+"\\Imagen\\"+original.getName());
	        System.out.println("original "+original.getName());
 	        if (original.exists()) {
	            try {
	                InputStream in = new FileInputStream(original);
	                OutputStream out = new FileOutputStream(destino);
	                byte[] buf = new byte[1024];
	                int len;
	                while ((len = in.read(buf)) > 0) {
	                    out.write(buf, 0, len);
	                }
	                in.close();
	                out.close();
	                accion = true;
	            } catch (IOException ioe) {
	                ioe.printStackTrace();
	                accion = false;
	            }
	        } else {
	        	accion =  false;
	        }
			
 	        if (accion) {
 	        	usuario.setFotoUsuario(destino.getPath());
 	        	usuarioService.save(usuario);
 				return new ResponseEntity<String>("Usuario creado con éxito",HttpStatus.CREATED);
			}else {
				return new ResponseEntity<String>("Favor de validar ruta de la foto", HttpStatus.BAD_REQUEST);	
			}
			
			
			
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
			List<Usuario> usuarios = usuarioService.getAll();
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
			String validacion = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			Pattern pattern = Pattern.compile(validacion);
			System.out.println("muetsra " + pattern.matcher(usuario.getEmail()).matches());
			if (pattern.matcher(usuario.getEmail()).matches() != true) {
				System.out.println("El email ingresado es válido.");
				return new ResponseEntity<String>("El email ingresado es válido.", HttpStatus.BAD_REQUEST);
			}
			
//			String dir = System.getProperty("user.dir");
//			boolean accion ;
//			File original = new File(usuario.getFotoUsuario());
// 	        File destino = new File(dir+"\\Imagen\\"+original.getName());
//	        System.out.println("original "+original.getName());
// 	        if (original.exists()) {
//	            try {
//	                InputStream in = new FileInputStream(original);
//	                OutputStream out = new FileOutputStream(destino);
//	                byte[] buf = new byte[1024];
//	                int len;
//	                while ((len = in.read(buf)) > 0) {
//	                    out.write(buf, 0, len);
//	                }
//	                in.close();
//	                out.close();
//	                accion = true;
//	            } catch (IOException ioe) {
//	                ioe.printStackTrace();
//	                accion = false;
//	            }
//	        } else {
//	        	accion =  false;
//	        }
// 	        if (accion) {
 	        	usuarioService.update(usuario);
 	        	return new ResponseEntity<String>("Cambio aplicado con éxito", HttpStatus.OK);
//			}else {
//				return new ResponseEntity<String>("Error al cargar imagen", HttpStatus.BAD_REQUEST);
//			}
 	        
			
			
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
