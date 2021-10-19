package com.bridgelabz.fundooapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.fundooapp.entity.Label;
import com.bridgelabz.fundooapp.entity.User;
import com.bridgelabz.fundooapp.exception.FunDoNotesCustomException;
import com.bridgelabz.fundooapp.repository.LabelRepository;
import com.bridgelabz.fundooapp.repository.NoteRepository;
import com.bridgelabz.fundooapp.util.TokenService;


@Service
public class ILabelService implements LabelService {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public User gettingUser(String token) {
		User user = restTemplate.getForObject("http://FUN-DO-USER/giveUser/"+token, User.class);
		if (user == null) {
			throw new FunDoNotesCustomException(HttpStatus.BAD_REQUEST,"User not found");
		}
		return user;
	}
	
	
	@Override
	public Label createLable(String token, String labelName) {
		User user = gettingUser(token);
		Label label = labelRepository.findByLabelName(labelName);
		if (label != null) {
			throw new FunDoNotesCustomException(HttpStatus.CONFLICT, "Label Name already presnt");
		}
		Label label1 = new Label();
		label1.setLabelName(labelName);
		label1.setUserId(user.getId());
		labelRepository.save(label1);
		
		return label1;
	}
	
	@Override
	public List<Label> displayAllLabels(String token){
		User user = gettingUser(token);
		List<Label> labels = labelRepository.findAll().stream().
								filter(label -> label.getUserId()==user.getId()).collect(Collectors.toList());
		return labels;
	}
	
	@Override
	public void deleteLabel(String token, int labelId) {
		User user = gettingUser(token);
		Label labels = labelRepository.findAll().stream().filter(label -> label.getUserId()==user.getId()).findAny().get(); 
		if (labels == null) {
			throw new FunDoNotesCustomException(HttpStatus.BAD_REQUEST,"Label not created");
		}
		labelRepository.delete(labels);
	}
	
	@Override
	public Label updateLabel(String token, int labelId, String labelName) {
		User user = gettingUser(token);
		
		Label labels = labelRepository.findAll().stream().filter(label -> label.getUserId()==user.getId()).findAny().get(); 
		if (labels == null) {
			throw new FunDoNotesCustomException(HttpStatus.BAD_REQUEST,"Label not created");
		}
		
		labels.setLabelName(labelName);
		labelRepository.save(labels);
		return labels;
	}

}
