package org.zhouqinsheng.faceExam.model;


import javax.persistence.*;


/**
 * 监考老师
 *
 * @author zqs
 */
@Entity
@Table(name = "t_teacher_info")
public class TeacherInfo {

    /**
     * 老师id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * 教师名称
     */
    @Column(name = "teacher_name")
    private String teacherName;

    /**
     * 教师性别:1男2女
     */
    @Column(name = "gender")
    private int gender;


    //老师电话号码
    private String phone;

    //年龄
    private String age;

    //工号
    @Column(name = "job_number")
    private String jobNumber;

    //学院
    private String college;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取老师id
     *
     * @return 老师id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置老师id
     *
     * @param id 老师id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * 获取教师名称
     *
     * @return 教师名称
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * 设置教师名称
     *
     * @param teacherName 教师名称
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }


    /**
     * 获取教师性别:1男2女
     *
     * @return 教师性别:1男2女
     */
    public int getGender() {
        return gender;
    }

    /**
     * 设置教师性别:1男2女
     *
     * @param gender 教师性别:1男2女
     */
    public void setGender(int gender) {
        this.gender = gender;
    }


}
