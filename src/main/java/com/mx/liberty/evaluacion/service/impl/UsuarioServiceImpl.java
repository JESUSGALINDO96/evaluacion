package com.mx.liberty.evaluacion.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.mx.liberty.evaluacion.dao.UsuarioDAO;
import com.mx.liberty.evaluacion.dto.UsuarioDTO;
import com.mx.liberty.evaluacion.model.Usuario;
import com.mx.liberty.evaluacion.service.UsuarioService;


@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO; 
 
	
	@Override	
	public Usuario save(Usuario  usu) {
		return usuarioDAO.save(usu);
	}

	@Override
	@Transactional
	public List<Usuario> getAll() {
		return usuarioDAO.getAll();
	}

	@Override
	@Transactional
	public boolean update(Usuario usuario) {
		return usuarioDAO.update(usuario);
	}

	@Override
	@Transactional
	public boolean desactiva(Integer usuarioId) {
		Usuario usuario = getById(usuarioId);
		usuario.setEstatus(false);
		return usuarioDAO.update(usuario);
	}

	@Override
	@Transactional
	public Usuario getById(Integer usuarioId) {
		return usuarioDAO.getById(usuarioId);
	}

}
