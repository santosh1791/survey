package org.survey.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.survey.domain.User;
import org.survey.repository.UserRepository;

public class MD5Encoder implements PasswordEncoder {
	
	Logger logger = LoggerFactory.getLogger(MD5Encoder.class);
	
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		String md5Enc = DigestUtils
			      .md5Hex(rawPassword.toString());
				logger.debug(">>>>>>>>>>>>>>>>Encode() Converted md5 : {}",md5Enc);
		return md5Enc;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		logger.debug("\n\n\n>>>>>>>>>>>>MATCHING ANY>>>>>>>>>>>>{}\n\n\n{}",rawPassword,encodedPassword);
		if (encodedPassword == null || encodedPassword.length() == 0) {
			logger.warn("Empty encoded password");
			return false;
		}
		String md5Enc = DigestUtils
	      .md5Hex(rawPassword.toString());
		logger.debug(">>>>>>>>>>>>>>>> matches() Converted md5 : {}",md5Enc);
		
		if(md5Enc.equals(encodedPassword))
			return true;
		else
			return false;
	}

}
