package com.ctgu.npc.business.sys.service;


import com.ctgu.npc.business.common.utils.DigestsUtil;
import com.ctgu.npc.business.common.utils.Encodes;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.business.sys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class SysService {
	
	@Autowired
	private UserMapper userMapper;
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	
	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = DigestsUtil.generateSalt(SALT_SIZE);
		byte[] hashPassword = DigestsUtil.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
	}
	
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0,16));
		byte[] hashPassword = DigestsUtil.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
	}

	/**
	 * 更新用户密码
	 * @param id
	 * @param newPassword
	 */
	@Transactional(readOnly = false)
	public  void updatePasswordById(String id, String newPassword) {
		//System.out.println("->" + newPassword);
		
		Users user = new Users(id);
		user.setPassword(entryptPassword(newPassword));
		userMapper.updatePasswordById(user);
	}
	

}
