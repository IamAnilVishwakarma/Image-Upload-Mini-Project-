package com.example.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Images;

@Repository
public interface UploadImageRep extends JpaRepository<Images, Integer> {
	

}
