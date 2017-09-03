/**
 * @FileName:StudentDAO.java
 * @description:
 * @author Cage Yang
 * @version 
 * Modified Date:2017年7月27日
 * Why & What is modified: <修改原因描述>
 */
package cn.cage.student;

/**
 * @ClassName StudentDAO
 * @description DAO工厂类，用于创建数据操作对象。
 * @author Cage Yang 
 */
public class DAOFactory {
	public static StudentDAO getDAOImpl() {
		return new StudentDAOSpringImpl();
	}
}