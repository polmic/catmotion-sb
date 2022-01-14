package catmotion.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("video")
public class VideoModel {

    @Id
    private String id;
    private String name;
    private long size;
    private byte[] data;

    public VideoModel() {
    }

    public VideoModel(String name, long size, byte[] data) {
        this.name = name;
        this.size = size;
        this.data = data;
    }

    public VideoModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
