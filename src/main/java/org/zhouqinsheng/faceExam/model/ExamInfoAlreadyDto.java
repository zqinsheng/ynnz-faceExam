package org.zhouqinsheng.faceExam.model;

public class ExamInfoAlreadyDto {
    //考试信息
    private ExamInfo examInfo;
    //总共考生人数
    private int studentCount;
    //成功人数
    private int countSuccess;
    //失败人数
    private int countFail;
    //考试时间
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

    public int getCountSuccess() {
        return countSuccess;
    }

    public void setCountSuccess(int countSuccess) {
        this.countSuccess = countSuccess;
    }

    public int getCountFail() {
        return countFail;
    }

    public void setCountFail(int countFail) {
        this.countFail = countFail;
    }
}
