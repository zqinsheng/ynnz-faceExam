package org.zhouqinsheng.faceExam.model;


import javax.persistence.*;
import java.util.Date;


/**
 *
 * 用户信息表
 * @author zqs
 *
 */
@Entity
@Table(name="t_user_info")
public class UserInfo {

	/**
	 * 用户id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 用户类型
	 */
	@Column(name="user_type")
	private int userType;

	/**
	 * 获取用户id
	 * @return 用户id
	 */
	public int getId() {
		 return id;
	}

	/**
	 * 设置用户id
	 * @param id 用户id
	 */
	public void setId(int id) {
		 this.id = id;
	}


	/**
	 * 获取用户名
	 * @return 用户名
	 */
	public String getUsername() {
		 return username;
	}

	/**
	 * 设置用户名
	 * @param username 用户名
	 */
	public void setUsername(String username) { 
		 this.username = username;
	}


	/**
	 * 获取用户密码
	 * @return 用户密码
	 */
	public String getPassword() {
		 return password;
	}

	/**
	 * 设置用户密码
	 * @param password 用户密码
	 */
	public void setPassword(String password) { 
		 this.password = password;
	}


	/**
	 * 获取用户类型
	 * @return 用户类型 1-普通用户   2-管理员
	 */
	public int getUserType() {
		 return userType;
	}

	/**
	 * 设置用户类型
	 * @param userType 用户类型
	 */
	public void setUserType(int userType) {
		 this.userType = userType;
	}


}
