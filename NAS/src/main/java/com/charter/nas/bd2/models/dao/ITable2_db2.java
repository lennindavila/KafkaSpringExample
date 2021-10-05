package com.charter.nas.bd2.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.charter.nas.bd2.models.entity.Table2_db2;

public interface ITable2_db2 extends CrudRepository<Table2_db2, Long> {
	
	@Query("from Table2_db2")
	public List<Table2_db2> findAll();
	
}
