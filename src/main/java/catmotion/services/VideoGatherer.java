package catmotion.services;

import catmotion.models.VideoModel;
import catmotion.repositories.VideosRepository;
import catmotion.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
    VideosRepository videosRepository;

    @Scheduled(fixedRateString = "${videos.gathering.rate}")
    public void gatherVideos() throws IOException {
        Set<String> filenames = getFilenames(VIDEOS_DIRECTORY_PATH);
        for (String filename : filenames) {
            if (isMkvFile(filename) && !isVideoInDatabase(filename)) {
                Path path = Paths.get(VIDEOS_DIRECTORY_PATH + filename);
                long size = Files.size(path);
                byte[] bytes = Files.readAllBytes(path);
                videosRepository.save(new VideoModel(filename, size, bytes));
                Files.delete(path);
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
        return videosRepository.findItemByName(name) != null;
    }

    private boolean isMkvFile(String name) {
        return name.contains(Constants.FileTypes.MKV);
    }

}