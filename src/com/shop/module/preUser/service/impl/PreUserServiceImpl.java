package com.shop.module.preUser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.common.base.MD5;
import com.shop.module.preUser.dao.mapper.PreUserMapper;
import com.shop.module.preUser.model.PreUser;
import com.shop.module.preUser.service.inter.PreUserService;
/**
 * 用户service实现类
 * @author miaohanbin
 *
 */
@Service
public class PreUserServiceImpl implements PreUserService {
	@Autowired
	private PreUserMapper preUserDao;
	@Override
	public void savePreUser(PreUser preUser) {
		preUserDao.savePreUser(preUser);
	}

	@Override
	public void updatePreUser(PreUser preUser) {
		PreUser user=preUserDao.findPreUserByUserCode(preUser.getPreUserCode());
		if(user!=null&&user.getPassword().equals(preUser.getPassword())){
			preUser.setPassword(user.getPassword());
		}else{
			if(StringUtils.isNotBlank(preUser.getPassword())){
				preUser.setPassword(new MD5().getMD5ofStr(preUser.getPassword()));
			}
		}
		preUserDao.updatePreUser(preUser);
	}

	@Override
	public int deletePreUserByCode(String preUserCode) {
		return preUserDao.deletePreUserByCode(preUserCode);
	}
	
	
	@Override
	public PreUser findPreUserByUserCode(String preUserCode) {
		return preUserDao.findPreUserByUserCode(preUserCode);
	}

	@Override
	public List<PreUser> findPreUsers(Map<String, Object> map) {
		return preUserDao.findPreUsers(map);
	}

	@Override
	public int findPreUsersCount(Map<String, Object> map) {
		return preUserDao.findPreUsersCount(map);
	}

	@Override
	public boolean checkUserName(String username,String preUserCode) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("username", username);
		map.put("status", "1");
		if(StringUtils.isNotBlank(preUserCode)){
			map.put("preUserCode", preUserCode);
		}
		int count=preUserDao.checkUserName(map);
		if(count==0){
			return true;
		}
		return false;
	}

	@Override
	public void deletePreUsers(String ids) {
		if(StringUtils.isNotBlank(ids)){
			String[] delUserId = ids.split(",");
			for(String id:delUserId){
				preUserDao.deletePreUserByCode(id);
			}
		}
	}
	
	
}
