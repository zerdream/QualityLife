package yinao.qualityLife.service;

import yinao.qualityLife.model.LoginDetail;
import yinao.qualityLife.model.TokenDetail;

public interface LoginService {

    LoginDetail getLoginDetail(String username);

    String generateToken(TokenDetail tokenDetail);

}
