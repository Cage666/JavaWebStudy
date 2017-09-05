/**
 * @FileName:StuDataOperate2.java
 * @description:主类：向数据库中插入X条数据。
 * @author Cage Yang
 * @version 
 * Modified Date:2017年9月4日
 * Why & What is modified: <修改原因描述>
 */
package cn.cage.student.operate;

import java.util.ArrayList;
import java.util.List;

import cn.cage.student.RandomStudent;
import cn.cage.student.dao.DAOFactory;
import cn.cage.student.dao.StudentDAO;
import cn.cage.student.pojos.Student;



/**
 * @ClassName StuDataOperate2
 * @description 主类：向数据库中插入X条数据。
 * @author Cage Yang
 */
public class StuDataOperate2 {

	/**
	 * @description 生成学生数据，然后插入数据库。 1.生成1万条数据，存入集合中 2.将集合中的数据一次性批处理插入到数据库中
	 *              3.创建10个线程，每个线程执行上述操作10次。
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		long start = System.currentTimeMillis();
		Thread t1 = new Thread(new MyThread());
		Thread t2 = new Thread(new MyThread());
		Thread t3 = new Thread(new MyThread());
		Thread t4 = new Thread(new MyThread());
		Thread t5 = new Thread(new MyThread());
		Thread t6 = new Thread(new MyThread());
		Thread t7 = new Thread(new MyThread());
		Thread t8 = new Thread(new MyThread());
		Thread t9 = new Thread(new MyThread());
		Thread t10 = new Thread(new MyThread());
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			t8.join();
			t9.join();
			t10.join();
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

}

class MyThread implements Runnable {

	/*
	 * （非 Javadoc）
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO 自动生成的方法存根
		StudentDAO dao = DAOFactory.getDAOImpl();
		for (int i = 0; i < 10; i++) {
			List<Student> list = new ArrayList<Student>();
			for (int j = 0; j < 10000; j++) {
				list.add(RandomStudent.getStudent());
			}
			dao.addStu(list);
		}
	}

}