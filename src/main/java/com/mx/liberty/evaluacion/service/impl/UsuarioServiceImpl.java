package com.mx.liberty.evaluacion.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mx.liberty.evaluacion.dao.IUsuarioDao;
import com.mx.liberty.evaluacion.dao.UsuarioDAO;
import com.mx.liberty.evaluacion.dto.UsuarioDTO;
import com.mx.liberty.evaluacion.dto.UsuariosDTO;
import com.mx.liberty.evaluacion.model.Usuario;
import com.mx.liberty.evaluacion.service.UsuarioService;


@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO; 
	@Autowired
	private IUsuarioDao iusuarioDAO; 
 
	
	@Override	
	@Transactional
	public Usuario save(UsuariosDTO  usuario) {
		Usuario usu = new Usuario();
		usu.setEmail(usuario.getEmail());
		usu.setEstatus(usuario.isEstatus());
		usu.setFotoUsuario(null);
		usu.setGenero(usuario.getGenero());
		usu.setNombre(usuario.getNombre());
		return usuarioDAO.save(usu);
	}

	@Override
	@Transactional
	public List<Usuario> getAll() {
		return usuarioDAO.getAll();
	}

	@Override
	@Transactional
	public boolean update(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setEstatus(usuarioDTO.isEstatus());
		usuario.setGenero(usuarioDTO.getGenero());
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setId(usuarioDTO.getId());
		return usuarioDAO.update(usuario);
	}

	@Override
	@Transactional
	public boolean desactiva(Integer usuarioId) {
		return usuarioDAO.desactiva(usuarioId);
	}

	@Override
	@Transactional
	public Usuario getById(Integer usuarioId) {
		return usuarioDAO.getById(usuarioId);
	}

	@Override
	@Transactional
	public boolean subirFoto(Integer id, String bas64) {
		return usuarioDAO.subirFoto(id, bas64);
	}

//	@Override
//	@Transactional
//	public Page<Usuario> findAll(Pageable pageable) {
//		
//		return iusuarioDAO.findAll(pageable);
//	}

	@Override
	@Transactional
	public List<Usuario> findPaginated(int pageNo, int pageSize) {
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);
			System.out.println("*** "+paging);
	        Page<Usuario> pagedResult = iusuarioDAO.findAll(paging);

	        return pagedResult.toList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
