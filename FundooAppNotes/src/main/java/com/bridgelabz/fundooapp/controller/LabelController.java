package com.bridgelabz.fundooapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooapp.entity.Label;
import com.bridgelabz.fundooapp.response.ResponseDto;
import com.bridgelabz.fundooapp.service.LabelService;



@RestController
public class LabelController {
	
	@Autowired
	LabelService labelService;
	
	@PostMapping("/createLabel")
	public ResponseEntity<ResponseDto> createLabel(@RequestHeader String token, @RequestParam String labelName) {
		Label label = labelService.createLable(token, labelName);
		ResponseDto responseDto = new ResponseDto("Created label succesfully",label);
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/displayLabel")
	public ResponseEntity<ResponseDto> displayLabel(@RequestHeader String token) {
		List<Label> labels = labelService.displayAllLabels(token);
		ResponseDto responseDto = new ResponseDto("Displayed label succesfully",labels);
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteLabel")
	public ResponseEntity<ResponseDto>  deleteLabel(@RequestHeader String token, @RequestParam int labelId) {
		labelService.deleteLabel(token, labelId);
		ResponseDto responseDto = new ResponseDto("Label Deleted succesfully","");
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
	
	@PutMapping("updateLabel")
	public ResponseEntity<ResponseDto>  updateLabel(@RequestHeader String token, @RequestParam int labelId, @RequestParam String labelName) {
		Label label = labelService.updateLabel(token, labelId, labelName);
		ResponseDto responseDto = new ResponseDto("Label Updated succesfully",label);
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
}
