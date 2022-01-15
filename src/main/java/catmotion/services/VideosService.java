package catmotion.services;

import catmotion.models.VideoModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public interface VideosService {

    void save(String filename, MultipartFile multipartFile) throws IOException;

    List<VideoModel> getAllVideos();

    VideoModel findItemByName(String name);

    InputStream getVideoStreamById(String id) throws IOException;

    void deleteVideoById(String id);

}