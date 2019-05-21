package org.zhouqinsheng.faceExam.repository;

import org.konghao.reposiotry.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.zhouqinsheng.faceExam.model.ExamAddStudent;

import java.util.Date;
import java.util.List;

public interface IExamAddStudentRepository extends BaseRepository<ExamAddStudent,Integer>,JpaSpecificationExecutor<ExamAddStudent> {


    @Query(value = "select * from t_exam_add_student where exam_info_id = ?1 ORDER BY ?#{#pageable}",
          countQuery = "select count(*) from t_exam_add_student where exam_info_id = ?1",
          nativeQuery = true)
    Page<ExamAddStudent> findAllByExamId(int examId,Pageable pageable);


    @Transactional
    @Modifying
    int deleteByExamInfoId(Integer integer);

    int countByExamInfoId(Integer examInfoId);

    @Query(value = "select * from t_exam_add_student where exam_info_id=?1 and exam_status=1",
    nativeQuery = true)
    List<ExamAddStudent> findSucceByExamId(int examInfoId);

    @Query(value = "select * from t_exam_add_student where exam_info_id=?1 and exam_status=0",
            nativeQuery = true)
    List<ExamAddStudent> findFailByExamId(int examInfoId);

    @Query(value = "select count(*) from t_exam_add_student where exam_info_id=?1 and exam_status=1",
    nativeQuery = true)
    int countSucceByExamId(int examInfoId);

    @Query(value = "select count(*) from t_exam_add_student where exam_info_id=?1 and exam_status=0",
            nativeQuery = true)
    int countFailByExamId(int examInfoId);

    @Query(value = "SELECT * from t_exam_add_student where person_name LIKE %?1% and exam_info_id=?2 and exam_status=1",
            nativeQuery = true)
    List<ExamAddStudent> findSucceStuByName(String name,int examInfoId);

    @Query(value = "SELECT * from t_exam_add_student where person_name LIKE %?1% and exam_info_id=?2 and exam_status=0",
            nativeQuery = true)
    List<ExamAddStudent> findFailStuByName(String name,int examInfoId);

    @Query(value = "select COUNT(*) from t_exam_add_student where " +
            "DATE_FORMAT( exam_time, '%Y-%m-%d')>?1 " +
            "and  DATE_FORMAT( exam_time, '%Y-%m-%d')<=?2",nativeQuery = true)
    int countMonthFace(Date startDate, Date endDate);
}