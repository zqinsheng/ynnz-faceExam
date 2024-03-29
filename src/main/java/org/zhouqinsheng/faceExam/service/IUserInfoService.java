package org.zhouqinsheng.faceExam.service;

import org.konghao.service.IBaseService;
import org.zhouqinsheng.faceExam.model.UserInfo;

/**
 * 用户Service的实现
 * 默认实现了CRUD方法，add:添加,update:更新,delete:删除,load:加载一个对象,list:列表对象不分页,find:分页列表对象
 * @author zqs
 * 
 */
public interface IUserInfoService extends IBaseService<UserInfo,Integer> {

    UserInfo findByUsername(String username);
}