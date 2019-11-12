package com.example.demo.controllers;

import com.example.demo.models.SimpleModel;
import com.example.demo.models.SimpleModelBuilder;
import com.example.demo.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private KafkaTemplate<String, SimpleModel> kafkaTemplate;
    private ModelRepository modelRepository;
    private Twitter twitter;

    @Autowired
    public KafkaController(KafkaTemplate<String, SimpleModel> kafkaTemplate, ModelRepository modelRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.modelRepository = modelRepository;

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("ELBckB6q0xHIwbqqBqkKI2sow")
                .setOAuthConsumerSecret("g2hf5Ad8esOHIOWKkAsnvkDaTS9mpxwWROhhnRL5bS6BFAECqK")
                .setOAuthAccessToken("1097218198502555648-hCSfqDk6HmXDvrV4HY2PfHlxgzWpLl")
                .setOAuthAccessTokenSecret("iDiDbxI6QmcCgb0vuHp6xpO3uHyKE3M45SMLs5VWgTxQt");
        TwitterFactory tf = new TwitterFactory(cb.build());
        this.twitter = tf.getInstance();
    }

    @PostMapping("/send")
    public void post(@RequestBody SimpleModel simpleModel) {
        kafkaTemplate.send("myTopic", simpleModel);
    }

    @KafkaListener(topics = "myTopic")
    public void getFromKafka(SimpleModel simpleModel) {
        System.out.println(simpleModel.toString());
        modelRepository.insert(simpleModel);
    }

    @GetMapping("/getAll")
    public List<SimpleModel> getAll() {
        return modelRepository.findAll();
    }

    @GetMapping("/deleteAll")
    public List<SimpleModel> deleteAll() {
        modelRepository.deleteAll();
        return modelRepository.findAll();
    }

    @PostMapping("/queryTwitter")
    public String queryTwitter(@RequestBody String queryCriteria) {
        try {
            Query query = new Query(queryCriteria);
            query.setCount(100);
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {

                SimpleModelBuilder modelBuilder = new SimpleModelBuilder();
                modelBuilder.setUser((status.getUser().getScreenName()));
                modelBuilder.setText(status.getText());
                modelBuilder.setDate(status.getCreatedAt());
                modelBuilder.setLocation(status.getGeoLocation());
                modelBuilder.setFavoriteCount(status.getFavoriteCount());

                //modelRepository.insert(modelBuilder.build());
                kafkaTemplate.send("myTopic", modelBuilder.build());
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return "Successfully inserted tweets containing " + queryCriteria + " into MongoDB";
    }

    @PostMapping("/queryMongoId")
    public List<SimpleModel> queryMongoId(@RequestBody String queryCriteria) {
        return modelRepository.findSimpleModelByIdContaining(queryCriteria);
    }

    @PostMapping("/queryMongoUser")
    public List<SimpleModel> queryMongoUser(@RequestBody String queryCriteria) {
        return modelRepository.findSimpleModelByUserContaining(queryCriteria);
    }

    @PostMapping("/queryMongoText")
    public List<SimpleModel> queryMongoText(@RequestBody String queryCriteria) {
        return modelRepository.findSimpleModelByTextContaining(queryCriteria);
    }

    @PostMapping("/queryMongoDate")
    public List<SimpleModel> queryMongoDate(@RequestBody Date queryCriteria) {
        return modelRepository.findSimpleModelByDateContaining(queryCriteria);
    }

}
