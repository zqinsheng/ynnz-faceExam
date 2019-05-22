package org.zhouqinsheng.faceExam.wxapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zhouqinsheng.faceExam.apiTools.Base64Util;
import org.zhouqinsheng.faceExam.apiTools.FileUtil;
import org.zhouqinsheng.faceExam.apiTools.HttpUtil;
import org.zhouqinsheng.faceExam.apiTools.ResultTools;
import org.zhouqinsheng.faceExam.model.*;
import org.zhouqinsheng.faceExam.service.IExamAddStudentService;
import org.zhouqinsheng.faceExam.service.IExamInfoService;
import org.zhouqinsheng.faceExam.service.ITeacherInfoService;
import org.zhouqinsheng.faceExam.service.IUserInfoService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;


@RestController
@RequestMapping("api/ynavc")
public class YnavcController {

	@Value("${uploadpic.path}")
	private String uploadPicPath;

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
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			//教师信息
			TeacherInfo teacher = teacherInfoService.findByOpenId(openId);


			/**根据当前时间  设置该教师关联的考试状态*/
			List<ExamInfo> allExam = examInfoService.findAllExamByTeacherId(teacher.getId());
			for(ExamInfo e :allExam){
				String endDate = e.getEndDate();
				Date eDate = format.parse(endDate);
				if (eDate.after(new Date())){
					//待监考
					e.setExamStatus(1);
				}else if(eDate.before(new Date())){
					//已监考
					e.setExamStatus(0);
				}
				examInfoService.add(e);
			}

			//待监考和未监考
			int readyExam = examInfoService.countReadysExamByTeacherId(teacher.getId());
			int alreadyExam = examInfoService.countAlreadysExamByTeacherId(teacher.getId());


			/**查询本月已经监考的场次*/
			Calendar cale = null;
			// 获取当月第一天和最后一天
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
			map.put("readyExam",readyExam);
			map.put("alreadyExam",alreadyExam);
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


	/**人脸验证*/
	@RequestMapping("/verifyFace")
	public ResultModel verifyFace(@RequestParam("file") MultipartFile file) {
		//System.out.println("name1:"+file.getName());
		//System.out.println("originalName:"+file.getOriginalFilename());

		//文件上传
		if(file!=null) {
			BufferedOutputStream bw = null;
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String date = df.format(new Date());// n

			try {
				String fileName = file.getOriginalFilename();
				//判断是否有文件
				if(StringUtils.isNoneBlank(fileName)) {
					//文件名称
					String imgName = date+"-"+UUID.randomUUID().toString()+
							fileName.substring(fileName.indexOf("."));
					//文件格式
					String imageType = fileName.substring(fileName.lastIndexOf(".")+1);
					//文件存储路径
					String outPath = uploadPicPath+imgName;
					//创建输出文件对象
					File outFile = new File(outPath);
					//拷贝文件到输出文件对象
					copyInputStreamToFile(file.getInputStream(), outFile);
					//调用检测方法
					String path = outFile.getPath();
					//System.out.println(imgName);
					//System.out.println(path);
					//调用人脸验证接口
					Map<String, Object> studentMap = this.sendFaceImg(path);
					Map<String,Object> map = new HashMap<String,Object>();

					if (studentMap!=null){
						map.put("content",studentMap);
						map.put("imgName",imgName);
						map.put("imgType",imageType);
						return ResultTools.result(0, "", map);
					}else{
						return ResultTools.result(0, "", map);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(bw!=null) {bw.close();}
				} catch (IOException e) {
					return ResultTools.result(404, e.getMessage(), null);
				}
			}
		}
		return ResultTools.result(1002, "", null);


	}

	//调用人脸验证接口
	private Map<String, Object> sendFaceImg(String path) {
		try {
			String url = "https://face.yunyitx.com/getface";
			byte[] bytes = FileUtil.readFileByBytes(path);
			String image = Base64Util.encode(bytes);

			// 发送请求
			Map<String,String> map = new HashMap<String,String>();
			map.put("imgdata",image);
			String data = HttpUtil.post(url,map);
			System.out.println(data);
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> json = null;

			json = mapper.readValue(data, Map.class);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}


	/**人脸验证成功，通过考试id和学号来判断是否有这个学生*/
	@RequestMapping(value = "/verifyStudent/{examId}/{stuNumber}/{imageName}/{imageType}",method = RequestMethod.POST)
	public ResultModel verifyStudent(@PathVariable("examId") int examId,
									 @PathVariable("stuNumber") String stuNumber,
									 @PathVariable("imageName") String imageName,
									 @PathVariable("imageType") String imageType) {
		ExamAddStudent student = examAddStudentService.findByExamInfoIdAndStuNumber(examId, stuNumber);
		Map<String,Object> map = new HashMap<String,Object>();
		if (student!=null){
			//成功验证学生
			//存储图片
			student.setImageUrl(imageName);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = new Date();
			String examTime = sdf.format(date);
			//存储刷脸时间
			student.setExamTime(examTime);
			//刷脸状态成功
			student.setExamStatus(1);
			examAddStudentService.add(student);


			map.put("content",student);
			return ResultTools.result(0, "", map);
		}else{
			return ResultTools.result(0, "", null);
		}
	}

	/**是否开启刷脸功能*/
	@RequestMapping(value = "/verifyExamTime/{examId}",method = RequestMethod.POST)
	public String verifyExamTime(@PathVariable("examId") int examId) {
		try {
			ExamInfo examInfo = examInfoService.load(examId);
			String startDate = examInfo.getStartDate();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = format.parse(startDate);
			long time = 30*60*1000;//30分钟
			Date beforeDate = new Date(date .getTime() - time);//考试前30分钟
			Date now = new Date();
			//考试时间未到
			if(now.before(beforeDate)){
				return "0";
				//考试前30分钟已到
			}else if (now.after(beforeDate)){
				return "1";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "0";
	}
}
