package com.dxc.dao;

import java.util.List;

import com.dxc.beans.Student;

public interface DAO<E> {
	boolean save(E e) throws Exception;
	boolean edit(E e) throws Exception;
	boolean delete(int id) throws Exception;
	E find(int id) throws Exception;
	List<E> findAll() throws Exception;	
	
}
