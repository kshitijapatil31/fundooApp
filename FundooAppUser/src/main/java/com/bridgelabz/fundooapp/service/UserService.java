package com.bridgelabz.fundooapp.service;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooapp.dto.LoginDto;
import com.bridgelabz.fundooapp.dto.UpdatePasswordDto;
import com.bridgelabz.fundooapp.dto.UserDto;
import com.bridgelabz.fundooapp.entity.User;

@Service
public interface UserService {
	
	public User register(UserDto userDto);
	public String login(LoginDto loginDto);
	public void verifyEmail(String token);
	public void forgotPassword(String email);
	public void resetPassword(String newPassword, String token);
	public void updatePassword(String token, UpdatePasswordDto updatePasswordDto);
}