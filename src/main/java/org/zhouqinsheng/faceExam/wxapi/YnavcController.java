package org.zhouqinsheng.faceExam.wxapi;

import org.apache.commons.beanutils.BeanUtils;
import org.konghao.reposiotry.kit.SimplePageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zhouqinsheng.faceExam.apiTools.ResultTools;
import org.zhouqinsheng.faceExam.model.*;
import org.zhouqinsheng.faceExam.service.IExamAddStudentService;
import org.zhouqinsheng.faceExam.service.IExamInfoService;
import org.zhouqinsheng.faceExam.service.ITeacherInfoService;
import org.zhouqinsheng.faceExam.service.IUserInfoService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("api/ynavc")
public class YnavcController {

	@Autowired
	private IUserInfoService userInfoService;

	@Autowired
	private ITeacherInfoService teacherInfoService;

	@Autowired
    private IExamInfoService examInfoService;

	@Autowired
	private IExamAddStudentService examAddStudentService;

	/**
	 * 教师登录，通过教工号查询教师
	 * @param jobNumber
	 * @return
	 */
	@RequestMapping(value="/login/{jobNumber}",method=RequestMethod.POST)
	public ResultModel login(@PathVariable String jobNumber) {
		try {
			if (jobNumber==null){
				return ResultTools.result(1001,"",null);
			}

			TeacherInfo teacher = teacherInfoService.findByJobNumber(jobNumber);
			Map<String,Object> map = new HashMap<String,Object>();
			if (teacher!=null){
				map.put("content",teacher);
				return ResultTools.result(0, "", map);
			}

			return ResultTools.result(1002, "", null);

		} catch (Exception e) {
			return ResultTools.result(404, e.getMessage(), null);
		}
	}

    /**
     * 绑定微信用户的openId
     * @param jobNumber
     * @param openId
     * @return
     */
	@RequestMapping(value="/addTeacher/{jobNumber}/{openId}",method=RequestMethod.POST)
	public String addTeacher(@PathVariable("jobNumber") String jobNumber,
                             @PathVariable("openId") String openId) {
        TeacherInfo teacher = teacherInfoService.findByJobNumber(jobNumber);
        teacher.setOpenId(openId);
        teacherInfoService.add(teacher);
		return "1";
	}

	/**
	 * 统计教师的待监考和未监考,本月核验人数，成功，失败
	 *
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "/examCount/{openId}", method = RequestMethod.GET)
	public ResultModel examCount(@PathVariable("openId") String openId) {
		try {
			//教师信息
			TeacherInfo teacher = teacherInfoService.findByOpenId(openId);
			//待监考和未监考
			List list = examInfoService.countExamByTeacherId(teacher.getId());

			/**查询本月已经监考的场次*/
			Calendar cale = null;
			// 获取当月第一天和最后一天
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String firstday, lastday;
			// 获取月的第一天
			cale = Calendar.getInstance();
			cale.add(Calendar.MONTH, 0);
			cale.set(Calendar.DAY_OF_MONTH, 1);
			firstday = format.format(cale.getTime());
			// 获取月的最后一天
			cale = Calendar.getInstance();
			cale.add(Calendar.MONTH, 1);
			cale.set(Calendar.DAY_OF_MONTH, 0);
			lastday = format.format(cale.getTime());

			Date startDate = format.parse(firstday);
			Date endDate = format.parse(lastday);
			/**统计本月考试*/
			List<ExamInfo> monthExam = examInfoService.findMonthExam(teacher.getId(), startDate, endDate);
			int seccessCount = 0;
			int failCount = 0;

			//统计每场考试成功和失败的人数
			for (ExamInfo e:monthExam){
				seccessCount += examAddStudentService.countSucceByExamId(e.getId());
				failCount += examAddStudentService.countFailByExamId(e.getId());
			}
			int total = seccessCount+failCount;

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("content", list);
			map.put("teacher", teacher);
			map.put("total",total);
			map.put("seccessCount",seccessCount);
			map.put("failCount",failCount);

