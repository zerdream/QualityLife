package yinao.qualityLife.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import yinao.qualityLife.model.domain.HouseKeeper;


@Mapper
@Component
public interface HouseKeeperMapper {

	//获取家政端列表  详情页同上 
	List<HouseKeeper> houseKeeperList(
			@Param("username") String username,
			@Param("name") String name,
			@Param("state") String state);
	
	//修改家政信息接口
	int updateEmployerInfo(
			@Param("username") String username,
			@Param("name") String name,
			@Param("headimageurl") String headimageurl,
			@Param("idcard") String idcard,
			@Param("education") String education,
			@Param("address1") String address1,
			@Param("address2") String address2,
			@Param("address3") String address3,
			@Param("sex") String sex,
			@Param("role_id") String role_id);
			//@Param("state") String state); 
	
	//删除家政信息接口
	int deletehousekeeper(
			@Param("username") String username);
			
	
	//新增家政人员信息接口
	int addhousekeeper(
			@Param("username") String username,
			@Param("name") String name,
			@Param("headimageurl") String headimageurl,
			@Param("idcard") String idcard,
			@Param("education") String education,
			@Param("address1") String address1,
			@Param("address2") String address2,
			@Param("address3") String address3,
			@Param("sex") String sex);
	
	
			
   

}
