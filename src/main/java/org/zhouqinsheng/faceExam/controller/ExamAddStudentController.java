package org.zhouqinsheng.faceExam.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.konghao.reposiotry.kit.SimplePageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zhouqinsheng.faceExam.model.ExamAddStudent;
import org.zhouqinsheng.faceExam.model.ExamPerson;
import org.zhouqinsheng.faceExam.service.IExamAddStudentService;
import org.zhouqinsheng.faceExam.service.IExamPersonService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;


@Controller
@RequestMapping("/examAddStudent")
public class ExamAddStudentController {

	@Autowired
	private IExamAddStudentService examAddStudentService;

	@Autowired
	private IExamPersonService examPersonService;

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		ExamAddStudent examAddStudent = new ExamAddStudent();
		model.addAttribute("examAddStudent",examAddStudent);
		return "examAddStudent/add";
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(ExamAddStudent examAddStudent,Model model) {
		examAddStudentService.add(examAddStudent);
		return "redirect:/examAddStudent/list";
	}

	//添加单个考生
	@RequestMapping(value="/addOne/{examId}/{stuNumber}",method=RequestMethod.POST)
	@ResponseBody
	public String addOne(@PathVariable int examId,@PathVariable String stuNumber,Model model) {
		ExamPerson person = examPersonService.findByStuNumber(stuNumber);
		ExamAddStudent student = new ExamAddStudent();
		if(person!=null){
			student.setExamInfoId(examId);
			student.setCollege(person.getCollege());
			student.setGender(person.getGender());
			student.setIdCard(person.getIdCard());
			student.setPersonName(person.getPersonName());
			student.setStuNumber(person.getStuNumber());
			examAddStudentService.add(student);
			return "1";
		}
		return "0";


	}

	@RequestMapping("/{id}")
	public String show(@PathVariable int id,Model model) {
		ExamAddStudent examAddStudent = examAddStudentService.load(id);
		model.addAttribute("examAddStudent", examAddStudent);
		return "examAddStudent/show";
	}

	@RequestMapping("/delete/{id}")
	public @ResponseBody String delete(@PathVariable int id) {
		examAddStudentService.delete(id);
		return "1";
	}

	@RequestMapping("/list/{id}")
	public String find(Model model,Integer page,@PathVariable int id) {
		Page<ExamAddStudent> examAddStudents = examAddStudentService.find(SimplePageBuilder.generate(page));
		//int examId = Integer.parseInt(id);
		model.addAttribute("examId",id);
		model.addAttribute("datas",examAddStudents);
		return "examAddStudent/list";
	}

}
