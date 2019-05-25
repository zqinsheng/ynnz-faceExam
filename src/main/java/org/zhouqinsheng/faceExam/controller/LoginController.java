package org.zhouqinsheng.faceExam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhouqinsheng.faceExam.apiTools.ConstantUtils;
import org.zhouqinsheng.faceExam.model.UserInfo;
import org.zhouqinsheng.faceExam.service.IUserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class LoginController {

    @Autowired
    IUserInfoService userInfoService;

    @RequestMapping(value = "/login",method= RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest request,
                        HttpServletResponse response)throws Exception{
        UserInfo userInfo = userInfoService.findByUsername(username);
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        if(userInfo.getPassword().equals(password)){
            request.getSession().setAttribute(ConstantUtils.USER_SESSION_KEY,userInfo);//用户名存入该用户的session 中
            //out.print("<script language=\"javascript\">alert('登录成功！');window.location.href='/index'</script>");
            return "index";
        }else{
            out.print("<script language=\"javascript\">alert('账号或密码错误');window.location.href='/login'</script>");
            return "login";
        }
    }
}
