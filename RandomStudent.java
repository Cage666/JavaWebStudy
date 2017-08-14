/**
 * @FileName:RandomStudent.java
 * @description:用于生成随机学生数据的工具类
 * @author Cage Yang
 * @version 1.1
 * Modified Date:2017年8月13日
 * Why & What is modified: 
 * 1.将getName()、getQq()中，未完成的字符串类型改为StringBuilder，否则会产生大量垃圾字符串对象。
 */
package cn.cage.student;

import static java.lang.Math.random;

import java.sql.Date;
import java.util.Calendar;

/**
 * @ClassName RandomStudent
 * @description
 * @author Cage Yang
 */
public class RandomStudent {

	/**
	 * @description 随机生成学生数据，由这些数据创建学生对象并返回。
	 * @return 生成的学生对象
	 */
	public static Student getStudent() {
		Student stu = new Student(getName(), getMajor(), getJnshuId());
		stu.setQq(getQq());
		stu.setEntryTime(getEntryTime());
		stu.setSchool(getSchool());
		stu.setDailyUrl(getDailyUrl());
		stu.setDesire(getDesire());
		stu.setJnshuBro(getJnshuBro());
		stu.setKnowFrom(getKnowFrom());
		return stu;
	}

	/**
	 * @description 随机生成学生姓名。有20种姓氏；名字长度可能有1-2位，共有6480中组合。共有129600种可能的姓名组合。
	 * @return 生成的学生姓名。
	 */
	public static String getName() {
		String[] firstNameArray = { "李", "王", "张", "刘", "陈", "杨", "赵", "黄", "周", "吴", "徐", "孙", "胡", "朱", "高", "欧阳",
				"太史", "端木", "上官", "司马" };// 20个姓，其中5个复姓
		String[] lastNameArray = { "伟", "勇", "军", "磊", "涛", "斌", "强", "鹏", "杰", "峰", "超", "波", "辉", "刚", "健", "明", "亮",
				"俊", "飞", "凯", "浩", "华", "平", "鑫", "毅", "林", "洋", "宇", "敏", "宁", "建", "兵", "旭", "雷", "锋", "彬", "龙", "翔",
				"阳", "剑", "静", "敏", "燕", "艳", "丽", "娟", "莉", "芳", "萍", "玲", "娜", "丹", "洁", "红", "颖", "琳", "霞", "婷", "慧",
				"莹", "晶", "华", "倩", "英", "佳", "梅", "雪", "蕾", "琴", "璐", "伟", "云", "蓉", "青", "薇", "欣", "琼", "宁", "平",
				"媛" };// 80个常用于名字的单字
		int firstPos = (int) (20 * random());
		StringBuilder name = new StringBuilder(firstNameArray[firstPos]);
		int lastLen = (int) (2 * random()) + 1;
		/*
		 * 为了各函数的统一性，此处也用for循环实现 int lastPos1 = (int) (80 * random()); String lastName =
		 * lastNameArray[lastPos1]; if (lastLen == 2) { int lastPos2 = (int) (80 *
		 * random()); lastName = lastName + lastNameArray[lastPos2]; }
		 */
		for (int i = 0; i < lastLen; i++) {
			int lastPos = (int) (80 * random());
			name.append(lastNameArray[lastPos]);
		}
		return name.toString();
	}

	/**
	 * @description 随机生成5~11位的QQ号。
	 * @return 生成的QQ号。
	 */
	public static String getQq() {
		char[] numArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int qqLen = (int) (7 * random()) + 5; // qq号长度可能为5~11位
		StringBuilder qq = new StringBuilder();
		for (int i = 0; i < qqLen; i++) {
			int qqPos = (int) (10 * random());
			qq.append(numArray[qqPos]);
		}
		return qq.toString();
	}

	/**
	 * @description 随机选择9种方向之一。
	 * @return 选择的学习方向。
	 */
	public static String getMajor() {
		String[] majorArray = { "css", "js", "android", "ios", "java", "op", "pm", "ui", "qa" };// 共有9种方向
		int majorPos = (int) (9 * random());
		return majorArray[majorPos];
	}

	/**
	 * @description 随机生成的入学时间，时间范围在2016.1.1~2017.12.31。
	 * @return 生成的入学时间。
	 */
	public static String getEntryTime() {
		// 先确定2016.1.1和2017.12.31对应的毫秒数，在此范围内生成long随机数，然后根据该随机数生成java.sql.Date，再toString。
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(2016, 0, 1);
		long minMillis = calendar.getTimeInMillis();
		calendar.set(2018, 0, 1);
		long maxMillis = calendar.getTimeInMillis();
		long millis = (long) ((maxMillis - minMillis) * random()) + minMillis;
		return (new Date(millis)).toString();
	}

	/**
	 * @description 随机选择10所学校之一。
	 * @return 选择的学校。
	 */
	public static String getSchool() {
		String[] schoolArray = { "北京大学", "清华大学", "武汉大学", "复旦大学", "浙江大学", "上海交通大学", "南京大学", "中国人民大学", "解放军国防科学技术大学",
				"吉林大学" };
		int schoolPos = (int) (10 * random());
		return schoolArray[schoolPos];
	}

	/**
	 * @description 随机生成的学号，取值范围在1~2147483647 （int最大正值）。
	 * @return 生成的学号。
	 */
	public static int getJnshuId() {
		return (int) (Integer.MAX_VALUE * random()) + 1;
	}

	/**
	 * @description 随机生成的日报链接：http://www.jnshu.com/daily/xxxx（int型数字，取值范围：1~2147483647
	 *              （int最大正值））。
	 * @return 生成的日报链接。
	 */
	public static String getDailyUrl() {
		return "http://www.jnshu.com/daily/" + getJnshuId();
	}

	/**
	 * @description 固定为：好好学习，天天向上！
	 * @return 生成的口号
	 */
	public static String getDesire() {
		return "好好学习，天天向上！";
	}

	/**
	 * @description 随机生成师兄姓名，通过调用getName实现。
	 * @return 生成的师兄姓名
	 */
	public static String getJnshuBro() {
		// 插入大量数据时，考虑到效率，此处可能会改为固定姓名。
		return getName();
	}

	/**
	 * @description 在三种途径中选择一个。
	 * @return 选择的了解途径。
	 */
	public static String getKnowFrom() {
		String[] knowFromArray = { "知乎", "朋友推荐", "传单/广告" };
		int knowFromPos = (int) (3 * random());
		return knowFromArray[knowFromPos];
	}
}