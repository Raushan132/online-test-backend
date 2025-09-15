package com.test.service;

import java.util.List;
import java.util.Map;

import com.test.dto.PlayListDTO;
import com.test.model.PlayListEntity;

public interface IPlayListService {
	
	 void savePlayList(PlayListEntity playList);
	 void generateAccessToken(Integer playlistId, Integer days);
	
	 List<PlayListDTO> getPlaylistList();
	 
	 Map<Integer,String> getPlaylistNamesWithId();
	 

}
