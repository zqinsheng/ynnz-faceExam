package org.zhouqinsheng.faceExam.model;


import javax.persistence.*;


/**
 *
 * 考生信息
 * @author zqs
 *
 */
@Entity
@Table(name="t_exam_person")
public class ExamPerson {

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * 姓名
	 */
	private String personName;

	/**
	 * 性别
	 */
	private int gender;

	/**
	 * 身份证号
	 */
	@Column(name="id_card")
	private String idCard;

	/**
	 * 学号
	 */
	@Column(name = "stu_number")
	private String stuNumber;

	//学院
	private String college;

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}



	/**
	 * 获取id
	 * @return id
	 */
	public int getId() {
		 return id;
	}

	/**
	 * 设置id
	 * @param id id
	 */
	public void setId(int id) {
		 this.id = id;
	}


	/**
	 * 获取姓名
	 * @return 姓名
	 */
	public String getPersonName() {
		 return personName;
	}

	/**
	 * 设置姓名
	 * @param personName 姓名
	 */
	public void setPersonName(String personName) { 
		 this.personName = personName;
	}


	/**
	 * 获取性别
	 * @return 性别
	 */
	public int getGender() {
		 return gender;
	}

	/**
	 * 设置性别
	 * @param gender 性别
	 */
	public void setGender(int gender) {
		 this.gender = gender;
	}


	/**
	 * 获取身份证号
	 * @return 身份证号
	 */
	public String getIdCard() {
		 return idCard;
	}

	/**
	 * 设置身份证号
	 * @param idCard 身份证号
	 */
	public void setIdCard(String idCard) { 
		 this.idCard = idCard;
	}


	/**
	 * 获取学号
	 * @return 学号
	 */
	public String getStuNumber() {
		 return stuNumber;
	}

	/**
	 * 设置学号
	 * @param stuNumber 学号
	 */
	public void setStuNumber(String stuNumber) {
		 this.stuNumber = stuNumber;
	}




}
