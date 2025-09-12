package com.test.service;

import com.test.model.PlayListEntity;

public interface IPlayListService {
	
	 void savePlayList(PlayListEntity playList);
	 void generateAccessToken(Integer playlistId, Integer days);
	 

}
