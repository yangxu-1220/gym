package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.gym.model.AttendCourseModel;
import com.gym.model.OrderCourseModel;


/**
 * 约课接口类
 * @author 姬西南2019/10/17  16：07
 *
 */
@Repository
public interface OrderCourseMapper {
	
	/**
	 * 分页，已上架课程，需要信息（查询当前日期今天，明天，后天的数据）
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@Select("SELECT * from ( " + 
			"SELECT attend_course_id as course_id,tb_attend_course.trainer_id, " + 
			"			tb_course.course_name,tb_trainer.trainer_name, " + 
			"			attend_course_time,attend_course_palce,tb_attend_course.attend_stock,  " + 
			"			tb_course.course_number,tb_course.course_length,tb_course.course_image   " + 
			"			FROM tb_attend_course,tb_trainer,tb_course   " + 
			"			where attend_course_state=\"已上架\"  " + 
			"			and tb_attend_course.attend_course_id=tb_course.course_id  " + 
			"			and tb_trainer.trainer_id=tb_attend_course.trainer_id  " + 
			"			and tb_attend_course.attend_course_time>CURRENT_TIME  " + 
			"			) as temp  " + 
			"			where TO_DAYS(attend_course_time)-TO_DAYS(NOW())=0 " + 
			"OR TO_DAYS(attend_course_time)-TO_DAYS(NOW())=1  " + 
			"OR TO_DAYS(attend_course_time)-TO_DAYS(NOW())=2 " + 
			"limit #{pageStart},#{pageSize}")
	public List<OrderCourseModel> getCourseByState(@Param("pageStart") int pageStart,@Param("pageSize") int pageSize);
	 /**
	  * 功能:根据课程名称去查课程ID
	  */
	 @Select("SELECT tb_course.course_id FROM tb_course WHERE tb_course.course_name=#{courseName}")
	 public int getCourseIdByCourseName(@Param("courseName") String courseName);
	 /**
	  * 功能:根据教练名称去查教练ID
	  */
	 @Select("SELECT tb_trainer.trainer_id FROM tb_trainer WHERE tb_trainer.trainer_name=#{trainerName}")
	 public int getTrainerByTrainerName(@Param("trainerName") String trainerName);
	 /**
	  * 功能:根据登陆的会员名查询会员id
	  */
	 @Select("SELECT tb_member.member_id FROM tb_member WHERE tb_member.member_name=#{memberName}")
	 public int getMemberByMemberName(@Param("memberName") String memberName);
	 /**
	  * 查询上架课程
	  */
	 @Select("SELECT attend_course_time FROM tb_order_course")
	 public String getAttendTime();
	 
	/**
	 * 查询已上架的课程信息（统计当前时间后今天，明天，后天的数据）
	 * @return
	 */
	 @Select("SELECT count(*) from ( " + 
	 		"SELECT attend_course_id as course_id,tb_attend_course.trainer_id, " + 
	 		"			tb_course.course_name,tb_trainer.trainer_name, " + 
	 		"			attend_course_time,attend_course_palce,tb_attend_course.attend_stock,  " + 
	 		"			tb_course.course_number,tb_course.course_length,tb_course.course_image   " + 
	 		"			FROM tb_attend_course,tb_trainer,tb_course   " + 
	 		"			where attend_course_state=\"已上架\"  " + 
	 		"			and tb_attend_course.attend_course_id=tb_course.course_id  " + 
	 		"			and tb_trainer.trainer_id=tb_attend_course.trainer_id  " + 
	 		"			and tb_attend_course.attend_course_time>CURRENT_TIME  " + 
	 		"			) as temp  " + 
	 		"			where TO_DAYS(attend_course_time)-TO_DAYS(NOW())=0 " + 
	 		"OR TO_DAYS(attend_course_time)-TO_DAYS(NOW())=1  " + 
	 		"OR TO_DAYS(attend_course_time)-TO_DAYS(NOW())=2")
	 public int getCourseTotalOfState();	
	 
	/**
	 * 根据当前登录会员ID去查询该会员的所有约课记录
	 * @param memberId
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	 @Select("SELECT  tb_order_course.*,"
	 		+ "tb_course.course_name,tb_trainer.trainer_name,"
	 		+ "tb_member.member_name,tb_course.course_length,tb_attend_course.attend_course_palce "  
	 		+ "FROM tb_order_course,tb_course,tb_trainer,tb_member,tb_attend_course "  
	 		+ "WHERE  tb_order_course.course_id=tb_course.course_id "  
	 		+ "and  tb_order_course.trainer_id=tb_trainer.trainer_id "  
	 		+ "and tb_member.member_id=tb_order_course.member_id "
	 		+ "and tb_order_course.course_id=tb_attend_course.attend_course_id "  
	 		+ "and tb_order_course.trainer_id=tb_attend_course.trainer_id "  
	 		+ "and tb_order_course.attend_course_time=tb_attend_course.attend_course_time " 
	 		+ "and tb_order_course.member_id=#{memberId} "
	 		+ "and tb_order_course.attend_course_time>CURRENT_TIME "  
	 		+ "limit #{pageStart},#{pageSize}")
	 public List<OrderCourseModel> OrderList(@Param("memberId") Integer memberId,
											 @Param("pageStart") Integer pageStart,
											 @Param("pageSize") Integer pageSize);
	 
	 /**
	  * 查表中有几条这个会员的约课信息
	  */
	 @Select("SELECT count(1) FROM tb_order_course WHERE member_id=#{memberId} "
	 		+ "and tb_order_course.attend_course_time>CURRENT_TIME")
	 public int getCountByMemberId(@Param("memberId") Integer memberId);
	 
