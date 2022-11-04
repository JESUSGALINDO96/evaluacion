package com.mx.liberty.evaluacion.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mx.liberty.evaluacion.model.Usuario;

 
public interface IUsuarioDao  extends PagingAndSortingRepository<Usuario, Long> {

}
