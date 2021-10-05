package com.charter.nas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.charter.nas.bd2.models.entity.Table2_db2;

import com.charter.nas.service.ITable2_db2;

@Service
public class Table2_db2impl implements ITable2_db2 {
	
	@Autowired
	private com.charter.nas.bd2.models.dao.ITable2_db2 table2_db2dao;

	@Override
	@Transactional(readOnly = true)
	public List<Table2_db2> findAll() {
		return table2_db2dao.findAll();
	}

	@Override
	@Transactional
	public Table2_db2 save(Table2_db2 table2) {
		return table2_db2dao.save(table2);
	}
}
