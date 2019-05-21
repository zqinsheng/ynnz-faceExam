package org.zhouqinsheng.faceExam.model;


/**
 *
 * 传输封装考试信息
 * @author zqs
 *
 */
public class ExamInfoDto {

	private ExamInfo examInfo;
	private int studentCount;
	private String examTime;

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public ExamInfo getExamInfo() {
		return examInfo;
	}

	public void setExamInfo(ExamInfo examInfo) {
		this.examInfo = examInfo;
	}

	public int getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}
}
