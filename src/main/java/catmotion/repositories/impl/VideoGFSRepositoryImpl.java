package catmotion.repositories.impl;

import catmotion.repositories.VideosGFSRepository;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class VideoGFSRepositoryImpl implements VideosGFSRepository {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations operations;

    @Override
    public String save(String name, MultipartFile file) throws IOException {
        ObjectId id = gridFsTemplate.store(file.getInputStream(), file.getName(), file.getContentType());
        return id.toString();
    }

    @Override
    public InputStream getVideoStreamById(String id) throws IOException {
        GridFSFile fsFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        GridFsResource gfsResource = operations.getResource(fsFile);
        return gfsResource.getInputStream();
    }

    @Override
    public void deleteById(String id) {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
    }

}
