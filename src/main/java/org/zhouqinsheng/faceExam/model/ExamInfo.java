package org.zhouqinsheng.faceExam.model;


import javax.persistence.*;
import java.util.Date;
import java.util.Date;
import java.util.Date;


/**
 *
 * 考试信息
 * @author zqs
 *
 */
@Entity
@Table(name="t_exam_info")
public class ExamInfo {

	/**
	 * 考试id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * 考试名称
	 */
	@Column(name="exam_name")
	private String examName;

	/**
	 * 监考老师
	 */
	@Column(name="exam_teacher")
	private String examTeacher;

	/**
	 * 考场名称
	 */
	@Column(name="exam_room_name")
	private String examRoomName;

	/**
	 * 考场id
	 */
	@Column(name="exam_room_id")
	private int examRoomId;

	/**
	 * 监考教师id一
	 */
	@Column(name="one_teacher_id")
	private int oneTeacherId;

	/**
	 * 监考教师id二
	 */
	@Column(name="two_teacher_id")
	private int twoTeacherId;

	/**
	 * 监考教师id三
	 */
	@Column(name="three_teacher_id")
	private int threeTeacherId;
	/**
	 * 考试开始时间
	 */
	@Column(name="start_date")
	private String startDate;

	/**
	 * 考试结束时间
	 */
	@Column(name="end_date")
	private String endDate;

	/**
	 * 考试状态 1.未开始  2.进行中   0.已结束
	 */
	@Column(name="exam_status")
	private int examStatus;


	public String getExamRoomName() {
		return examRoomName;
	}

	public void setExamRoomName(String examRoomName) {
		this.examRoomName = examRoomName;
	}

	public String getExamTeacher() {
		return examTeacher;
	}

	public void setExamTeacher(String examTeacher) {
		this.examTeacher = examTeacher;
	}

	/**
	 * 获取考试id
	 * @return 考试id
	 */
	public int getId() {
		 return id;
	}

	/**
	 * 设置考试id
	 * @param id 考试id
	 */
	public void setId(int id) {
		 this.id = id;
	}


	/**
	 * 获取考场id
	 * @return 考场id
	 */
	public int getExamRoomId() {
		 return examRoomId;
	}

	/**
	 * 设置考场id
	 * @param examRoomId 考场id
	 */
	public void setExamRoomId(int examRoomId) {
		 this.examRoomId = examRoomId;
	}


	/**
	 * 获取监考教师id一
	 * @return 监考教师id一
	 */
	public int getOneTeacherId() {
		 return oneTeacherId;
	}

	/**
	 * 设置监考教师id一
	 * @param oneTeacherId 监考教师id一
	 */
	public void setOneTeacherId(int oneTeacherId) { 
		 this.oneTeacherId = oneTeacherId;
	}


	/**
	 * 获取监考教师id二
	 * @return 监考教师id二
	 */
	public int getTwoTeacherId() {
		 return twoTeacherId;
	}

	/**
	 * 设置监考教师id二
	 * @param twoTeacherId 监考教师id二
	 */
	public void setTwoTeacherId(int twoTeacherId) { 
		 this.twoTeacherId = twoTeacherId;
	}


	/**
	 * 获取考试开始时间
	 * @return 考试开始时间
	 */
	public String getStartDate() {
		 return startDate;
	}

	/**
	 * 设置考试开始时间
	 * @param startDate 考试开始时间
	 */
	public void setStartDate(String startDate) {
		 this.startDate = startDate;
	}


	/**
	 * 获取考试结束时间
	 * @return 考试结束时间
	 */
	public String getEndDate() {
		 return endDate;
	}

	/**
	 * 设置考试结束时间
	 * @param endDate 考试结束时间
	 */
	public void setEndDate(String endDate) {
		 this.endDate = endDate;
	}


	/**
	 * 获取考试状态
	 * @return 考试状态
	 */
	public int getExamStatus() {
		 return examStatus;
	}

	/**
	 * 设置考试状态
	 * @param examStatus 考试状态
	 */
	public void setExamStatus(int examStatus) {
		 this.examStatus = examStatus;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public int getThreeTeacherId() {
		return threeTeacherId;
	}

	public void setThreeTeacherId(int threeTeacherId) {
		this.threeTeacherId = threeTeacherId;
	}
}
