package org.zhouqinsheng.faceExam.repository;

import org.konghao.reposiotry.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.zhouqinsheng.faceExam.model.ExamAddStudent;

public interface IExamAddStudentRepository extends BaseRepository<ExamAddStudent,Integer>,JpaSpecificationExecutor<ExamAddStudent> {


    @Query(value = "select * from t_exam_add_student where exam_info_id = ?1 ORDER BY ?#{#pageable}",
          countQuery = "select count(*) from t_exam_add_student where exam_info_id = ?1",
          nativeQuery = true)
    Page<ExamAddStudent> findAllByExamId(int examId,Pageable pageable);


    @Transactional
    @Modifying
    int deleteByExamInfoId(Integer integer);
}