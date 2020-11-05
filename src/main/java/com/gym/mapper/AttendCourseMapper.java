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
 * 上课接口类
 * @author 姬西南2019/10/11 15:00
 *
 */

@Repository
public interface AttendCourseMapper {
	/**
	 * 根据三个主键唯一确定一条记录
	 * @param courseId
	 * @param trainerId
	 * @param attendTime
	 * @return 三个主键唯一确定的AttendCourseModel对象
	 */
	@Select("SELECT * FROM tb_attend_course WHERE attend_course_id=#{courseId} "
			+ "and trainer_id=#{trainerId} and attend_course_time=#{attendTime}")
	public AttendCourseModel getAttendCourseInfo(int courseId,int trainerId, String attendTime);
	/**
	  * 查询上课表中有多少条记录
	 */
	 @Select("SELECT count(1) FROM tb_attend_course")
	 public int getAttendCourseTotal();
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
	    * 实现分页的查询
	    * 根据传入的pageStart和pageSize通过limit来查询从pageStart开始，查pageSize条数据
	  */
	@Select("SELECT tb_attend_course.*,tb_trainer.trainer_name as trainer_name,tb_course.course_name " 
		    + "as course_name,tb_course.course_length as course_length,tb_course.course_number as course_number "
			+ "FROM tb_attend_course,tb_trainer,tb_course WHERE tb_attend_course.trainer_id=tb_trainer.trainer_id and "
		    + "tb_attend_course.attend_course_id=tb_course.course_id limit #{pageStart},#{pageSize}")
	public List<AttendCourseModel> getAttendCourseBtween(@Param("pageStart") int pageStart,@Param("pageSize") int pageSize);
    /**
     * 根据课程表和教练表查询上课表所有相关信息
     * @return List<AttendCourseModel>
     */
	@Select("SELECT tb_attend_course.*,tb_trainer.trainer_name as trainer_name,tb_course.course_name "
			+ "as course_name,tb_course.course_length as course_length,tb_course.course_number as course_number "
			+ "FROM tb_attend_course,tb_trainer,tb_course WHERE tb_attend_course.trainer_id=tb_trainer.trainer_id and "
			+ "tb_attend_course.attend_course_id=tb_course.course_id")
	public List<AttendCourseModel> getAllAttendCourse();
	/**
	 * 检查要插入的数据是否存在 （教练和上课时间）
	 */
	@Select("SELECT * FROM tb_attend_course "
			+ "WHERE tb_attend_course.trainer_id=#{trainerId} and "
			+ "tb_attend_course.attend_course_time=#{attendCourseTime}")
	public AttendCourseModel checkAttendCourse(AttendCourseModel attendCourse);
	/**
	 * 检查要修改的数据是否和已经存在的数据冲突（教练和上课时间）
	 * @param attendCourse
	 * @return
	 */
	@Select("SELECT * FROM tb_attend_course "
			+ "WHERE tb_attend_course.trainer_id=#{trainerId} and "
			+ "tb_attend_course.attend_course_time=#{attendCourseTime2}")
	public AttendCourseModel checkAttendCourse_2(AttendCourseModel attendCourse);
	/**
	 * 检查要插入的数据是否和已经存在的数据冲突（上课时间和上课地点）
	 * @param attendCourse
	 * @return
	 */
	@Select("SELECT * FROM tb_attend_course "
			+ "WHERE tb_attend_course.attend_course_palce=#{attendCoursePalce} and "
			+ "tb_attend_course.attend_course_time=#{attendCourseTime}")
	public AttendCourseModel checkAttendCourse_1_2(AttendCourseModel attendCourse);
	/**
	 * 检查要修改的数据是否和已经存在的数据冲突（上课地点和上课时间）
	 * @param attendCourse
	 * @return
	 */

