package yinao.qualityLife.model.domain;

import yinao.qualityLife.model.LoginDetail;
import yinao.qualityLife.model.TokenDetail;

public class User implements LoginDetail, TokenDetail {

    private String username;
    private String password;
    private String auth;
    private Long create_time;
    private char state;
    

	public User() {}
	
	public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

//	public char getState() {
//		return state;
//	}
//
//	public void setState(char state) {
//		this.state = state;
//	}

	public User setEnable(char state) {
        this.state = state;
        return this;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean enable() {
        if (this.state == '1'){
            return true;
        }
        return false;
    }

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", auth=" + auth + ", create_time="
				+ create_time + ", state=" + state + "]";
	}

	@Override
	public String getUsertype() {
		// TODO Auto-generated method stub
		return null;
	}

   
}
