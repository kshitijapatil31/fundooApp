package com.bridgelabz.fundooapp.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
	
	@NotBlank(message = "Title cannot be blank")
	private String title;
	
	@NotBlank(message = "Description cannot be blank")
	private String description;
	
	private Boolean isPinned;
	
	private Boolean isTrashed;
	
	private Boolean isArchived;
	
	private String colour;
	
	private String reminder;
}
