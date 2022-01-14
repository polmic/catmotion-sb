package catmotion.controllers;

import catmotion.models.VideoModel;
import catmotion.repositories.VideosRepository;
import catmotion.services.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

@RestController
public class VideosController {

    @Autowired
    VideosService videosService;

    @Autowired
    VideosRepository videosRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/videos")
    @ResponseBody
    public ResponseEntity<List<VideoModel>> getAllVideos() {
        List<VideoModel> videos = videosService.getAllVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/videos/stream/{id}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable("id") String id) {
        VideoModel video = videosService.getVideoById(id);
        InputStream inputStream = new ByteArrayInputStream(video.getData());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("video/mkv"));
        headers.set("Accept-Ranges", "bytes");
        headers.set("Expires", "0");
        headers.set("Cache-Control", "no-cache, no-store");
        headers.set("Connection", "keep-alive");
        headers.set("Content-Transfer-Encoding", "binary");

        return new ResponseEntity<>(new InputStreamResource(inputStream), headers, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/videos/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        videosService.deleteVideoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
