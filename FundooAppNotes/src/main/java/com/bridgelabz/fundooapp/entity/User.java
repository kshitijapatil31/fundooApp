package com.bridgelabz.fundooapp.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private int id;
	private String fname;
	private String lname;
	private String email;
	private String password;
	
	private LocalDateTime userCreated;
	private LocalDateTime userUpdated;
	private boolean emailVerified;
	
}
