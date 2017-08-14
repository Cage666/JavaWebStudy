/**
 * @FileName:DataOperate.java
 * @description:
 * @author Cage Yang
 * @version 
 * Modified Date:2017年8月4日
 * Why & What is modified: 
 * 1.将updateStu函数的返回值由Student修改为boolean，参数列表增加long id。
 * 		原因：不需要获取旧学生信息；由调用者提供id参数更好。
 * 2.修改queryStuByName的返回值为List<Student>，因为可能出现的同名学生。
 * 3.修改delStuByName的返回值为int，因为可能出现的同名学生。
 * 4.删除delStuByName和delStuByJnshu两个方法。
 *	  原因：在实际业务逻辑中一般是先查询，然后根据查询结果选择删除。既然是这样，只需要根据选择项的id删除即可，不会出现误删的问题。

 */
package cn.cage.student;

import java.util.List;

/**
 * @ClassName DataOperate
 * @description 数据操作接口，规定了对学生对象数据的增删改查四种操作。
 * @author Cage Yang 
 */
public interface StudentDAO {
	//增
	public abstract boolean addStu(Student stu);
	
	//删
	public abstract boolean delStuById(long id);
//	public abstract int delStuByName(String name);
//	public abstract boolean delStuByJnshu(String major, int jnshuId);
	public abstract boolean delStu(Student stu);
	
	//改
	public abstract boolean updateStu(Student stu, long id);
	
	//查
	public abstract Student queryStuById(long id);
	public abstract List<Student> queryStuByName(String name);
	public abstract Student queryStuByJnshu(String major, int jnshuId);
	public abstract Student queryStu(Student stu);
}
