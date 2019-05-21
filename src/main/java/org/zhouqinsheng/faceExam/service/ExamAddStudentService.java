package org.zhouqinsheng.faceExam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhouqinsheng.faceExam.model.ExamAddStudent;
import org.zhouqinsheng.faceExam.repository.IExamAddStudentRepository;

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

	/**
	 * 统计考试相关的学生人数
	 * @param examId
	 * @return
	 */
	@Override
	public int countByExamId(int examId) {
		return examAddStudentRepository.countByExamInfoId(examId);
	}

	/**
	 * 刷脸成功的学生
	 * @param examId
	 * @return
	 */
	@Override
	public List<ExamAddStudent> findSucceByExamId(int examId) {
		return examAddStudentRepository.findSucceByExamId(examId);
	}

	/**
	 * 刷脸失败的学生
	 * @param examId
	 * @return
	 */
	@Override
	public List<ExamAddStudent> findFailByExamId(int examId) {
		return examAddStudentRepository.findFailByExamId(examId);
	}

	/**
	 * 统计成功学生人数
	 * @param examId
	 * @return
	 */
	@Override
	public int countSucceByExamId(int examId) {
		return examAddStudentRepository.countSucceByExamId(examId);
	}

	/**
	 * 统计失败学生人数
	 * @param examId
	 * @return
	 */
	@Override
	public int countFailByExamId(int examId) {
		return examAddStudentRepository.countFailByExamId(examId);
	}

	/**
	 * 通过姓名模糊查询成功学生
	 * @param examId
	 * @param name
	 * @return
	 */
	@Override
	public List<ExamAddStudent> findSucceStuByName(int examId, String name) {
		return examAddStudentRepository.findSucceStuByName(name,examId);
	}

	/**
	 * 通过姓名查询失败学生
	 * @param examId
	 * @param name
	 * @return
	 */
	@Override
	public List<ExamAddStudent> findFailStuByName(int examId, String name) {
		return examAddStudentRepository.findFailStuByName(name,examId);
	}
}
