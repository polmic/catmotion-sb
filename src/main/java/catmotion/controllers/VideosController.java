package catmotion.controllers;

import catmotion.models.VideoModel;
import catmotion.services.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    VideosService videosService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<VideoModel>> getAllVideos() {
        List<VideoModel> videos = videosService.getAllVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @GetMapping("/stream/{id}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable("id") String id) throws IOException {
        InputStream inputStream = videosService.getVideoStreamById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("video/mkv"));
        headers.set("Accept-Ranges", "bytes");
        headers.set("Expires", "0");
        headers.set("Cache-Control", "no-cache, no-store");
        headers.set("Connection", "keep-alive");
        headers.set("Content-Transfer-Encoding", "binary");
        return new ResponseEntity<>(new InputStreamResource(inputStream), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        videosService.deleteVideoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
