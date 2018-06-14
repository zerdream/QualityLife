package yinao.qualityLife.service.impl;

import yinao.qualityLife.dao.UserMapper;
import yinao.qualityLife.model.LoginDetail;
import yinao.qualityLife.model.TokenDetail;
import yinao.qualityLife.service.LoginService;
import yinao.qualityLife.utils.TokenUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserMapper userMapper;
    private final TokenUtils tokenUtils;

    @Autowired
    public LoginServiceImpl(UserMapper userMapper, TokenUtils tokenUtils) {
        this.userMapper = userMapper;
        this.tokenUtils = tokenUtils;
    }

    @Override
    public String generateToken(TokenDetail tokenDetail) {
        return tokenUtils.generateToken(tokenDetail);
    }

	@Override
	public LoginDetail getLoginDetail(String username, String usertype) {
    	if(usertype.equals("EMPLOYER")) {
    		return userMapper.loginEmployer(username);
    	}else {
    		return userMapper.loginHousekeeper(username) ; 
    	}
	}

}
