package com.bridgelabz.fundooapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooapp.entity.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Integer> {
	Label findByLabelName(String labelName); 
	Label findByLabelName(Label labelName); 

}
