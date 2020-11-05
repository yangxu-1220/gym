package com.gym.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;

import com.gym.model.MemberConsumptionModel;

public interface MemberConsumptionMapper {
//	sql字符串
	String sqlString="select tb_member.member_id as memberId,sale_id as saleId,tb_member.member_id as cardId,member_name as memberName,tb_goods.goods_id as goodsId,goods_name as goodsName,tb_goods.goods_price as price,sale_number as number,sale_price as amount,sale_time as time " + 
			"from tb_sale,tb_member,tb_goods " + 
			"where tb_sale.member_id=tb_member.member_id and tb_sale.goods_id=tb_goods.goods_id and sale_time>=#{startTime} and sale_time<=#{endTime} ";
	String sqlString_2="select stuff_name as stuff from tb_stuff";
	//绘制饼图--统计会员在一段时间内的消费总金额
	String sqlString_3="select member_name as memberName,SUM(sale_price) as amount " + 
			"from tb_sale,tb_member " + 
			"where tb_sale.member_id=tb_member.member_id and sale_time>=#{startTime} and sale_time<=#{endTime} " + 
			"GROUP BY member_name";
	//绘制条形图，折线图--统计一段时间内会员按天的消费情况
	String sqlString_4="select SUBSTRING_INDEX(sale_time,' ',1) as time,SUM(sale_price) as amount " + 
			"from tb_sale,tb_member " + 
			"where tb_sale.member_id=tb_member.member_id and sale_time>=#{startTime} and sale_time<=#{endTime} " + 
			"GROUP BY SUBSTRING_INDEX(sale_time,' ',1)";
	//统计总数量
	String sqlString_5="select count(*) " + 
			"from tb_sale,tb_member,tb_goods " + 
			"where tb_sale.member_id=tb_member.member_id and tb_sale.goods_id=tb_goods.goods_id and sale_time>=#{startTime} and sale_time<=#{endTime} ";
	
	@Select("select * from ("+sqlString+" limit #{page},#{limit} "+") as temp where memberId>=100000 and memberId<200000 ")
	public List<MemberConsumptionModel> selectMemberConsumptionByNull(String startTime,String endTime,int page,int limit); 
	
	@Select(sqlString_5)
	public int count_4(String startTime,String endTime);
	
	@Select("select * from ("+sqlString+") as temp where saleId=#{member} or memberName=#{member} or memberId=#{member} limit #{page},#{limit} ")
	public List<MemberConsumptionModel> selectMemberConsumptionByMember(String startTime,String endTime,String member,int page,int limit); 

	@Select("select count(*) from ("+sqlString+") as temp where saleId=#{member} or memberName=#{member} or memberId=#{member} ")
	public int count_2(String startTime,String endTime,String member); 
	
	@Select(sqlString+" and stuff_name=#{stuff} limit #{page},#{limit} ")
	public List<MemberConsumptionModel> selectMemberConsumptionByStuff(String startTime,String endTime,String stuff,int page,int limit);
	
	@Select(sqlString+" and stuff_name=#{stuff} ")
	public int count_3(String startTime,String endTime,String stuff);
	
	@Select("select * from ("+sqlString+" and stuff_name=#{stuff})"+" as temp where saleId=#{member} or memberName=#{member} or memberId=#{member} limit #{page},#{limit}")
	public List<MemberConsumptionModel> selectMemberConsumptionByMemberStuff(String startTime,String endTime,String member,String stuff,int page,int limit);
	
	@Select("select count(*) from ("+sqlString+" and stuff_name=#{stuff})"+" as temp where saleId=#{member} or memberName=#{member} or memberId=#{member}")
	public int count_1(String startTime,String endTime,String member,String stuff);
	
	//查询所有员工姓名
	@Select(sqlString_2)	
	public List<MemberConsumptionModel> selectStuffByNull();
	
	//绘制饼图--统计会员在一段时间内的消费总金额
	@Select(sqlString_3)	
	public List<MemberConsumptionModel> selectMemberonPie(String startTime,String endTime);
	
	//绘制条形图，折线图--统计一段时间内会员按天的消费情况
	@Select(sqlString_4)	
	public List<MemberConsumptionModel> selectMemberonBarLine(String startTime,String endTime);
}
