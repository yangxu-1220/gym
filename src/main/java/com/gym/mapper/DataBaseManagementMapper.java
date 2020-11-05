package com.gym.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;

import com.gym.model.DataBaseManagementModel;

public interface DataBaseManagementMapper {
	//sql字符串
	String sqlString="show DATABASES";
	
	@Select(sqlString)
	public List<DataBaseManagementModel> selectDataBaseByNull();
}
