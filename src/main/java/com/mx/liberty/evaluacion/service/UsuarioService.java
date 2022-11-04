package com.mx.liberty.evaluacion.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mx.liberty.evaluacion.dto.UsuarioDTO;
import com.mx.liberty.evaluacion.dto.UsuariosDTO;
import com.mx.liberty.evaluacion.model.Usuario;

public interface UsuarioService {
	
	Usuario save(UsuariosDTO usuario);
	
	List<Usuario> getAll();
	
	boolean update(UsuarioDTO usuarioDTO);
	
	boolean desactiva(Integer usuarioId);
	
	Usuario getById(Integer usuario);
	
	boolean subirFoto(Integer id,String bas64);
	
//	Page<Usuario> findAll(Pageable pageable);
	List<Usuario> findPaginated(int pageNo, int pageSize);

}
