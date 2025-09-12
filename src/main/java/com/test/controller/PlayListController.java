package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.dto.HttpResponseDTO;
import com.test.model.PlayListEntity;
import com.test.service.IPlayListService;

@CrossOrigin
@RestController
public class PlayListController {
	
	@Autowired
	private IPlayListService playListService;
	
	@PostMapping("/save-playlist")
	private ResponseEntity<HttpResponseDTO<String>> savePlaylist(@RequestBody PlayListEntity entity) {
		
		
		playListService.savePlayList(entity);
	  return ResponseEntity.ok(new HttpResponseDTO<>(HttpStatus.ACCEPTED, "Saved", "Saved Successful"));
		
	}

}
