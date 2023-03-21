package com.kenzie.capstone.service;

import com.kenzie.capstone.service.converter.VideoGameConverter;
import com.kenzie.capstone.service.dao.VideoGameDao;
import com.kenzie.capstone.service.exceptions.InvalidGameException;
import com.kenzie.capstone.service.model.VideoGameRecord;
import com.kenzie.capstone.service.model.VideoGameRequest;
import com.kenzie.capstone.service.model.VideoGameResponse;

import javax.inject.Inject;

public class VideoGameService {
    private VideoGameDao videoGameDao;
    @Inject
    public VideoGameService(VideoGameDao videoGameDao){
        this.videoGameDao = videoGameDao;
    }

    public VideoGameRecord getVideoGame(String name){
       VideoGameRecord record = videoGameDao.findByName(name);
       if(record == null){
           throw new InvalidGameException("Invalid Game Name Inputted");
       }
       return record;
    }

    public VideoGameResponse addVideoGame(VideoGameRequest request){
        if (request == null) {
            throw new InvalidGameException("Request must contain a valid information");
        }
        VideoGameRecord record = VideoGameConverter.fromRequestToRecord(request);
        videoGameDao.addVideoGame(record);
        return VideoGameConverter.fromRecordToResponse(record);
    }

    public boolean deleteVideoGame(String videoGameName){
        VideoGameRecord record = videoGameDao.findByName(videoGameName);
            if(record == null){
                throw new InvalidGameException("Request must contain a valid video game name");
         }
            boolean deleted = videoGameDao.deleteVideoGame(record);

                if(!deleted){
                   return false;
              }
        return deleted;
    }

}
