package org.zhouqinsheng.faceExam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhouqinsheng.faceExam.model.TeacherInfo;
import org.zhouqinsheng.faceExam.repository.ITeacherInfoRepository;

import java.util.List;

@Service
public class TeacherInfoService implements ITeacherInfoService {

	@Autowired
	private ITeacherInfoRepository teacherInfoRepository;

	/**
	 * 添加教师对象
	 * 
	 */
	@Transactional
	@Override
	public void add(TeacherInfo t) {
		teacherInfoRepository.save(t);
	}
	/**
	 * 更新教师对象
	 * 
	 */
	@Transactional
	@Override
	public void update(TeacherInfo t) {
		teacherInfoRepository.save(t);
	}
	/**
	 * 删除教师对象
	 * 
	 */
	@Transactional
	@Override
	public void delete(Integer id) {
		teacherInfoRepository.delete(id);
	}
	/**
	 * 加载教师对象
	 * 
	 */
	@Override
	public TeacherInfo load(Integer id) {
		 return teacherInfoRepository.findOne(id);
	}
	/**
	 * 获取所有的教师对象,不进行分页
	 * 
	 */
	@Override
	public List<TeacherInfo> list() {
		 return teacherInfoRepository.findAll();
	}
	/**
	 * 获取所有的教师对象,进行分页
	 * 
	 */
	@Override
	public Page<TeacherInfo> find(Pageable page) {
		 return teacherInfoRepository.findAll(page);
	}

	/**
	 * 通过工号查找教师
	 * @param jobNumer
	 * @return
	 */
	@Override
	public TeacherInfo findByJobNumber(String jobNumer) {
		return teacherInfoRepository.findByJobNumber(jobNumer);
	}

	/**
	 * 通过openid查找用户
	 * @param openId
	 * @return
	 */
	@Override
	public TeacherInfo findByOpenId(String openId) {
		return teacherInfoRepository.findByOpenId(openId);
	}


}
