package com.paul.ssm.service;

import com.paul.ssm.domain.User;

/**
 * 用户Service接口
 * @author huangyun
 *
 */
public interface IUserService {

	/**
	 * 新增
	 * @return
	 */
	int insert(User user);
	
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	User getUserById(Long id);
}
