package org.zhouqinsheng.faceExam.repository;

import org.springframework.data.jpa.repository.Query;
import org.zhouqinsheng.faceExam.model.ExamInfo;
import org.konghao.reposiotry.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IExamInfoRepository extends BaseRepository<ExamInfo,Integer>,JpaSpecificationExecutor<ExamInfo> {

    @Query(value = "SELECT exam_status,COUNT(*) from t_exam_info WHERE " +
            "one_teacher_id IN(?1) OR two_teacher_id IN(?1) " +
            "or three_teacher_id IN(?1) GROUP BY exam_status",
            nativeQuery = true)
    List countExamByTeacherId(int teacherId);
}