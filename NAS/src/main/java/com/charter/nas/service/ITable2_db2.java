package com.charter.nas.service;

import java.util.List;
import com.charter.nas.bd2.models.entity.Table2_db2;

public interface ITable2_db2 {
	public List<Table2_db2> findAll();	
	public Table2_db2 save(Table2_db2 table2);
}