	@Select("SELECT * FROM tb_attend_course "
			+ "WHERE tb_attend_course.attend_course_palce=#{attendCoursePalce} and "
			+ "tb_attend_course.attend_course_time=#{attendCourseTime2}")
	public AttendCourseModel checkAttendCourse_2_2(AttendCourseModel attendCourse);
	/**
	  * 新增一个需要新上架的课程
	 * @return 添加的条数
	 */
	@Insert("INSERT INTO tb_attend_course SET attend_course_id=#{attendCourseId},trainer_id=#{trainerId},"
			+ "attend_course_time=#{attendCourseTime},attend_course_palce=#{attendCoursePalce},"
			+ "attend_course_state=#{attendCourseState},attend_stock=#{attendStock}")
	public void InsertAttendCourse(AttendCourseModel attendCourseModel);
    /**
     * 修改需要上架课程的信息
     * @return 修改的条数
     */
	@Update("UPDATE tb_attend_course SET attend_course_time=#{attendCourseTime2},attend_course_palce=#{attendCoursePalce},attend_course_state="
			+ "#{attendCourseState} WHERE attend_course_id=#{attendCourseId} and trainer_id=#{trainerId} and attend_course_time=#{attendCourseTime}")
	public int UpdateAttendCourse(AttendCourseModel attendCourseModel);
	/**
	  * 删除不需要上架的课程
	 */
	@Delete("DELETE FROM tb_attend_course WHERE attend_course_id=#{attendCourseId} and trainer_id=#{trainerId} and attend_course_time=#{attendCourseTime}")
	public int DeleteAttendCourse(int attendCourseId,int trainerId,String attendCourseTime);
	/**
	 * 根据课程id，教练id和上课时间确定一条上课记录去修改该记录的状态位为未上架
	 */
	@Update("UPDATE tb_attend_course SET attend_course_state=\"未上架\" "
			+ "WHERE attend_course_id=#{courseId} "
			+ "and trainer_id=#{trainerId} "
			+ "and attend_course_time=#{attendCourseTime}")
	public void renewStateToNo(@Param("courseId") int courseId,
 						       @Param("trainerId") int trainerId,
							   @Param("attendCourseTime") String attendCourseTime);
	/**
	 * 根据课程id，教练id和上课时间确定一条上课记录去修改该记录的状态位为已上架
	 */
	@Update("UPDATE tb_attend_course SET attend_course_state=\"已上架\" "
			+ "WHERE attend_course_id=#{courseId} "
			+ "and trainer_id=#{trainerId} "
			+ "and attend_course_time=#{attendCourseTime}")
	public void renewStateToYes(@Param("courseId") int courseId,
								@Param("trainerId") int trainerId,
								@Param("attendCourseTime") String attendCourseTime);
	
	/**
	 * 查看会员约课情况
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@Select("SELECT tb_order_course.*,tb_attend_course.*,tb_trainer.*, "
			+ "tb_course.*,tb_member.* "
			+ "FROM tb_attend_course,tb_order_course,tb_trainer,tb_course,tb_member "
			+ "WHERE tb_order_course.trainer_id=tb_trainer.trainer_id "
			+ "and tb_order_course.course_id=tb_course.course_id "
			+ "and tb_order_course.member_id=tb_member.member_id "
			+ "and tb_order_course.attend_course_time=tb_attend_course.attend_course_time "
			+ "and tb_order_course.course_id=#{courseId} "
			+ "and tb_order_course.trainer_id=#{trainerId} "
			+ "and tb_order_course.attend_course_time=#{attendCourseTime}"
			+ "limit #{pageStart},#{pageSize}")
	public List<OrderCourseModel> getAttendOrder(@Param("courseId") int cId,@Param("trainerId")int tId,@Param("attendCourseTime")String tim, @Param("pageStart") int pageStart,@Param("pageSize") int pageSize);
	
	/**
	  * 查询会员约课情况
	 */
	 @Select("SELECT count(1) FROM tb_order_course "
	 		+ "WHERE tb_order_course.course_id=#{courseId} "
			+ "and tb_order_course.trainer_id=#{trainerId} "
	 		+ "and tb_order_course.attend_course_time=#{attendCourseTime} " 
			+ "limit #{pageStart},#{pageSize}")
	 public int getAttendOrderTotal();
}
