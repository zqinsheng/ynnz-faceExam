package org.zhouqinsheng.faceExam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhouqinsheng.faceExam.model.ExamAddStudent;
import org.zhouqinsheng.faceExam.model.ExamPerson;
import org.zhouqinsheng.faceExam.repository.IExamAddStudentRepository;
import org.zhouqinsheng.faceExam.repository.IExamPersonRepository;

import java.util.List;

@Service
public class ExamAddStudentService implements IExamAddStudentService {

	@Autowired
	private IExamAddStudentRepository examAddStudentRepository;

	/**
	 * 添加考生对象
	 * 
	 */
	@Transactional
	@Override
	public void add(ExamAddStudent t) {
		examAddStudentRepository.save(t);
	}
	/**
	 * 更新考生对象
	 * 
	 */
	@Transactional
	@Override
	public void update(ExamAddStudent t) {
		examAddStudentRepository.save(t);
	}
	/**
	 * 删除考生对象
	 * 
	 */
	@Transactional
	@Override
	public void delete(Integer id) {
		examAddStudentRepository.delete(id);
	}
	/**
	 *
	 * 加载考生对象
	 * 
	 */
	@Override
	public ExamAddStudent load(Integer id) {
		 return examAddStudentRepository.findOne(id);
	}
	/**
	 * 获取所有的考生对象,不进行分页
	 * 
	 */
	@Override
	public List<ExamAddStudent> list() {
		 return examAddStudentRepository.findAll();
	}
	/**
	 * 获取所有的考生对象,进行分页
	 * 
	 */
	@Override
	public Page<ExamAddStudent> find(Pageable page) {
		 return examAddStudentRepository.findAll(page);
	}

	/**
	通过考试id进行分页
	 */
	@Override
	public Page<ExamAddStudent> findAllByExamId(Pageable page, int examId) {
		return  examAddStudentRepository.findAllByExamId(examId,page);
	}

	/**
	 * 清空所有考试关联的学生
	 * @param examId
	 */

	@Transactional
	@Override
	public int deleteAll(int examId) {
		  return examAddStudentRepository.deleteByExamInfoId(examId);
	}
}
