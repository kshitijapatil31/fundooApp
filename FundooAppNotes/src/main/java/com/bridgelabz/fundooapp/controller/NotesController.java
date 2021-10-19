package com.bridgelabz.fundooapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooapp.dto.NoteDto;
import com.bridgelabz.fundooapp.entity.Note;
import com.bridgelabz.fundooapp.response.ResponseDto;
import com.bridgelabz.fundooapp.service.INoteService;




@RestController
public class NotesController {
	@Autowired
	INoteService noteService;
	
	@PostMapping("/addNote")
	public ResponseEntity<ResponseDto>  addNote(@Valid @RequestBody NoteDto noteDto,@RequestHeader String token) {
		noteService.createNote(noteDto, token);
		ResponseDto responseDto = new ResponseDto("Note Added succesfully",noteDto);
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
	
	@PostMapping("/viewNotes/{token}")
	public ResponseEntity<ResponseDto>  viewNotes(@PathVariable String token) {
		List<Note> notes = noteService.viewNotes(token);
		ResponseDto responseDto = new ResponseDto("Displaying Note succesfully",notes);
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
	
	@PutMapping("/pinNote")
	public ResponseEntity<ResponseDto>  pinNote(@RequestHeader String token, @RequestParam int noteId) {
		noteService.pinNote(token, noteId);
		ResponseDto responseDto = new ResponseDto("Note operation succesfully","");
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
	@PutMapping("/archieveNote")
	public ResponseEntity<ResponseDto>  archieveNote(@RequestHeader String token, @RequestParam int noteId) {
		noteService.archieveNote(token, noteId);
		ResponseDto responseDto = new ResponseDto("Note operation succesfully","");
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
	
	@PutMapping("/toggleTrashNote")
	public ResponseEntity<ResponseDto>  toggleTrashNote(@RequestHeader String token, @RequestParam int noteId) {
		noteService.toggleTrashNote(token, noteId);
		ResponseDto responseDto = new ResponseDto("Note Trash operation succesfully","");
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteNote")
	public ResponseEntity<ResponseDto>  deleteNote(@RequestHeader String token, @RequestParam int noteId) {
		String message = noteService.deleteNote(token, noteId);
		ResponseDto responseDto = new ResponseDto("Deletion succesfully",message);
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
	
	@PostMapping("/addLabelToNote")
	public ResponseEntity<ResponseDto>  addLabelToNote(@RequestHeader String token,@RequestParam int noteId,@RequestParam String labelName ) {
		String response = noteService.addLabelToNote(token, noteId, labelName);
		ResponseDto responseDto = new ResponseDto("Added label to a note Succesfully",response);
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteLabelFromNote")
	public ResponseEntity<ResponseDto>  deleteLabelFromNote(@RequestHeader String token,@RequestParam int noteId,@RequestParam String labelName ) {
		String response = noteService.deleteLabelFromNote(token, noteId, labelName);
		ResponseDto responseDto = new ResponseDto("Label Deleted from note succesfully",response);
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
}