package com.example.demo.repository;

import com.example.demo.models.SimpleModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface ModelRepository extends MongoRepository<SimpleModel, Integer> {

    List<SimpleModel> findSimpleModelById(String id);
    List<SimpleModel> findSimpleModelByIdContaining(String id);

    List<SimpleModel> findSimpleModelByUser(String user);
    List<SimpleModel> findSimpleModelByUserContaining(String user);

    List<SimpleModel> findSimpleModelByText(String text);
    List<SimpleModel> findSimpleModelByTextContaining(String text);

    List<SimpleModel> findSimpleModelByDate(String date);
    List<SimpleModel> findSimpleModelByDateContaining(Date date);

    List<SimpleModel> findSimpleModelByLocation(String location);
    List<SimpleModel> findSimpleModelByLocationContaining(String location);
}
