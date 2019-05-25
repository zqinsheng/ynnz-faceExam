package org.zhouqinsheng.faceExam.wxapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zhouqinsheng.faceExam.apiTools.HttpUtil;
import org.zhouqinsheng.faceExam.apiTools.ResultTools;
import org.zhouqinsheng.faceExam.model.TeacherInfo;
import org.zhouqinsheng.faceExam.service.ITeacherInfoService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/wx")
public class WeixinController {

    @Autowired
    private ITeacherInfoService teacherInfoService;


    private static Logger log = LoggerFactory.getLogger(WeixinController.class);

    private String appId ="wxd777ef7694ed2fb1";

    private String appSecret ="356b631f0c2212a6d0798e9c780bdf13";

    private String grantType = "wx.grantType=authorization_code";
    // wx.grantType=authorization_code

    private String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
    // wx.requestUrl=https://api.weixin.qq.com/sns/jscode2session

  /*  @RequestMapping(value = "/session/{code}",method = RequestMethod.POST)
    public Map<String, Object> getSession(@PathVariable String code) {
        return this.getSessionByCode(code);
    }

    @RequestMapping("/session")
    public Map<String, Object> getSession(@RequestParam(required = true) String code) {
        return this.getSessionByCode(code);
    }

   */

    @RequestMapping(value = "/session/{code}",method = RequestMethod.POST)
    public ResultModel getSession(@PathVariable String code) {
        try {
            Map<String,Object> wxUser;
            wxUser = this.getSessionByCode(code);

            if (wxUser!=null){
                String openId = wxUser.get("openid").toString();
                TeacherInfo teacherInfo = teacherInfoService.findByOpenId(openId);
                Map<String,Object> map = new HashMap<String,Object>();
                if (teacherInfo!=null){
                    map.put("content",teacherInfo);
                    map.put("teacherStatus",1);
                    return ResultTools.result(0, "", map);
                }else if (teacherInfo==null){
                    map.put("openId",openId);
                    map.put("teacherStatus",0);
                    return ResultTools.result(0, "", map);
                }
            }


        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
        //没有找到openId
        return ResultTools.result(1001, "", null);
    }



    private Map<String, Object> getSessionByCode(String code) {
        String url = this.requestUrl + "?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type="
                + grantType;
        // 发送请求
        String data = HttpUtil.get(url);
        log.debug("请求地址：{}", url);
        log.debug("请求结果：{}", data);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = null;
        try {
            json = mapper.readValue(data, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 形如{"session_key":"6w7Br3JsRQzBiGZwvlZAiA==","openid":"oQO565cXXXXXEvc4Q_YChUE8PqB60Y"}的字符串
        return json;
    }


}