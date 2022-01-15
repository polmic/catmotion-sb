package catmotion.services.impl;

import catmotion.models.VideoModel;
import catmotion.repositories.VideosGFSRepository;
import catmotion.repositories.VideosRepository;
import catmotion.services.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class VideosServiceImpl implements VideosService {

    @Autowired
    VideosGFSRepository videosGFSRepository;

    @Autowired
    VideosRepository videosRepository;

    @Override
    public void save(String filename, MultipartFile multipartFile) throws IOException {
        videosGFSRepository.save(filename, multipartFile);
    }

    @Override
    public List<VideoModel> getAllVideos() {
        return videosRepository.findAll();
    }

    @Override
    public VideoModel findItemByName(String name) {
        return videosRepository.findItemByName(name);
    }

    @Override
    public InputStream getVideoStreamById(String id) throws IOException {
        return videosGFSRepository.getVideoStreamById(id);
    }

    @Override
    public void deleteVideoById(String id) {
        videosGFSRepository.deleteById(id);
    }

}
