package org.zhouqinsheng.faceExam.model;


import javax.persistence.*;


/**
 *
 * 关联考试考生
 * @author zqs
 *
 */
@Entity
@Table(name="t_exam_add_student")
public class ExamAddStudent {

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

	//考试id
	@Column(name = "exam_info_id")
	private int examInfoId;

	/**
	 * 班级
	 * @return
	 */
	@Column(name = "classes_name")
	private  String classesName;

	/**
	 * 上传头像路径
	 * @return
	 */
	@Column(name = "image_url")
	private  String imageUrl;

	/**
	 *考试状态，1-成功考试，0-没有成功
	 * @return
	 */
	@Column(name = "exam_status")
	private int examStatus;

	/**
	 * 刷脸时间
	 */
	@Column(name="exam_time")
	private String examTime;


	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public int getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(int examStatus) {
		this.examStatus = examStatus;
	}

	public String getClassesName() {
		return classesName;
	}

	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

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

    public int getExamInfoId() {
        return examInfoId;
    }

    public void setExamInfoId(int examInfoId) {
        this.examInfoId = examInfoId;
    }
}
