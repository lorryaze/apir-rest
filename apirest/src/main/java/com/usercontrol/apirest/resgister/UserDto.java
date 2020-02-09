package com.usercontrol.apirest.resgister;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;;

public class UserDto {
	
    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;
     
    @NotNull
    @NotEmpty
    private String email;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
