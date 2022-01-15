package catmotion.services;

import catmotion.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class VideoGatherer {

    @Value("${videos.directory.path}")
    private String VIDEOS_DIRECTORY_PATH;

    @Autowired
    VideosService videosService;

    @Scheduled(fixedRateString = "${videos.gathering.rate}")
    public void gatherVideos() throws IOException {
        Set<String> filenames = getFilenames(VIDEOS_DIRECTORY_PATH);
        for (String filename : filenames) {
            if (isMkvFile(filename) && !isVideoInDatabase(filename)) {
                File videoFile = new File(VIDEOS_DIRECTORY_PATH + filename);
                InputStream stream =  new FileInputStream(videoFile);
                MultipartFile multipartFile = new MockMultipartFile(filename, stream);
                videosService.save(filename, multipartFile);
                Files.delete(Paths.get(VIDEOS_DIRECTORY_PATH + filename));
            }
        }
    }

    private Set<String> getFilenames(String directory) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(directory))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

    private boolean isVideoInDatabase(String name) {
        return videosService.findItemByName(name) != null;
    }

    private boolean isMkvFile(String name) {
        return name.contains(Constants.FileTypes.MKV);
    }

}