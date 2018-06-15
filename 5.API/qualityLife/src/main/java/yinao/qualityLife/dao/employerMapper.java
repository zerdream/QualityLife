package yinao.qualityLife.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import yinao.qualityLife.model.domain.Employer;

@Mapper
@Component
public interface employerMapper {
	
	//获取雇主信息列表
	List<Employer> getEmployerInfo(
			@Param("username") String username,
			@Param("name") String name,
			@Param("servicestate") String servicestate); 
	
	//编辑雇主信息
	int updateEmployerInfo(
			@Param("username") String username,
			@Param("name") String name,
			@Param("idcard") String idcard,
			@Param("education") String education,
			@Param("address1") String address1,
			@Param("address2") String address2, 
			@Param("address3") String address3,
			@Param("headimageurl") String headimageurl,
			@Param("servicestate") String servicestate);
		
	
}
