package kz.kalabay.redis.service;

import kz.kalabay.redis.model.Article;
import kz.kalabay.redis.model.Comment;
import kz.kalabay.redis.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import kz.kalabay.redis.model.ArticleInfo;

import java.util.Random;

@Service
public class ArticleService{
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private RedisTemplate<String,Article> redisTemplate;
    public Article getById(Long id){
        return articleRepository.findById(id).orElse(null);
//        if(article!=null){
//            return new ArticleInfo(
//                    article.getId(),
//                    article.getTitle(),
//                    article.getText(),
//                    article.getComments().stream().mapToInt(Comment::getRating).average().orElse(0));
//        }
//        return null;
    }
    public ArticleInfo getByCash(Long id){
        String key="article:"+id;
        Article article=redisTemplate.opsForValue().get(key);
        if(article!=null){
            System.out.println("Article found in Redis");
            return new ArticleInfo(article.getId(),
                    article.getTitle(),
                    article.getText(),
                    article.getComments().stream().
                        mapToInt(Comment::getRating).average().orElse(0));
        }else{
            System.out.println("Article is not found in redis, checking database");
            Article articleInfo=getById(id);
            if(articleInfo!=null){
                redisTemplate.opsForValue().set(key, articleInfo);
                return new ArticleInfo(articleInfo.getId(),
                        articleInfo.getTitle(),
                        articleInfo.getText(),
                        articleInfo.getComments().stream().mapToInt(Comment::getRating).average().orElse(0));
            }
        return  null;
        }
    }
    public ArticleInfo getByRandom(){
        long count=articleRepository.count();
        long random=new Random().nextLong(1,count);
        return getByCash(random);


    }

}