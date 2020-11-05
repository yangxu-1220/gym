package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.gym.model.CourseModel;
/**
 * 课程设置接口,改于2019年10月9日
 * @author jxn 2019/09/07 
 * 新设置分页
 */


@Repository
public interface CourseMapper {
	/**
	 * 查询所有课程信息
	 * @return
	 */
	@Select("SELECT * FROM tb_course")
	public List<CourseModel> getAllCourse();
	/**
	 * 根据id查询所有课程信息
	 * @param courseId
	 * @return
	 */
	@Select("SELECT * FROM tb_course WHERE course_id=#{courseId}")
	public CourseModel getCourseById(@Param("courseId") int courseId);
	
	/**
	 * 根据名字获得course对象
	 */
	@Select("SELECT * FROM tb_course WHERE course_name=#{courseName}")
	public CourseModel getCourseByName(@Param("courseName") String courseName);
	
	/**
	 * 根据课程的名称查ID
	 * @param courseName
	 * @return
	 */
	@Select("SELECT course_id FROM tb_course WHERE course_name=#{courseName}")
	public int getCourseIdByName(@Param("courseName") String courseName);
	
	/**
	 * 添加课程信息
	 * @param courseModel
	 * @return 添加的条数
	 */
	@Insert("INSERT INTO tb_course set course_id=#{courseId},course_name=#{courseName},"
			+ "course_length=#{courseLength},course_number=#{courseNumber},"
			+ "course_image=#{courseImage},course_content=#{courseContent}")
	public boolean InsertCourse(CourseModel courseModel);
	/**
	 * 修改课程信息
	 * @param courseModel
	 * @return 修改的信息条数
	 */
	@Update("UPDATE tb_course SET course_name=#{courseName},course_length=#{courseLength},"
			+ "course_number=#{courseNumber},course_Image=#{courseImage},course_content=#{courseContent}"
			+ " where course_id=#{courseId}")
	public boolean UpdateCourse(CourseModel courseModel);
	/**
	 * 删除课程信息
	 * @param courseId
	 * @return 根据id删除的条数
	 */
	@Delete("DELETE FROM tb_course WHERE course_id=#{courseId}")
	public int DeleteCourseInfo(int courseId);
	
	/**
	 * 根据课程id和名称查询课程信息
	  * 返回值：CourseModel
	 */
	@Select("SELECT * FROM tb_course WHERE course_id=#{courseA} or course_name=#{courseA}")
	public CourseModel getCourseInfo(@Param("courseA") String courseA);
	 /**
	  * @author jxn添加于2019/10/09
	  * 查询表中有多少条记录
	  */
	@Select("SELECT count(1) FROM tb_course")
	public int getCourseTotal();
	
	   /**
	    * 实现分页的查询 jxn添加于2019/10/09
	    * @param pageStart  pageSize根据传入的pageStart和pageSize通过limit来查询从pageStart开始，查pageSize条数据
	    * @return
	    */
	@Select("SELECT * FROM tb_course limit #{pageStart},#{pageSize}")
	public List<CourseModel> getCourseBtween(@Param("pageStart") int pageStart,@Param("pageSize") int pageSize);
}
