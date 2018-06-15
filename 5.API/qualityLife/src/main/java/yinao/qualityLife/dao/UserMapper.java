   package yinao.qualityLife.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import yinao.qualityLife.model.domain.User;

@Mapper
@Component
public interface UserMapper {

	//雇主登录接口
    User loginEmployer(@Param("username") String username);
    //家政登录接口
    User loginHousekeeper(@Param("username") String username);
    //雇主注册接口
    void registerEmployer(@Param("username") String username , @Param("password") String password , @Param("name") String name);
    //家政注册接口
    void registerHousekeeper(@Param("username") String username , @Param("password") String password , @Param("name") String name);


}
