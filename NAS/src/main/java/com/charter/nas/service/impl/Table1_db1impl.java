package com.charter.nas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.charter.nas.bd1.models.entity.Table1_db1;
import com.charter.nas.service.ITable1_db1;

@Service
public class Table1_db1impl implements ITable1_db1 {
	
	@Autowired
	private com.charter.nas.bd1.models.dao.ITable1_db1 table1_db1dao;

	@Override
	@Transactional(readOnly = true)
	public List<Table1_db1> findAll() {
		return table1_db1dao.findAll();
	}
}
