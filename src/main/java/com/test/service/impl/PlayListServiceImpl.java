package com.test.service.impl;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.PlayListDTO;
import com.test.model.AccessTokenEntity;
import com.test.model.PlayListEntity;
import com.test.model.UserEntity;
import com.test.repository.AccessTokenRepository;
import com.test.repository.PlayListRepository;
import com.test.service.IAuthenticatedUserService;
import com.test.service.IPlayListService;

@Service
public class PlayListServiceImpl implements IPlayListService {
	
	@Autowired
	private PlayListRepository playListRepository;
	@Autowired
	private   IAuthenticatedUserService authenticatedUserService;
	@Autowired
	private AccessTokenRepository accessTokenRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	
	private static final String ALPHANUMERIC_CHARS =   "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	 private final SecureRandom random = new SecureRandom(); 
	 private static final int codeLength=6;


	@Override
	public void savePlayList(PlayListEntity playList) {
		// TODO Auto-generated method stub
		UserEntity userEntity =authenticatedUserService.getCurrentUser();
		
		playList.setUser(userEntity);
		AccessTokenEntity token = new AccessTokenEntity();
		token.setActive(true);
		token.setToken(generateRandomToken());
		token.setCreateAt(LocalDateTime.now());
		token.setExpireDate(LocalDateTime.now().plusDays(30));
		token = accessTokenRepository.save(token);
		playList.setAccessToken(token);
		
		playListRepository.save(playList);

	}

	@Override
	public void generateAccessToken(Integer playlistId,Integer days) {
		// TODO Auto-generated method stub
		PlayListEntity entity= playListRepository.findById(playlistId).orElseThrow(()-> new RuntimeException("Play list not found exception"));
		AccessTokenEntity token = new AccessTokenEntity();
		token.setActive(true);
		token.setToken(generateRandomToken());
		token.setCreateAt(LocalDateTime.now());
		token.setExpireDate(LocalDateTime.now().plusDays(days));
		token = accessTokenRepository.save(token);
		
		entity.setAccessToken(token);
		playListRepository.save(entity);
		
	}
	
	private String generateRandomToken() {
		
		 StringBuilder sb = new StringBuilder(codeLength);
	        for (int i = 0; i < codeLength; i++) {
	            int index = random.nextInt(ALPHANUMERIC_CHARS.length());
	            sb.append(ALPHANUMERIC_CHARS.charAt(index));
	        }
	        return sb.toString();
	
	}

	@Override
	public List<PlayListDTO> getPlaylistList() {
		
		UserEntity userEntity =authenticatedUserService.getCurrentUser();
		List<PlayListEntity> playLists= playListRepository.findByUserUserId(userEntity.getUserId());
		return playLists.stream().map(playlist-> {
		    PlayListDTO dto = new PlayListDTO();
			modelMapper.map(playlist,dto);
			dto.setNoOfTestSeries(0);
		   return dto;
		}).toList();
		
	}

	@Override
	public Map<Integer, String> getPlaylistNamesWithId() {
		// TODO Auto-generated method stub
		UserEntity userEntity =authenticatedUserService.getCurrentUser();
		List<PlayListEntity> playLists= playListRepository.findByUserUserId(userEntity.getUserId());
		return playLists.stream().collect(Collectors.toMap(PlayListEntity::getTestListId, PlayListEntity::getTitle));
		
	}

}
