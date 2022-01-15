package catmotion.repositories;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public interface VideosGFSRepository {

    String save(String name, MultipartFile file) throws IOException;

    InputStream getVideoStreamById(String id) throws IOException;

    void deleteById(String id);

}
