package com.charter.nas.bd1.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.charter.nas.bd1.models.entity.Table1_db1;

public interface ITable1_db1 extends JpaRepository<Table1_db1, Long> {
	
	@Query("from Table1_db1")
	public List<Table1_db1> findAll();
	
}
