package org.zhouqinsheng.faceExam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhouqinsheng.faceExam.model.ExamInfo;
import org.zhouqinsheng.faceExam.repository.IExamInfoRepository;

import java.util.Date;
import java.util.List;

@Service
public class ExamInfoService implements IExamInfoService {

	@Autowired
	private IExamInfoRepository examInfoRepository;

	/**
	 * 添加考试对象
	 * 
	 */
	@Transactional
	@Override
	public void add(ExamInfo t) {
		examInfoRepository.save(t);
	}
	/**
	 * 更新考试对象
	 * 
	 */
	@Transactional
	@Override
	public void update(ExamInfo t) {
		examInfoRepository.save(t);
	}
	/**
	 * 删除考试对象
	 * 
	 */
	@Transactional
	@Override
	public void delete(Integer id) {
		examInfoRepository.delete(id);
	}
	/**
	 * 加载考试对象
	 * 
	 */
	@Override
	public ExamInfo load(Integer id) {
		 return examInfoRepository.findOne(id);
	}
	/**
	 * 获取所有的考试对象,不进行分页
	 * 
	 */
	@Override
	public List<ExamInfo> list() {
		 return examInfoRepository.findAll();
	}
	/**
	 * 获取所有的考试对象,进行分页
	 * 
	 */
	@Override
	public Page<ExamInfo> find(Pageable page) {
		 return examInfoRepository.findAll(page);
	}

	/**
	 * 通过教师id查询相关考试
	 * @param id
	 * @return
	 */
	@Override
	public List countExamByTeacherId(int id) {
		return examInfoRepository.countExamByTeacherId(id);
	}

	/**
	 * 通过教师id，查询待监考场次
	 * @param teacherId
	 * @return
	 */
	@Override
	public List findReadysExamByTeacherId(int teacherId) {
		return examInfoRepository.findReadysExamByTeacherId(teacherId);
	}

	/**
	 * 查询已结束场次
	 * @param teacherId
	 * @return
	 */
	@Override
	public List<ExamInfo> findAlreadysExamByTeacherId(int teacherId) {
		return examInfoRepository.findAlreadysExamByTeacherId(teacherId);
	}

	/**
	 * 统计待监考场次
	 * @param teacherId
	 * @return
	 */
	@Override
	public int countReadysExamByTeacherId(int teacherId) {
		return examInfoRepository.countReadysExamByTeacherId(teacherId);
	}

	/**
	 * 统计已监考场次
	 * @param teacherId
	 * @return
	 */
	@Override
	public int countAlreadysExamByTeacherId(int teacherId) {
		return examInfoRepository.countAlreadysExamByTeacherId(teacherId);
	}

	/**
	 * 查询日期在本月的考试
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Override
	public List<ExamInfo> findMonthExam(int teacherId, Date startDate, Date endDate) {
		return examInfoRepository.findMonthExam(teacherId,startDate,endDate);
	}

	/**
	 * 查询待监考场次
	 * @param teacherId
	 * @return
	 */
	@Override
	public List<ExamInfo> findReadyExam(int teacherId) {
		return examInfoRepository.findReadyExam(teacherId);
	}

	/**
	 * 查询已监考场次
	 * @param teacherId
	 * @return
	 */
	@Override
	public List<ExamInfo> findAlreadyExam(int teacherId) {
		return examInfoRepository.findAlreadyExam(teacherId);
	}

	/**
	 * 查询和教师相关的考试场次
	 * @param teacherId
	 * @return
	 */
	@Override
	public List<ExamInfo> findAllExamByTeacherId(int teacherId) {
		return examInfoRepository.findAllExamByTeacherId(teacherId);
	}
}
