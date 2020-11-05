package com.gym.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.gym.model.MStuffModel;

@Repository
public interface MStuffMapper {

	//查询会籍顾问的信息
	@Select("SELECT * FROM tb_stuff WHERE stuff_type='前台'")
	public List<MStuffModel> GetAllStuff();
}
