package com.mx.liberty.evaluacion.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.Mergeable;
import org.springframework.stereotype.Repository;

import com.mx.liberty.evaluacion.dao.UsuarioDAO;
import com.mx.liberty.evaluacion.model.Usuario;

@Repository("usuarioDAO")
public class UsuarioDAOImpl implements UsuarioDAO  {

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
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
		return em.createQuery("from Usuario where estatus = true ").getResultList();
	}


	@Override
	public boolean update(Usuario usuario) {
		try {
			System.out.println(usuario.toString());
			 em.createQuery(" update Usuario set estatus=:estatus, "
			 		+ " email =:email, "
			 		+ " genero =:genero, "
			 		+ " nombre =:nombre "
			 		+ " where id=:id ")
			 .setParameter("id", usuario.getId())
			 .setParameter("email", usuario.getEmail())
			 .setParameter("genero", usuario.getGenero())
			 .setParameter("nombre", usuario.getNombre())
			 .setParameter("estatus", usuario.isEstatus())
			 .executeUpdate();
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}


	@Override
	public Usuario getById(Integer id) {
		 return em.find(Usuario.class, id);
//		return em.createQuery("from Usuario where estatus = true ")
//				.getFirstResult();
	}


	@Override
	public boolean desactiva(Integer usuarioId) {
		try {
			 em.createQuery(" update Usuario "
			 		+ " set estatus=false "
			 		+ " where id=:usuarioId ")
			 .setParameter("usuarioId", usuarioId)
			 .executeUpdate();
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean subirFoto(Integer id, String bas64) {
		try {
			 em.createQuery(" update Usuario "
			 		+ " set fotoUsuario=:bas64 "
			 		+ " where id=:id ")
			 .setParameter("bas64", bas64)
			 .setParameter("id", id)
			 .executeUpdate();
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	 
}