	 /**
	  * 根据3个主键唯一确定一条课程记录查tb_attend_course
	  */
	 @Select("SELECT tb_attend_course.*,tb_course.course_name, "
	 		+ "tb_course.course_image,tb_trainer.trainer_name "
	 		+ "FROM tb_attend_course,tb_course,tb_trainer " 
	 		+ "WHERE tb_attend_course.attend_course_id=tb_course.course_id "
	 		+ "and tb_attend_course.trainer_id=tb_trainer.trainer_id "
	 		+ "and tb_attend_course.attend_course_id=#{attendCourseId} and "
	 		+ "tb_attend_course.trainer_id=#{trainerId} and "
	 		+ "tb_attend_course.attend_course_time=#{attendCourseTime}")
	 public AttendCourseModel selectAttendByPrimary(@Param("attendCourseId") int attendCourseId,
			 										@Param("trainerId") int trainerId,
			 										@Param("attendCourseTime") String attendCourseTime);
	 /**
	  * 查询order_course表中主键对应的所有记录
	  * @param orderbefo
	  * @return
	  */
	 @Select("SELECT tb_order_course.*,tb_attend_course.*,tb_member.*,tb_trainer.*,tb_course.* " 
	 		+ "FROM  tb_order_course,tb_attend_course,tb_member,tb_trainer,tb_course  " 
	 		+ "WHERE tb_member.member_id=tb_order_course.member_id "
	 	    + "and tb_course.course_id=tb_order_course.course_id " 
	 	    + "and tb_trainer.trainer_id=tb_order_course.trainer_id "
	 	    + "and tb_attend_course.attend_course_time=tb_order_course.attend_course_time ")
	 public OrderCourseModel getOrderCourseInfo(OrderCourseModel orderbefo);
	 /**
	 * 检查要插入的数据是否存在 
	 */
	@Select("SELECT * FROM tb_order_course "
			+ "WHERE tb_order_course.course_id=#{courseId} "
			+ "and tb_order_course.trainer_id=#{trainerId} "
			+ "and tb_order_course.member_id=#{memberId} "
			+ "and tb_order_course.attend_course_time=#{attendCourseTime} ")
	public OrderCourseModel checkOrderCourse(OrderCourseModel orderCourse);
	/**
	 * 检查该会员该时段是否有课 
	 */
	@Select("SELECT * FROM tb_order_course "
			+ "WHERE tb_order_course.member_id=#{memberId} "
			+ "and tb_order_course.attend_course_time=#{attendCourseTime} ")
	public OrderCourseModel checkOrderCourse_2(OrderCourseModel orderCourse);
	 /**
	  * 约课操作（在约课表插入一条记录）
	  * @param orderCourseModel
	  */
	 @Insert("INSERT INTO tb_order_course SET member_id=#{memberId},"
	 		+ "trainer_id=#{trainerId},course_id=#{courseId},"
	 		+ "attend_course_time=#{attendCourseTime},order_time=#{orderTime}")
	 public void InsertOrderCourse(OrderCourseModel orderCourseModel);
	 
	 /**
	  * 预约成功后，修改剩余库存量
	  */
	 @Update("UPDATE tb_attend_course "
	 		+ "SET attend_stock=attend_stock-1 "
	 		+ "WHERE attend_course_id=#{courseId} "
	 		+ "and trainer_id=#{trainerId} "
	 		+ "and attend_course_time=#{attendCourseTime}")
	 public void minStock(OrderCourseModel orderCourseModel);
	 /**
	  * 查询课程余量用于检测
	  */
	 @Select("SELECT tb_attend_course.attend_stock " + 
	 		"FROM tb_attend_course " + 
	 		"WHERE attend_course_id=#{courseId} " + 
	 		"AND trainer_id=#{trainerId} " + 
	 		"AND attend_course_time=#{attendCourseTime}")
	 public int QueryRestStock(OrderCourseModel orderCourseModel);
	 /**
	  * 取消预约后，修改剩余库存量
	  */
	 @Update("UPDATE tb_attend_course "
	 		+ "SET attend_stock=attend_stock+1 "
	 		+ "WHERE attend_course_id=#{courseId} "
	 		+ "and trainer_id=#{trainerId} "
	 		+ "and attend_course_time=#{attendCourseTime}")
	 public void plusStock(@Param("courseId") Integer courseId,
						   @Param("trainerId") Integer trainerId,
						   @Param("attendCourseTime") String attendCourseTime);
	 
	 /**
	  * 根据4个主键删除唯一对应的约课记录
	  */
	 @Delete("DELETE FROM tb_order_course "
	 		+ "WHERE member_id=#{memberId} "
	 		+ "and course_id=#{courseId} "
	 		+ "and trainer_id=#{trainerId} "
	 		+ "and attend_course_time=#{attendCourseTime}")
	 public void deleteOrderCourseByPrimary(@Param("memberId") Integer memberId,
			 								@Param("courseId") Integer courseId,
			 								@Param("trainerId") Integer trainerId,
			 								@Param("attendCourseTime") String attendCourseTime);
}
