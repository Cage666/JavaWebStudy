/**
 * @FileName:StudentDAOImpl.java
 * @description:
 * @author Cage Yang
 * @version 
 * Modified Date:2017年8月4日
 * Why & What is modified:
 * 1.将updateStu函数的返回值由Student修改为boolean，参数列表增加long id。
 * 	  函数体：将返回值设为“影响行数>0则返回true”；sql语句中的条件参数由stu.getId()改为id。
 * 		原因：不需要获取旧学生信息；由调用者提供id参数更好。
 * 2.在addStu函数中，去掉sql语句中id的添加，id由数据库自增完成，以免发生id重复的情况。
 * 3.将各查询方法中的RowMapper提取出来封装为一个类，在各查询方法中直接使用此类对象，避免重复代码。
 * 	  为适配封装的QueryStuRowMapper，将各查询方法中的sql语句改为查询所有属性。
 * 4.修改addStu函数体，当name、major、jnshuId为空时报错。
 * 5.在updateStu函数体中也加上非空报错。
 * 6.bug：各sql语句中的表名是student，数据库表名是students，应修改。
 * 7.在通过id、专业+学号查询的方法中，通过try-catch捕获EmptyResultDataAccessException异常，给出查询结果为空的提示。
 * 8.修改queryStuByName的返回值为List<Student>，因为可能出现的同名学生；然后通过query函数查询，且当结果size==0时，给出查询结果为空的提示。
 * 9.修改delStuByName的返回值为int，因为可能出现的同名学生。
 * 10.删除delStuByName和delStuByJnshu两个方法。
 * 		原因：在实际业务逻辑中一般是先查询，然后根据查询结果选择删除。既然是这样，只需要根据选择项的id删除即可，不会出现误删的问题。
 */
package cn.cage.student;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName StudentDAOImpl
 * @description 
 * @author Cage Yang 
 */
public class StudentDAOImpl implements StudentDAO {
	private ApplicationContext apc = null;
	private JdbcTemplate jdbcTemplate = null;

