package org.zhouqinsheng.faceExam.model;


import javax.persistence.*;
import java.util.Date;


/**
 *
 * 考场信息
 * @author zqs
 *
 */
@Entity
@Table(name="t_exam_room")
public class ExamRoom {

	/**
	 * 考场id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	//考成类型1.教室  2.机房
	@Column(name = "room_type")
	private int roomType;

	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}
/**
	 * 考场名称

	@Column(name="exam_name")
	private String examName;
	 */

	/**
	 * 考试地点
	 */
	@Column(name="exam_address")
	private String examAddress;

	/**
	 * 考场人数
	 */
	@Column(name="person_num")
	private int personNum;

	/**
	 * 获取考场id
	 * @return 考场id
	 */
	public int getId() {
		 return id;
	}

	/**
	 * 设置考场id
	 * @param id 考场id
	 */
	public void setId(int id) {
		 this.id = id;
	}


	/**
	 * 获取考场名称
	 * @return 考场名称

	public String getExamName() {
		 return examName;
	}
	 */

	/**
	 * 设置考场名称
	 * @param examName 考场名称

	public void setExamName(String examName) { 
		 this.examName = examName;
	}
	 */


	/**
	 * 获取考试地点
	 * @return 考试地点
	 */
	public String getExamAddress() {
		 return examAddress;
	}

	/**
	 * 设置考试地点
	 * @param examAddress 考试地点
	 */
	public void setExamAddress(String examAddress) { 
		 this.examAddress = examAddress;
	}


	/**
	 * 获取考场人数
	 * @return 考场人数
	 */
	public int getPersonNum() {
		 return personNum;
	}

	/**
	 * 设置考场人数
	 * @param personNum 考场人数
	 */
	public void setPersonNum(int personNum) {
		 this.personNum = personNum;
	}


}
