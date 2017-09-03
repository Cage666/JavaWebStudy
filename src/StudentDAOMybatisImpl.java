/**
 * @FileName:StudentDAOMybatisImpl.java
 * @description:
 * @author Cage Yang
 * @version 
 * Modified Date:2017年9月4日
 * Why & What is modified: <修改原因描述>
 */
package cn.cage.student;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @ClassName StudentDAOMybatisImpl
 * @description 
 * @author Cage Yang 
 */
public class StudentDAOMybatisImpl implements StudentDAO {
	
	private InputStream inputStream = null;
	private SqlSession sqlSession = null;
	
	/**
	 *  创建的对象的同时获取sqlSession对象
	 */
	public StudentDAOMybatisImpl() {
		String resource = "MybatisConfig.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		sqlSession = sqlSessionFactory.openSession();
	}
	
	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#addStu(cn.cage.student.Student)
	 */
	public boolean addStu(Student stu) {
		// TODO 自动生成的方法存根
		int line = sqlSession.insert("addStu", stu);
		sqlSession.commit();
		return line>0?true:false;
	}

	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#delStuById(long)
	 */
	public boolean delStuById(long id) {
		// TODO 自动生成的方法存根
		int line = sqlSession.delete("delStuById", id);
		sqlSession.commit();
		return line>0?true:false;
	}

	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#delStu(cn.cage.student.Student)
	 */
	public boolean delStu(Student stu) {
		// TODO 自动生成的方法存根
		return false;
	}

	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#updateStu(cn.cage.student.Student, long)
	 */
	public boolean updateStu(Student stu, long id) {
		// TODO 自动生成的方法存根
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("stu", stu);
		param.put("id", id);
		int line = sqlSession.update("updateStu", param);
		sqlSession.commit();
		return line>0?true:false;
	}

	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#queryStuById(long)
	 */
	public Student queryStuById(long id) {
		// TODO 自动生成的方法存根
		return sqlSession.selectOne("queryStuById", id);
	}

	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#queryStuByName(java.lang.String)
	 */
	public List<Student> queryStuByName(String name) {
		// TODO 自动生成的方法存根
		return sqlSession.selectList("queryStuByName", name);
	}

	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#queryStuByJnshu(java.lang.String, int)
	 */
	public Student queryStuByJnshu(String major, int jnshuId) {
		// TODO 自动生成的方法存根
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("major", major);
		param.put("jnshuId", jnshuId);
		return sqlSession.selectOne("queryStuByJnshu", param);
	}

	/* （非 Javadoc）
	 * @see cn.cage.student.StudentDAO#queryStu(cn.cage.student.Student)
	 */
	public Student queryStu(Student stu) {
		// TODO 自动生成的方法存根
		return null;
	}

	public void close() {
		try {
			if (inputStream!=null) {
				inputStream.close();
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if (sqlSession!=null) {
			sqlSession.close();
		}
	}
}
