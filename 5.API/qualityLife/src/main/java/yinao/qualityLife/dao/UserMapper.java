   package yinao.qualityLife.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import yinao.qualityLife.model.domain.User;

@Mapper
@Component
public interface UserMapper {

    User loginEmployer(@Param("username") String username);
   
    User loginHousekeeper(@Param("username") String username);
    
    void registerEmployer(@Param("username") String username , @Param("password") String password , @Param("name") String name);
    
    void registerHousekeeper(@Param("username") String username , @Param("password") String password , @Param("name") String name);


}
