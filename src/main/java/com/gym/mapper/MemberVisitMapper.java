package com.gym.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;

import com.gym.model.MemberVisitModel;

public interface MemberVisitMapper {
//	sql字符串
	String sqlString_1="select member_visit_time as comeTime,tb_member.member_id as memberId,tb_member.member_name as memberName " + 
			"from tb_member_visit,tb_member " + 
			"where tb_member_visit.member_id=tb_member.member_id and member_visit_time>=#{startTime} and member_visit_time<=#{endTime} ";
	String sqlString_2="select tb_member.member_id as memberId,tb_member.member_name as memberName,count(tb_member.member_id) as count "+
			"from tb_member_visit,tb_member "+
			"where tb_member_visit.member_id=tb_member.member_id and member_visit_time>=#{startTime} and member_visit_time<=#{endTime} " + 
			"GROUP BY tb_member.member_id";
	String sqlString_3="select * from (select tb_member.member_id as memberId,tb_member.member_name as memberName,count(tb_member.member_id) as count "+
			"from tb_member_visit,tb_member "+
			"where tb_member_visit.member_id=tb_member.member_id and member_visit_time>=#{startTime} and member_visit_time<=#{endTime} " + 
			"GROUP BY tb_member.member_id) as temp where memberId=#{member} or memberName=#{member}";
	//绘制条形图和折线图，统计来访时间内一天的来访数量
	String sqlString_4="select SUBSTRING_INDEX(member_visit_time,' ',1) as comeTime,count(member_id) as count " + 
			"from tb_member_visit " + 
			"where member_visit_time>=#{startTime} and member_visit_time<=#{endTime} " +
			"GROUP BY SUBSTRING_INDEX(member_visit_time,' ',1)";
	//统计总数量
	String sqlString_5="select count(*) " + 
			"from tb_member_visit,tb_member " + 
			"where tb_member_visit.member_id=tb_member.member_id and member_visit_time>=#{startTime} and member_visit_time<=#{endTime} ";
	
	@Select(sqlString_1+" limit #{page},#{limit} ")
	public List<MemberVisitModel> selectMemberVisitByDetailNull(String startTime,String endTime,int page,int limit);
	
	@Select(sqlString_5)
	public int count_1(String startTime,String endTime);
	
	@Select("select * from ("+sqlString_1+") as temp where memberId=#{member} or memberName=#{member} limit #{page},#{limit} ")
	public List<MemberVisitModel> selectMemberVisitByDetailMember(String startTime,String endTime,String member,int page,int limit);
	
	@Select("select count(*) from ("+sqlString_1+") as temp where memberId=#{member} or memberName=#{member} ")
	public int count_2(String startTime,String endTime,String member);
	
	@Select(sqlString_2)
	public List<MemberVisitModel> selectMemberVisitByTimeNull(String startTime,String endTime);
	
	@Select(sqlString_3)
	public List<MemberVisitModel> selectMemberVisitByTimeMember(String startTime,String endTime,String member);
	
	//绘制条形图和折线图，统计来访时间内一天的来访数量
	@Select(sqlString_4)
	public List<MemberVisitModel> selectMemberVisitonBarLine(String startTime,String endTime);
}
