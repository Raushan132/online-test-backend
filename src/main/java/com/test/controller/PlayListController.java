package com.test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.dto.HttpResponseDTO;
import com.test.dto.PlayListDTO;
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
	
	@GetMapping("/playlists")
	private ResponseEntity<HttpResponseDTO<List<PlayListDTO>>> getAllPlaylist(){
	    List<PlayListDTO> playlists=	playListService.getPlaylistList();
		return ResponseEntity.ok(new HttpResponseDTO<>(HttpStatus.OK, "Fetched", playlists));
	
	}
	
	@GetMapping("/playlistsNames")
	private ResponseEntity<HttpResponseDTO<Map<Integer,String>>> getAllPlaylistNameByUser(){
		
		Map<Integer,String> playListNames = playListService.getPlaylistNamesWithId();
		
		return ResponseEntity.ok(new HttpResponseDTO<>(HttpStatus.OK, "Fetched", playListNames));
	}

}
