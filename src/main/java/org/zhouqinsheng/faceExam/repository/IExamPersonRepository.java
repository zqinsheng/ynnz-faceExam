package org.zhouqinsheng.faceExam.repository;

import org.springframework.data.jpa.repository.Query;
import org.zhouqinsheng.faceExam.model.ExamPerson;
import org.konghao.reposiotry.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IExamPersonRepository extends BaseRepository<ExamPerson,Integer>,JpaSpecificationExecutor<ExamPerson> {

    @Query(value="select * from t_exam_person where stu_number=?1",nativeQuery=true)
    ExamPerson findByNumber(String stuNumber);
}