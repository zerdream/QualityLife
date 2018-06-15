package yinao.qualityLife.controller;

import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import yinao.qualityLife.dao.HouseKeeperMapper;
import yinao.qualityLife.dao.UserMapper;
import yinao.qualityLife.model.LoginDetail;
import yinao.qualityLife.model.ResultMap;
import yinao.qualityLife.model.domain.HouseKeeper;
import yinao.qualityLife.model.domain.User;
import yinao.qualityLife.service.LoginService;
import yinao.qualityLife.utils.PageUtils;
import yinao.qualityLife.utils.TokenUtils;

@RestController
public class HouseKeeperController {
	
	private final UserMapper userMapper;
	private final HouseKeeperMapper housekeeperMapper;
	private final TokenUtils tokenUtils;
	
	@Value("${token.header}")
    private String tokenHeader;
	
	@Autowired
    public HouseKeeperController(UserMapper userMapper , HouseKeeperMapper housekeeperMapper , TokenUtils tokenUtils) {
        this.housekeeperMapper = housekeeperMapper ; 
        this.userMapper = userMapper ; 
        this.tokenUtils = tokenUtils ; 
    }
	
	@RequestMapping(value = "houseKeeperList",method = RequestMethod.GET)
    public ResultMap houseKeeperList(HttpServletRequest request){
		
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String state = request.getParameter("state");
		int currPage = Integer.parseInt(request.getParameter("currPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		List<HouseKeeper> housekeeperlist = this.housekeeperMapper.houseKeeperList(username,name,state);
		List<HouseKeeper> list  = PageUtils.getList(housekeeperlist , currPage , pageSize ) ; 
		if(list != null) {
			return new ResultMap().success().message("success").data(list );		
		}else {
			return new ResultMap().success().message("没有更多数据");
		}
    }
	
	@RequestMapping(value = "editHousekeeperInfo", method = RequestMethod.POST)
	public ResultMap editHousekeeperInfo(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader) ;
		String username_token = tokenUtils.getUsernameFromToken(token) ; 
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String headimageurl = request.getParameter("headimageurl");
		String idcard = request.getParameter("idcard"); 
		String education = request.getParameter("education"); 
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address3 = request.getParameter("address3");
		String sex = request.getParameter("sex");
		String role_id = request.getParameter("role_id");
		
		if(!username_token.equals(username)) {
			return new ResultMap().fail("401").message("没有权限修改他人信息");		
		}
		try {
			int count = this.housekeeperMapper.updateEmployerInfo(username,name,headimageurl, idcard,education,address1,address2,address3,sex,role_id);
			if(count == 1 ) {
				return new ResultMap().success().message("修改成功" );		
			}else {
				return new ResultMap().fail("435").message("修改失败");		
			}
		} catch (Exception e) {
			return new ResultMap().fail("436").message("修改失败");		
		}
	}
	
	@RequestMapping(value = "addhousekeeper", method = RequestMethod.POST)
	public ResultMap addhousekeeper(HttpServletRequest request) {
		String username = request.getParameter("username");
		if(username == null ) {
			return new ResultMap().fail("400").message("请添加手机号码").data("");
		}
		User user = userMapper.loginHousekeeper(username) ; 
		if(user != null) {
			return new ResultMap().fail("434").message("改账号已经被注册！").data("");
		}
		String name = request.getParameter("name");
		String headimageurl = request.getParameter("headimageurl");
		String idcard = request.getParameter("idcard"); 
		String education = request.getParameter("education"); 
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address3 = request.getParameter("address3");
		String sex = request.getParameter("sex");
		try {
			int count = this.housekeeperMapper.addhousekeeper(username,name,headimageurl, idcard,education,address1,address2,address3,sex);
			if(count == 1 ) {
				return new ResultMap().success().message("插入成功");		
			}else {
				return new ResultMap().fail("435").message("插入失败");		
			}
		} catch (Exception e) {
			return new ResultMap().fail("436").message("插入失败");		
		}
	}
	
	@RequestMapping(value = "deletehousekeeper", method = RequestMethod.POST)
	public ResultMap deletehousekeeper(HttpServletRequest request) {
		String username = request.getParameter("username");
		if(username == null ) {
			return new ResultMap().fail("400").message("请添加手机号码");
		}
		User user = userMapper.loginHousekeeper(username) ; 
		if(user == null) {
			return new ResultMap().fail("434").message("改账号还未被注册！");
		}
		try {
			int count = this.housekeeperMapper.deletehousekeeper(username);
			if(count == 1 ) {
				return new ResultMap().success().message("删除成功");		
			}else {
				return new ResultMap().fail("435").message("删除失败");		
			}
		} catch (Exception e) {
			return new ResultMap().fail("436").message("删除失败").data(e.toString() );		
		}
	}
}
