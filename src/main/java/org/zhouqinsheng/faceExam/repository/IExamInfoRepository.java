package org.zhouqinsheng.faceExam.repository;

import org.springframework.data.jpa.repository.Query;
import org.zhouqinsheng.faceExam.model.ExamInfo;
import org.konghao.reposiotry.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface IExamInfoRepository extends BaseRepository<ExamInfo,Integer>,JpaSpecificationExecutor<ExamInfo> {

    @Query(value = "SELECT exam_status,COUNT(*) from t_exam_info WHERE " +
            "one_teacher_id=?1 OR two_teacher_id=?1 " +
            "or three_teacher_id=?1 GROUP BY exam_status",
            nativeQuery = true)
    List countExamByTeacherId(int teacherId);


    @Query(value = "select * from t_exam_info where (one_teacher_id=?1 " +
            "or two_teacher_id=?1 or three_teacher_id=?1) AND exam_status = 1",
    nativeQuery = true)
    List<ExamInfo> findReadysExamByTeacherId(int teacherId);

    @Query(value = "select * from t_exam_info where (one_teacher_id=?1 " +
            "or two_teacher_id=?1 or three_teacher_id=?1) AND exam_status = 0",
            nativeQuery = true)
    List<ExamInfo> findAlreadysExamByTeacherId(int teacherId);


    @Query(value = "select COUNT(*) from t_exam_info where (one_teacher_id=?1 " +
            "or two_teacher_id=?1 or three_teacher_id=?1) AND exam_status = 1",
            nativeQuery = true)
    int countReadysExamByTeacherId(int teacherId);

    @Query(value = "select COUNT(*) from t_exam_info where (one_teacher_id=?1 " +
            "or two_teacher_id=?1 or three_teacher_id=?1) AND exam_status = 0",
            nativeQuery = true)
    int countAlreadysExamByTeacherId(int teacherId);


    @Query(value = "select * from t_exam_info where (one_teacher_id=?1 or two_teacher_id=?1 " +
            "or three_teacher_id=?1) AND exam_status = 0 " +
            "and DATE_FORMAT( end_date, '%Y-%m-%d') >?2 " +
            "and DATE_FORMAT( end_date, '%Y-%m-%d')<?3",
            nativeQuery = true)
    List<ExamInfo> findMonthExam(int teacherId, Date startDate, Date endDate);
}