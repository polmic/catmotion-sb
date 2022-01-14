package catmotion.services.impl;

import catmotion.models.VideoModel;
import catmotion.repositories.VideosRepository;
import catmotion.services.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideosServiceImpl implements VideosService {

    @Autowired
    VideosRepository videosRepository;

    @Override
    public List<VideoModel> getAllVideos() {
        return videosRepository.findAll();
    }

    @Override
    public VideoModel getVideoById(String id) {
        return videosRepository.findItemById(id);
    }

    @Override
    public void deleteVideoById(String id) {
        videosRepository.deleteById(id);
    }

}
