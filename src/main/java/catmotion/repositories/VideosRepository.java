package catmotion.repositories;

import catmotion.models.VideoModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideosRepository extends MongoRepository<VideoModel, String> {

    @Query(value = "{}")
    List<VideoModel> findAll();

    @Query(value = "{filename:'?0'}")
    VideoModel findItemByName(String name);

}
