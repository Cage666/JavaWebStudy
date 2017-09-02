/**
 * @FileName:MybatisStuImpl.java
 * @description:
 * @author Cage Yang
 * @version 
 * Modified Date:2017年8月23日
 * Why & What is modified: <修改原因描述>
 */
package cn.cage.student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @ClassName MybatisStuImpl
 * @description 测试通过Mybatis接口绑定自动生成的数据操作类的运行情况。
 * @author Cage Yang
 */
public class MybatisStuImpl {
	static InputStream in = null;
	static SqlSession sqlSession = null;

	/**
	 * @description 创建出SqlSession实例
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String resource = "MybatisConfig.xml";
		in = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		sqlSession = sqlSessionFactory.openSession();
	}

	/**
	 * @description 释放资源
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		sqlSession.close();
		in.close();
	}

	@Test
	public void testAddStu() {
		Student stu = RandomStudent.getStudent();
		stu.setMajor("java");
		stu.setJnshuId(1501);
		assertEquals("插入失败！", 1, sqlSession.insert("addStu", stu));
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("major", "java");
		param.put("jnshuId", 1501);
		Student stu2 = sqlSession.selectOne("queryStuByJnshu", param);
		System.out.println(stu2.getCreateTime());
		assertEquals("插入错误，或查询byJnshu出错", stu, stu2);
	}

	@Test
	public void testDelStuById() {
		assertEquals("删除失败！", 1, sqlSession.delete("delStuById", 4));
		//assertNull("删除错误，或查询byId出错", sqlSession.selectOne("queryStuById", 4));
	}

	@Test
	@Ignore
	public void testUpdateStu() {
		Student stu = RandomStudent.getStudent();
		stu.setDesire("哈哈哈哈哈哈哈哈");
		assertEquals("更新失败！", 1, sqlSession.update("updateStu", new Object[] { stu, 2 }));
		assertEquals("更新错误，或查询byId出错", "哈哈哈哈哈哈哈哈", ((Student) sqlSession.selectOne("queryStuById", 2)).getDesire());
	}

	@Test
	public void testQueryStuByName() {
		List<Student> list = sqlSession.selectList("queryStuByName", "王五");
		for (Iterator<Student> iterator = list.iterator(); iterator.hasNext();) {
			Student student = (Student) iterator.next();
			if (student.getJnshuId() == 1111) {
				assertEquals("查询byName出错", "2017-08-06", student.getEntryTime());
			}
		}
	}
}
