package com.mx.liberty.evaluacion.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mx.liberty.evaluacion.dto.UsuarioDTO;
import com.mx.liberty.evaluacion.dto.UsuariosDTO;
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

	
	//esto es un comebtario para sbir a git
	@Operation(summary = "Guarda registro de Usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario guardado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
	@PostMapping(value ="guardaUsuario")
	public ResponseEntity<String> guardaUsuario( @RequestBody UsuariosDTO usuario ) {
		try {

 			System.out.println("usuario "+usuario.toString());
			
			String validacion = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			Pattern pattern = Pattern.compile(validacion);
			System.out.println("muetsra " + pattern.matcher(usuario.getEmail()).matches());
			if (pattern.matcher(usuario.getEmail()).matches() != true) {
				System.out.println("El email ingresado es válido.");
				return new ResponseEntity<String>("El email ingresado es válido.", HttpStatus.BAD_REQUEST);
			}

			usuarioService.save(usuario);
			return new ResponseEntity<String>("Usuario creado con éxito", HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

 

	@Operation(summary = "Guarda registro de Usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario guardado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
	@PostMapping(value = "subirImagen", consumes = "multipart/form-data")
	public ResponseEntity<String> subirImagen(@Parameter Integer id ,@RequestParam("file") MultipartFile multipartFile ) {
		try {

			String base64Image  = Base64.getEncoder().encodeToString(multipartFile.getBytes());
			usuarioService.subirFoto(id, base64Image);
			return new ResponseEntity<String>("Imagen subida con éxito", HttpStatus.CREATED);

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
	public ResponseEntity<String> actualiza(@RequestBody UsuarioDTO usuario) {
		try {
			String validacion = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			Pattern pattern = Pattern.compile(validacion);
			System.out.println("muetsra " + pattern.matcher(usuario.getEmail()).matches());
			if (pattern.matcher(usuario.getEmail()).matches() != true) {
				System.out.println("El email ingresado es válido.");
				return new ResponseEntity<String>("El email ingresado es válido.", HttpStatus.BAD_REQUEST);
			}
 
			usuarioService.update(usuario);
			return new ResponseEntity<String>("Cambio aplicado con éxito", HttpStatus.OK);
 
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
			return new ResponseEntity<String>("Cambio aplicado con éxito", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	
	
	@Operation(summary = "Paginar consulta")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
	@GetMapping(value = "paginador")
	public ResponseEntity<List<Usuario>> paginador(@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="1") int size) {
		try {
   			
			List<Usuario> list = usuarioService.findPaginated(page, size);
			return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Usuario>>( HttpStatus.BAD_REQUEST);
		}
	}
	 
	
	@Operation(summary = "Detalle Usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
	@GetMapping(value = "detalle" )
	public ResponseEntity<Usuario> detalle(@Parameter Integer id) {
		try {
			Usuario usuario =  usuarioService.getById(id);
			return new ResponseEntity<Usuario>( usuario, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@Operation(summary = "Descarga imagen")
	@GetMapping(value = "/descargaImagen", produces = "application/octet-stream")
	public ResponseEntity<String> start(@Parameter Integer id, HttpServletResponse response) throws IOException {
		try {
			Usuario usuario = usuarioService.getById(id);
			if (usuario!=null) {
				if (usuario.getFotoUsuario()!=null) {
					byte[] decodedString = Base64.getDecoder().decode(usuario.getFotoUsuario().getBytes("UTF-8"));
					System.out.println("decodedString"+decodedString);
					
					ServletOutputStream outStream = response.getOutputStream();
					response.setContentType("application/png");
					response.setHeader("Content-Disposition", "attachment; filename=descarga.png");
					outStream.write(decodedString);
					outStream.flush();
					return new ResponseEntity<String>("Imagen descargada con éxito",HttpStatus.OK);
				}else {
					return new ResponseEntity<String>("No hay imagen",HttpStatus.BAD_REQUEST);
				}
				
			}else {
				return new ResponseEntity<String>("No hay registro",HttpStatus.BAD_REQUEST);
			}
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error al descargar imagen",HttpStatus.BAD_REQUEST);
		}
		


 	}
	 
}
