/**
 * @FileName:StuDaoImplTest.java
 * @description:
 * @author Cage Yang
 * @version 
 * Modified Date:2017年8月8日
 * Why & What is modified: <修改原因描述>
 */
package cn.cage.student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @ClassName StuDaoImplTest
 * @description 
 * @author Cage Yang 
 */
public class StuDaoImplTest {
	private static StudentDAOImpl stuDao = null;
	/**
	 * @description 
	 * @throws java.lang.Exception 
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stuDao = new StudentDAOImpl();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		stuDao = null;
	}

	/**
	 * {@link cn.cage.student.StudentDAOImpl#addStu(cn.cage.student.Student)} 的测试方法。
	 */
	@Test
	public void testAddStu() {
		Student stu = RandomStudent.getStudent();
		stu.setEntryTime("1995-8-6");
		assertTrue("插入失败！",stuDao.addStu(stu));
		assertEquals("插入错误，或查询byJnshu出错", stu, stuDao.queryStuByJnshu("Java后端工程师", 1501));
	}

	/**
	 * {@link cn.cage.student.StudentDAOImpl#delStuById(long)} 的测试方法。
	 */
	@Test
	public void testDelStuById() {
		assertTrue("删除失败！",stuDao.delStuById(3));
		assertNull("删除错误，或查询byId出错", stuDao.queryStuById(3));
	}

	/**
	 * {@link cn.cage.student.StudentDAOImpl#updateStu(cn.cage.student.Student, long)} 的测试方法。
	 */
	@Test
	public void testUpdateStu() {
		Student stu = RandomStudent.getStudent();
		stu.setDesire("哈哈哈哈哈哈哈哈");
		assertTrue("更新失败！", stuDao.updateStu(stu, 2));
		assertEquals("更新错误，或查询byId出错", "哈哈哈哈哈哈哈哈", stuDao.queryStuById(2).getDesire());
	}

	/**
	 * {@link cn.cage.student.StudentDAOImpl#queryStuByName(java.lang.String)} 的测试方法。
	 */
	@Test
	public void testQueryStuByName() {
		List<Student> list = stuDao.queryStuByName("王五");
		for (Iterator<Student> iterator = list.iterator(); iterator.hasNext();) {
			Student student = (Student) iterator.next();
			if (student.getJnshuId()==1509) {
				assertEquals("查询byName出错", "2017-08-06", student.getEntryTime());
			}
		}
	}
}