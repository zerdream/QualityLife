package yinao.qualityLife.controller;

import yinao.qualityLife.dao.UserMapper;
import yinao.qualityLife.model.LoginDetail;
import yinao.qualityLife.model.ResultMap;
import yinao.qualityLife.model.TokenDetail;
import yinao.qualityLife.model.vo.Data;
import yinao.qualityLife.model.vo.RequestLoginUser;
import yinao.qualityLife.service.LoginService;
import yinao.qualityLife.utils.TokenUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    private final LoginService loginService;
    private final UserMapper userMapper;
    private final TokenUtils tokenUtils;
    
   
    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    public LoginController(LoginService loginService , UserMapper userMapper ,TokenUtils tokenUtils ) {
        this.loginService = loginService;
        this.userMapper = userMapper ; 
        this.tokenUtils = tokenUtils ; 
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultMap login(@Valid RequestLoginUser requestLoginUser, BindingResult bindingResult){
        // 检查有没有输入用户名密码和格式对不对
        if (bindingResult.hasErrors()){
            return new ResultMap().fail("400").message("缺少参数或者参数格式不对").data("");
        }

        LoginDetail loginDetail = loginService.getLoginDetail(requestLoginUser.getUsername() , requestLoginUser.getUsertype());
        ResultMap ifLoginFail = checkAccount(requestLoginUser, loginDetail);
        if (ifLoginFail != null){
            return ifLoginFail;
        }
        return new ResultMap().success().message("").data(new Data().addObj(tokenHeader, loginService.generateToken((TokenDetail) loginDetail, requestLoginUser.getUsertype())));

    }

    //登录验证
    private ResultMap checkAccount(RequestLoginUser requestLoginUser, LoginDetail loginDetail){
        if (loginDetail == null){
            return new ResultMap().fail("434").message("账号不存在！").data("");
        }else {
            if (loginDetail.enable() == false){
                return new ResultMap().fail("452").message("账号在黑名单中").data("");
            }
            if (!loginDetail.getPassword().equals(requestLoginUser.getPassword())){
                return new ResultMap().fail("438").message("密码错误！").data("");
            }
        }
        return null;
    }
    
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResultMap register(@Valid RequestLoginUser requestLoginUser, BindingResult bindingResult){
        // 检查有没有输入用户名密码和格式对不对
        if (bindingResult.hasErrors()){
            return new ResultMap().fail("400").message("缺少参数或者参数格式不对").data("");
        }
        //根据  usertype BUTLER（家政），EMPLOYER（ 雇主）  判断是哪个角色的注册
        //数据库查询 是否存在相关用户
        
        LoginDetail loginDetail = loginService.getLoginDetail(requestLoginUser.getUsername() , requestLoginUser.getUsertype());
        ResultMap ifLoginFail = checkAccount02(loginDetail);
        if (ifLoginFail != null){
            return ifLoginFail;
        }
        
        if(requestLoginUser.getUsertype().equals("EMPLOYER")) {
        	//将数据写入数据库 并生成token
            userMapper.registerEmployer(requestLoginUser.getUsername() , requestLoginUser.getPassword() , requestLoginUser.getName()) ; 
            return new ResultMap().success().message("success").data(new Data().addObj(tokenHeader, tokenUtils.generateToken(requestLoginUser.getUsername() ,requestLoginUser.getUsertype()))); 
    	}else {
    		//将数据写入数据库 并生成token
            userMapper.registerHousekeeper(requestLoginUser.getUsername() , requestLoginUser.getPassword() ,requestLoginUser.getName() ) ; 
            return new ResultMap().success().message("success").data(new Data().addObj(tokenHeader, tokenUtils.generateToken(requestLoginUser.getUsername() ,requestLoginUser.getUsertype()))); 
    	}
    }
    
    //注册验证
    private ResultMap checkAccount02(LoginDetail loginDetail){
        if (loginDetail != null){
        	return new ResultMap().fail("434").message("改账号已经被注册！").data("");
        }
        return null;
    }

}
