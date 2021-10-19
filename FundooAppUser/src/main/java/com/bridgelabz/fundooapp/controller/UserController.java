package com.bridgelabz.fundooapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooapp.Response.ResponseDto;
import com.bridgelabz.fundooapp.dto.LoginDto;
import com.bridgelabz.fundooapp.dto.UpdatePasswordDto;
import com.bridgelabz.fundooapp.dto.UserDto;
import com.bridgelabz.fundooapp.entity.User;
import com.bridgelabz.fundooapp.service.IUserService;



@RestController
public class UserController {

	@Autowired
	IUserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDto> post(@RequestBody UserDto userDto ) {
		User user = userService.register(userDto);
		ResponseDto responseDto = new ResponseDto("Added succesfully",user);
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
	
	@GetMapping("/giveUser/{token}")
	public User giveUser(@PathVariable("token") String token) {
	User user = userService.givingUser(token);
		return user;
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDto> login(@RequestBody LoginDto loginDto) {
		String token = userService.login(loginDto);
		ResponseDto responseDto = new ResponseDto("LoggedIn succesfully",token);
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
	
	@GetMapping("/verify/{token}")
	public ResponseEntity<ResponseDto> verifyEmail(@PathVariable(value = "token") String token) {
		userService.verifyEmail(token);
		ResponseDto responseDto = new ResponseDto("User email verified","");
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}

	@GetMapping("/forgot/{email}")
	public ResponseEntity<ResponseDto> forgotPassword(@PathVariable String email) {
		userService.forgotPassword(email);
		ResponseDto responseDto = new ResponseDto("For got password succesfull",email);
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
	
	@PutMapping("/reset")
	public ResponseEntity<ResponseDto> resetEmail(@RequestHeader String token, @RequestParam String newPassword) {
		userService.resetPassword(newPassword, token);
		ResponseDto responseDto = new ResponseDto("Reset password succesfull","");
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
	
	@PutMapping("/updatePassword")
	public ResponseEntity<ResponseDto> updatePassword(@RequestHeader String token, @RequestBody UpdatePasswordDto updatePasswordDto) {
		userService.updatePassword(token,updatePasswordDto);
		ResponseDto responseDto = new ResponseDto("Reset password succesfull","");
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
	
}