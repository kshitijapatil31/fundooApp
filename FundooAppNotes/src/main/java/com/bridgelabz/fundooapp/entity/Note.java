package com.bridgelabz.fundooapp.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notes")
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String title;
	
	private String description;
	
	private Boolean isPinned;
	
	private Boolean isTrashed;
	
	private Boolean isArchived;
	
	private String colour;
	
	private String reminder;
	
	@CreationTimestamp
	private LocalDateTime createdTimeStamp;
	@UpdateTimestamp
	private LocalDateTime updateTimeStamp;
	
	private int userId;
	
	@ManyToMany
    @JoinTable( name = "Label_Name", 
    			joinColumns = { @JoinColumn (name = "Note_id")},
    			inverseJoinColumns = { @JoinColumn (name = "label_id") })
	private List<Label> labels;
}
