/**
 * @FileName:QueryStuRowMapper.java
 * @description:
 * @author Cage Yang
 * @version 
 * Modified Date:2017年8月4日
 * Why & What is modified: 
 * 1.给entrytime的设定语句加上非空判断，否则当其为空时，null.toString抛出空指针异常。
 */
package cn.cage.student;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @ClassName QueryStuRowMapper
 * @description 直接使用此类建立的对象时，sql语句应查询Student的所有属性。
 * @author Cage Yang 
 */
public class QueryStuRowMapper implements RowMapper<Student> {
	/**
	 * 直接使用此类建立的对象时，sql语句应查询Student的所有属性。
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO 自动生成的方法存根
		Student stu = new Student(rs.getString("name"), rs.getString("major"), rs.getInt("id_jnshu"));
		stu.setId(rs.getLong("id"));
		stu.setCreateTime(rs.getLong("create_at"));
		stu.setUpdateTime(rs.getLong("update_at"));
		stu.setQq(rs.getString("qq"));
		// TODO 从Mysql中获取的Date值，在java中是java.sql.Date，toString的格式为YYYY-MM-dd。
		Date entryTime = rs.getDate("entrytime");
		if (entryTime!=null) {
			stu.setEntryTime(entryTime.toString());
		}
		stu.setSchool(rs.getString("gra_school"));
		stu.setDailyUrl(rs.getString("daily_url"));
		stu.setDesire(rs.getString("desire"));
		stu.setJnshuBro(rs.getString("bro_jnshu"));
		stu.setKnowFrom(rs.getString("knowfrom"));
		return stu;
	}
}
