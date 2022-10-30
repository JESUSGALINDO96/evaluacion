package com.mx.liberty.evaluacion.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.mx.liberty.evaluacion.dao.UsuarioDAO;
import com.mx.liberty.evaluacion.model.Usuario;

@Repository("usuarioDAO")
public class UsuarioDAOImpl implements UsuarioDAO  {

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		try {
			em.persist(usuario);
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public List<Usuario> getAll() {
		  return em.createQuery("from Usuario where estatus=true").getResultList();
	}


	@Override
	public boolean update(Usuario usuario) {
		try {
			 em.merge(usuario);
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}


	@Override
	public Usuario getById(Integer id) {
		 return em.find(Usuario.class, id);
	}


//	@Override
//	public boolean desactiva(Integer usuarioId) {
//		try {
//			 em.createQuery("Usuario "
//			 		+ " set estatus=false "
//			 		+ " where id=:usuarioId ")
//			 .setParameter("usuarioId", usuarioId).executeUpdate();
//			 return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}

}
