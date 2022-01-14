package catmotion.services;

import catmotion.models.VideoModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VideosService {

    List<VideoModel> getAllVideos();

    VideoModel getVideoById(String id);

    void deleteVideoById(String id);

}