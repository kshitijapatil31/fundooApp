package com.bridgelabz.fundooapp.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "labels")
public class Label {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	 
	private String labelName;
	
	@CreationTimestamp
	private LocalDateTime labelCreated;
	
	@UpdateTimestamp
	private LocalDateTime labelUpdated;
	
	@ManyToMany(mappedBy = "labels")
    private List<Note> notes;
	
	private int userId;
}
