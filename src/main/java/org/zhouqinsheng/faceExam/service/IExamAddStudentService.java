package org.zhouqinsheng.faceExam.service;

import org.konghao.service.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zhouqinsheng.faceExam.model.ExamAddStudent;

import java.util.Date;
import java.util.List;

/**
 * 考试考生Service的实现
 * 默认实现了CRUD方法，add:添加,update:更新,delete:删除,load:加载一个对象,list:列表对象不分页,find:分页列表对象
 * @author zqs
 * 
 */
public interface IExamAddStudentService extends IBaseService<ExamAddStudent,Integer> {

     Page<ExamAddStudent> findAllByExamId(Pageable page,int examId);

     int deleteAll(int examId);

     int countByExamId(int examId);

     List<ExamAddStudent> findSucceByExamId(int examId);

     List<ExamAddStudent> findFailByExamId(int examId);

     int countSucceByExamId(int examId);

     int countFailByExamId(int examId);

     List<ExamAddStudent> findSucceStuByName(int examId,String name);

     List<ExamAddStudent> findFailStuByName(int examId,String name);

     int countMonthFace(Date startDate, Date endDate);
}