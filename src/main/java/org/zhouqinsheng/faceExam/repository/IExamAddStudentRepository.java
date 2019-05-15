package org.zhouqinsheng.faceExam.repository;

import org.konghao.reposiotry.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.zhouqinsheng.faceExam.model.ExamAddStudent;
import org.zhouqinsheng.faceExam.model.ExamPerson;

public interface IExamAddStudentRepository extends BaseRepository<ExamAddStudent,Integer>,JpaSpecificationExecutor<ExamAddStudent> {


}