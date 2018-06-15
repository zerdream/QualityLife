package yinao.qualityLife.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import yinao.qualityLife.dao.UserMapper;
import yinao.qualityLife.model.ResultMap;
import yinao.qualityLife.model.domain.User;
import yinao.qualityLife.utils.TokenUtils;

@RestController
public class MeController {
	
	private final TokenUtils tokenUtils;
	private final UserMapper userMapper;
	
	@Value("${token.header}")
    private String tokenHeader;
	
	@Autowired
    public MeController(TokenUtils tokenUtils , UserMapper userMapper ) {
        this.tokenUtils = tokenUtils ; 
        this.userMapper = userMapper ; 
    }

	@RequestMapping(value = "auth",method = RequestMethod.POST)
    public ResultMap authMe01(HttpServletRequest request){
		String token = request.getHeader(tokenHeader) ;
		String username = tokenUtils.getUsernameFromToken(token) ; 
		String usertype = tokenUtils.getUserTypeFromToken(token); 

		User user ; 
		if(usertype.equals("EMPLOYER")) {
			user = userMapper.loginEmployer(username) ;
		}else {
			user = userMapper.loginHousekeeper(username) ;  
		}
        return new ResultMap().success().message("success").data(user);	
    }
	
	
}
