package com.gym.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.gym.model.PayModel;

@Repository
public interface PayMapper {
//	sql字符串
	String sqlString="insert into tb_business "
			+ "set no=#{no},trade_no=#{tradeNo},name=#{name},amount=#{amount},context=#{context}";
	
	//将交易信息写入数据库的tb_business表中
	@Select(sqlString)
	public void insertBusiness(PayModel pay);
}
