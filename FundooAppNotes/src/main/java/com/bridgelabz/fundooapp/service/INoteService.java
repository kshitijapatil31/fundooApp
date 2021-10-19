package com.bridgelabz.fundooapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.fundooapp.dto.NoteDto;
import com.bridgelabz.fundooapp.entity.Label;
import com.bridgelabz.fundooapp.entity.Note;
import com.bridgelabz.fundooapp.entity.User;
import com.bridgelabz.fundooapp.exception.FunDoNotesCustomException;
import com.bridgelabz.fundooapp.repository.LabelRepository;
import com.bridgelabz.fundooapp.repository.NoteRepository;
import com.bridgelabz.fundooapp.util.TokenService;

@Service
public class INoteService implements NoteService {
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	LabelRepository labelRepository;
	
	@Autowired
	RestTemplate restTemplate;

	public User gettingUser(String token) {
		User user = restTemplate.getForObject("http://FUN-DO-USER/giveUser/"+token, User.class);
		if (user == null) {
			throw new FunDoNotesCustomException(HttpStatus.BAD_REQUEST,"User not found");
		}
		return user;
	}
	
	public Note gettingNote(String token, int noteId) {
		User user = gettingUser(token);
		Note note = noteRepository.findAll().stream().filter(notes -> 
											( (notes.getId() == noteId) && (notes.getUserId()==user.getId()) ) ).findAny().get();
		if (note == null) {
		throw new FunDoNotesCustomException(HttpStatus.BAD_REQUEST,"User Not found for creating note");
		}
		return note;		
	}
	
	@Override
	public Note createNote(NoteDto nodeDto ,String token) {
		User user = gettingUser(token);
		Note note = new Note();
		BeanUtils.copyProperties(nodeDto, note);
		note.setUserId(user.getId());
		noteRepository.save(note);
		return note;
	}

	@Override
	public List<Note> viewNotes(String token) {
		User user = gettingUser(token);
		List<Note> notes = noteRepository.findAll().stream().filter(note -> (note.getIsTrashed().equals(false)) && (note.getUserId()==user.getId()) ).collect(Collectors.toList());
		return notes;		
	}
	
	@Override
	public void pinNote(String token, int noteId) {
		Note note = gettingNote(token, noteId);
		
		if (note.getIsPinned()) {
			note.setIsPinned(false);
		}
		else {
			note.setIsPinned(true);
		}
		noteRepository.save(note);
	}
	
	@Override
	public void archieveNote(String token, int noteId) {
		Note note = gettingNote(token, noteId);
		
		if (note.getIsArchived()) {
			note.setIsArchived(false);
		}
		else {
			note.setIsArchived(true);
		}
		noteRepository.save(note);
	}
	
	@Override
	public void toggleTrashNote(String token, int noteId) {
		Note note = gettingNote(token, noteId);
		
		if (note.getIsTrashed()) {
			note.setIsTrashed(false);
		}
		else {
			note.setIsTrashed(true);
		}
		noteRepository.save(note);
	}
	
	@Override
	public String deleteNote(String token, int noteId) {
		Note note = gettingNote(token, noteId);
		
		if(note.getIsTrashed()) {
			noteRepository.delete(note);
			return "Deleted succesfully";
		}
		else {
			return "First move to trash";
		}
	}
	
	@Override
	public String addLabelToNote(String token, int noteId, String labelName) {
		Note notes = gettingNote(token, noteId);
		
		Label label = labelRepository.findByLabelName(labelName);
		if(label == null)
			throw new FunDoNotesCustomException(HttpStatus.BAD_REQUEST,"Label is not present, First make a label ");

		notes.getLabels().add(label);
		noteRepository.save(notes);
		return "Added label Succesfully";
	}
	
	@Override
	public String deleteLabelFromNote(String token, int noteId, String labelName) {
		Note notes = gettingNote(token, noteId);
		
		Label label = labelRepository.findByLabelName(labelName);
		if(label == null)
			throw new FunDoNotesCustomException(HttpStatus.BAD_REQUEST,"Label name incorrect");

		notes.getLabels().remove(label);
		noteRepository.save(notes);
		return "Label Deleted Succesfully";
	}

}