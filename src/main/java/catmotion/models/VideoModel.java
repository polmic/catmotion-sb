package catmotion.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fs.files")
public class VideoModel {

    @Id
    private String id;
    private String filename;

    public VideoModel() {
    }

    public String getId() { return id; }

    public String getFilename() { return filename; }

}