			return ResultTools.result(0, "", map);
		} catch (ParseException e) {
			return ResultTools.result(404,e.getMessage(), null);
		}
	}


	/**
	 *查找一个教师的信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/findTeacher/{id}",method=RequestMethod.POST)
	public ResultModel findTeacher(@PathVariable int id) {
		try {
			TeacherInfo teacher = teacherInfoService.load(id);
			Map<String,Object> map = new HashMap<String,Object>();
			if (teacher!=null){
				map.put("content",teacher);
				return ResultTools.result(0, "", map);
			}

			return ResultTools.result(1002, "", null);

		} catch (Exception e) {
			return ResultTools.result(404, e.getMessage(), null);
		}
	}

	/**
	 * 修改教师信息
	 * @param id
	 * @param teacherName
	 * @param gender
	 * @param age
	 * @param phone
	 * @param jobNumber
	 * @param college
	 * @return
	 */
	@RequestMapping(value="/updateTeacher/{id}/{teacherName}/{sex}/{age}/{phone}/{jobNumber}/{college}",method=RequestMethod.POST)
	public String updateTeacher(@PathVariable("id") int id,@PathVariable("teacherName") String teacherName,
									 @PathVariable("sex") int gender,@PathVariable("age") String age,
									 @PathVariable("phone") String phone,@PathVariable("jobNumber")String jobNumber,
									 @PathVariable("college") String college) {
		try {
			TeacherInfo teacher = teacherInfoService.load(id);
			teacher.setTeacherName(teacherName);
			teacher.setGender(gender);
			teacher.setAge(age);
			teacher.setPhone(phone);
			teacher.setJobNumber(jobNumber);
			teacher.setCollege(college);
			teacherInfoService.add(teacher);

			return "1";

		} catch (Exception e) {
			return "0";
		}
	}


	/**
	 * 查询待监考场次
	 * @param teacherId
	 * @return
	 */
	@RequestMapping(value="/findReadyExam/{teacherId}",method=RequestMethod.POST)
	public ResultModel findReadyExam(@PathVariable int teacherId) {
		try {

			Map<String,Object> map = new HashMap<String,Object>();
			List<ExamInfoDto> content = new ArrayList();
			//查询考试信息
			List<ExamInfo> readysExam = examInfoService.findReadysExamByTeacherId(teacherId);
			for (ExamInfo e:readysExam){
				//与考试相关的学生数
				int countStudent = examAddStudentService.countByExamId(e.getId());

				ExamInfoDto examInfoDto = new ExamInfoDto();
				examInfoDto.setExamInfo(e);
				examInfoDto.setStudentCount(countStudent);

				String startDate = e.getStartDate();
				String s = e.getEndDate().substring(11);
				String examTime = startDate+"-"+s;
				examInfoDto.setExamTime(examTime);
				content.add(examInfoDto);
			}
			//统计老师相关的考试总数
			int countExam = examInfoService.countReadysExamByTeacherId(teacherId);
			map.put("examCount",countExam);
			map.put("content",content);

				return ResultTools.result(0, "", map);

		} catch (Exception e) {
			return ResultTools.result(404, e.getMessage(), null);
		}
	}

	/**
	 * 已监考场次
	 * @param teacherId
	 * @return
	 */
	@RequestMapping(value="/findAlreadyExam/{teacherId}",method=RequestMethod.POST)
	public ResultModel findAlreadyExam(@PathVariable int teacherId) {
		try {

			Map<String,Object> map = new HashMap<String,Object>();
			List<ExamInfoAlreadyDto> content = new ArrayList();
			//查询考试信息
			List<ExamInfo> readysExam = examInfoService.findAlreadysExamByTeacherId(teacherId);
			for (ExamInfo e:readysExam){
				//统计学生总人数
				int countStudent = examAddStudentService.countByExamId(e.getId());
				//考试信息
				ExamInfoAlreadyDto examInfoDto = new ExamInfoAlreadyDto();
				examInfoDto.setExamInfo(e);
				examInfoDto.setStudentCount(countStudent);

				//成功人数
				int countSuccess = examAddStudentService.countSucceByExamId(e.getId());
				//失败人数
				int countFail = examAddStudentService.countFailByExamId(e.getId());
				examInfoDto.setCountSuccess(countSuccess);
				examInfoDto.setCountFail(countFail);

				String startDate = e.getStartDate();
				String s = e.getEndDate().substring(11);
				String examTime = startDate+"-"+s;
				examInfoDto.setExamTime(examTime);
				content.add(examInfoDto);
			}
			//统计老师相关的考试总数
			int countExam = examInfoService.countAlreadysExamByTeacherId(teacherId);
			map.put("examCount",countExam);
			map.put("content",content);
			return ResultTools.result(0, "", map);

		} catch (Exception e) {
			return ResultTools.result(404, e.getMessage(), null);
		}
	}

	/**
	 * 查询考试相关联的考生信息  成功  -  失败
	 *
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/findStudent/{examId}", method = RequestMethod.POST)
	public ResultModel findStudent(@PathVariable("examId") int examId) {
		//成功学生信息
		List<ExamAddStudent> successStudent = examAddStudentService.findSucceByExamId(examId);
		//失败学生信息
		List<ExamAddStudent> failStudent = examAddStudentService.findFailByExamId(examId);
		//成功人数
		int countSuccess = examAddStudentService.countSucceByExamId(examId);
		//失败人数
		int countFail = examAddStudentService.countFailByExamId(examId);

		//考试信息
		ExamInfo examInfo = examInfoService.load(examId);

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("successStudent",successStudent);
		map.put("failStudent", failStudent);
		map.put("countSuccess",countSuccess);
		map.put("countFail",countFail);
		map.put("examName",examInfo.getExamName());
		return ResultTools.result(0, "", map);

	}

	/**
	 * 查找一个学生的信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findOneStudent/{id}", method = RequestMethod.POST)
	public ResultModel findOneStudent(@PathVariable("id") int id) {
		ExamAddStudent examAddStudent = examAddStudentService.load(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("student",examAddStudent);
		return ResultTools.result(0, "", map);
	}


	/**
	 *
	 * 通过姓名模糊查询成功学生信息
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/searchSuccessStudent/{examId}/{name}", method = RequestMethod.POST)
	public ResultModel searchSuccessStudent(@PathVariable("examId") int examId,
											@PathVariable("name") String name) {
		List<ExamAddStudent> succeStuByName = examAddStudentService.findSucceStuByName(examId, name);
		Map<String,Object> map = new HashMap<String,Object>();
		if(succeStuByName.size()>0){
			map.put("successStudent",succeStuByName);
			return ResultTools.result(0, "", map);
		}else {
			return ResultTools.result(1002, "", null);
		}
	}


	/**
	 *
	 * 通过姓名模糊查询失败学生信息
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/searchFailStudent/{examId}/{name}", method = RequestMethod.POST)
	public ResultModel searchFailStudent(@PathVariable("examId") int examId,
											@PathVariable("name") String name) {
		List<ExamAddStudent> succeStuByName = examAddStudentService.findFailStuByName(examId, name);
		Map<String,Object> map = new HashMap<String,Object>();
		if(succeStuByName.size()>0){
			map.put("failStudent",succeStuByName);
			return ResultTools.result(0, "", map);
		}else {
			return ResultTools.result(1002, "", null);
		}
	}




	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(UserInfo userInfo,Model model) {
		userInfoService.add(userInfo);
		return "redirect:/userInfo/list";
	}

	@RequestMapping("/{id}")
	public String show(@PathVariable int id,Model model) {
		UserInfo userInfo = userInfoService.load(id);
		model.addAttribute("userInfo", userInfo);
		return "userInfo/show";
	}

	@RequestMapping("/delete/{id}")
	public @ResponseBody String delete(@PathVariable int id) {
		userInfoService.delete(id);
		return "1";
	}

	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		UserInfo userInfo = userInfoService.load(id);
		model.addAttribute("userInfo",userInfo);
		return "userInfo/update";
	}

	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,UserInfo userInfo) {
		try {
			UserInfo tuserInfo = userInfoService.load(id);
			BeanUtils.copyProperties(tuserInfo, userInfo);
			userInfoService.update(tuserInfo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "redirect:/userInfo/list";
	}

	@RequestMapping("/list")
	public String find(Model model,Integer page,HttpServletRequest request) {
		Page<UserInfo> userInfos = userInfoService.find(SimplePageBuilder.generate(page));
		model.addAttribute("datas",userInfos);
		return "userInfo/list";
	}
}
