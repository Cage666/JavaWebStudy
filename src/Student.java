/**
 * @FileName:Student.java
 * @description:
 * @author Cage Yang
 * @version 
 * Modified Date:2017年7月27日
 * Why & What is modified: 
 * 1.添加hashCode和equals方法
 * 2.将构造方法获取的参数由int改为Interger，否则在Mybatis查询中，会出现“找不到类型为[String,String,Integer]的构造函数”的报错。
 */
package cn.cage.student;

/**
 * @ClassName Student
 * @description 描述学生信息的类。
 * @author Cage Yang 
 */
public class Student implements Comparable<Student> {
	private long id;
	private long createTime;
	private long updateTime;
	private String name;
	private String qq;
	private String major;	//学习方向
	private String entryTime;
	private String school;
	private int jnshuId;	//修真院ID
	private String dailyUrl;
	private String desire;
	private String jnshuBro;//修真院师兄
	private String knowFrom;//信息来源
	/**
	 * @param name 学生姓名
	 * @param major 学习方向
	 * @param jnshuId 修真院ID
	 */
	public Student(String name, String major, Integer jnshuId) {
		this.name = name;
		this.major = major;
		this.jnshuId = jnshuId;
	}
	/* （非 Javadoc）
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [name=" + name + ", major=" + major + ", jnshuId=" + jnshuId + "]";
	}
	/* （非 Javadoc）
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + jnshuId;
		result = prime * result + ((major == null) ? 0 : major.hashCode());
		return result;
	}
	/* （非 Javadoc）
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Student)) {
			return false;
		}
		Student other = (Student) obj;
		if (jnshuId != other.jnshuId) {
			return false;
		}
		if (major == null) {
			if (other.major != null) {
				return false;
			}
		} else if (!major.equals(other.major)) {
			return false;
		}
		return true;
	}
	/* （非 Javadoc）
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Student o) {
		// TODO 自动生成的方法存根
		if (this.id!=o.id) {	//其实相等就是指二者id都不存在（为0）的情况
			return (new Long(this.id)).compareTo(new Long(o.id));
		}
		if (!this.major.equals(o.major)) {
			return this.major.compareTo(o.major);
		}
		return (new Integer(this.jnshuId).compareTo(new Integer(o.jnshuId)));
	}
	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id 要设置的 id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return qq
	 */
	public String getQq() {
		return qq;
	}
	/**
	 * @param qq 要设置的 qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * @return major
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * @param major 要设置的 major
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * @return entryTime
	 */
	public String getEntryTime() {
		return entryTime;
	}
	/**
	 * @param entryTime 要设置的 entryTime
	 */
	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}
	/**
	 * @return school
	 */
	public String getSchool() {
		return school;
	}
	/**
	 * @param school 要设置的 school
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	/**
	 * @return jnshuId
	 */
	public int getJnshuId() {
		return jnshuId;
	}
	/**
	 * @param jnshuId 要设置的 jnshuId
	 */
	public void setJnshuId(int jnshuId) {
		this.jnshuId = jnshuId;
	}
	/**
	 * @return dailyUrl
	 */
	public String getDailyUrl() {
		return dailyUrl;
	}
	/**
	 * @param dailyUrl 要设置的 dailyUrl
	 */
	public void setDailyUrl(String dailyUrl) {
		this.dailyUrl = dailyUrl;
	}
	/**
	 * @return desire
	 */
	public String getDesire() {
		return desire;
	}
	/**
	 * @param desire 要设置的 desire
	 */
	public void setDesire(String desire) {
		this.desire = desire;
	}
	/**
	 * @return jnshuBro
	 */
	public String getJnshuBro() {
		return jnshuBro;
	}
	/**
	 * @param jnshuBro 要设置的 jnshuBro
	 */
	public void setJnshuBro(String jnshuBro) {
		this.jnshuBro = jnshuBro;
	}
	/**
	 * @return knowFrom
	 */
	public String getKnowFrom() {
		return knowFrom;
	}
	/**
	 * @param knowFrom 要设置的 knowFrom
	 */
	public void setKnowFrom(String knowFrom) {
		this.knowFrom = knowFrom;
	}
	/**
	 * @return createTime
	 */
	public long getCreateTime() {
		return createTime;
	}
	/**
	 * @return updateTime
	 */
	public long getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param createTime 要设置的 createTime
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	/**
	 * @param updateTime 要设置的 updateTime
	 */
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
