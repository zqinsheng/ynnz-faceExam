package org.zhouqinsheng.faceExam.wxapi;

import org.apache.commons.beanutils.BeanUtils;
import org.konghao.reposiotry.kit.SimplePageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zhouqinsheng.faceExam.apiTools.ResultTools;
import org.zhouqinsheng.faceExam.model.TeacherInfo;
import org.zhouqinsheng.faceExam.model.UserInfo;
import org.zhouqinsheng.faceExam.service.IExamInfoService;
import org.zhouqinsheng.faceExam.service.ITeacherInfoService;
import org.zhouqinsheng.faceExam.service.IUserInfoService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/ynavc")
public class YnavcController {

	@Autowired
	private IUserInfoService userInfoService;

	@Autowired
	private ITeacherInfoService teacherInfoService;

	@Autowired
    private IExamInfoService examInfoService;

	//教师登录，查询教工号
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

    @RequestMapping(value="/examCount/{openId}",method=RequestMethod.POST)
    public ResultModel examCount(@PathVariable("openId") String openId) {
        TeacherInfo teacher = teacherInfoService.findByOpenId(openId);
        List list = examInfoService.countExamByTeacherId(teacher.getId());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("content",list);
        return ResultTools.result(0, "", map);
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
