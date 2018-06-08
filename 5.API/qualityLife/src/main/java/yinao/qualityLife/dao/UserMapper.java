package yinao.qualityLife.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import yinao.qualityLife.model.domain.User;

@Mapper
@Component
public interface UserMapper {

    User getUserFromDatabase(@Param("username") String username);

}