	/**
	 * 创建对象的同时获取jdbcTemplate对象。
	 */
	public StudentDAOImpl() {
		// TODO 自动生成的构造函数存根
		apc = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) apc.getBean("jdbcTemplate");
	}
	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#addStu(cn.cage.student.Student)
	 */
	public boolean addStu(Student stu) {
		// TODO 自动生成的方法存根
		String name = stu.getName(), major = stu.getMajor();
		int jnshuId = stu.getJnshuId();
		if (name==null || major==null || jnshuId==0) {
			throw new RuntimeException("addStu：姓名、专业、学号不能为空！");
		}
		String sql = "INSERT INTO students (name,qq,major,entrytime,gra_school,id_jnshu"
				+ ",daily_url,desire,bro_jnshu,knowfrom) VALUES (?,?,?,?,?,?,?,?,?,?)";
		int line = jdbcTemplate.update(sql,new Object[] {
				name,stu.getQq(),major,stu.getEntryTime(),
				stu.getSchool(),jnshuId,stu.getDailyUrl(),stu.getDesire(),
				stu.getJnshuBro(),stu.getKnowFrom()});
		return line>0?true:false;
	}

	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#delStuById(long)
	 */
	public boolean delStuById(long id) {
		// TODO 自动生成的方法存根
		String sql = "DELETE FROM students WHERE id=?";
		int line = jdbcTemplate.update(sql,id);
		return line>0?true:false;
	}

	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#delStuByName(java.lang.String)
	 */
	/*
	public int delStuByName(String name) {
		// TODO 自动生成的方法存根
		String sql = "DELETE FROM students WHERE name=?";
		return jdbcTemplate.update(sql,name);
	}
	 */
	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#delStuByJnshu(java.lang.String, int)
	 */
	/*
	public boolean delStuByJnshu(String major, int jnshuId) {
		// TODO 自动生成的方法存根
		String sql = "DELETE FROM students WHERE id_jnshu=? and major=?";
		int line = jdbcTemplate.update(sql,new Object[] {jnshuId,major});
		return line>0?true:false;
	}
	 */
	/* （非 Javadoc）
	 * 用于日后需要添加通过其他元素删除学生记录时的情况
	 * @see cn.cage.student.StudentDAO#delStu(cn.cage.student.Student)
	 */
	public boolean delStu(Student stu) {
		// TODO 自动生成的方法存根
		return false;
	}

	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#updateStu(cn.cage.student.Student)
	 */
	public boolean updateStu(Student stu, long id) {
		// TODO 自动生成的方法存根
		String name = stu.getName(), major = stu.getMajor();
		int jnshuId = stu.getJnshuId();
		if (name==null || major==null || jnshuId==0) {
			throw new RuntimeException("updateStu：姓名、专业、学号不能为空！");
		}
		String sql = "UPDATE students SET name=?,qq=?,major=?,entrytime=?,gra_school=?,id_jnshu=?"
				+ ",daily_url=?,desire=?,bro_jnshu=?,knowfrom=? WHERE id=?";
		int line = jdbcTemplate.update(sql,new Object[]{
				name,stu.getQq(),major,stu.getEntryTime(),
				stu.getSchool(),jnshuId,stu.getDailyUrl(),stu.getDesire(),
				stu.getJnshuBro(),stu.getKnowFrom(),id});
		return line>0?true:false;
	}

	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#queryStuById(int)
	 */
	public Student queryStuById(long id) {
		// TODO 自动生成的方法存根
		String sql = "SELECT id,create_at,update_at,name,qq,major,entrytime,gra_school" + 
				",id_jnshu,daily_url,desire,bro_jnshu,knowfrom FROM students WHERE id=?";
		Student stu = null;
		try {
			stu = jdbcTemplate.queryForObject(sql, new QueryStuRowMapper(),id);
		} catch (EmptyResultDataAccessException e) {
			// TODO 此处可做更复杂的提示动作，比如抛出异常、记录到本地文件、显示到GUI等。
			System.out.println("queryStuById：该学生不存在！");
		}
		return stu;
	}

	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#queryStuByName(java.lang.String)
	 */
	public List<Student> queryStuByName(String name) {
		// TODO 自动生成的方法存根
		String sql = "SELECT id,create_at,update_at,name,qq,major,entrytime,gra_school" + 
				",id_jnshu,daily_url,desire,bro_jnshu,knowfrom FROM students WHERE name=?";
		List<Student> list = jdbcTemplate.query(sql, new QueryStuRowMapper(),name);
		if (list.size()==0) {
			System.out.println("queryStuByName：该学生不存在！");
		}
		return list;
	}

	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#queryStuByJnshu(java.lang.String, int)
	 */
	public Student queryStuByJnshu(String major, int jnshuId) {
		// TODO 自动生成的方法存根
		String sql = "SELECT id,create_at,update_at,name,qq,major,entrytime,gra_school,id_jnshu" + 
				",daily_url,desire,bro_jnshu,knowfrom FROM students WHERE id_jnshu=? and major=?";
		Student stu = null;
		try {
			stu = jdbcTemplate.queryForObject(sql, new QueryStuRowMapper(),new Object[]{jnshuId,major});
		} catch (EmptyResultDataAccessException e) {
			// TODO 此处可做更复杂的提示动作，比如抛出异常、记录到本地文件、显示到GUI等。
			System.out.println("queryStuByJnshu：该学生不存在！");
		}
		return stu;
	}

	/* （非 Javadoc）
	 * 用于日后需要添加通过其他元素查询学生记录时的情况
	 * @see cn.cage.student.StudentDAO#queryStu(cn.cage.student.Student)
	 */
	public Student queryStu(Student stu) {
		// TODO 自动生成的方法存根
		return null;
	}

}
