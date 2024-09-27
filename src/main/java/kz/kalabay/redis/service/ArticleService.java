package kz.kalabay.redis.service;

import kz.kalabay.redis.model.Article;
import kz.kalabay.redis.model.ArticleInfo;
import kz.kalabay.redis.model.Comment;
import kz.kalabay.redis.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private RedisTemplate<String, Article> redisTemplate;
    public ArticleInfo getArticle(Long id){
        return articleRepository.findById(id).map(article -> new ArticleInfo(
                article.getId(),
                article.getTitle(),
                article.getText(),
                article.getComments().stream().mapToInt(Comment::getRating).average().orElse(0)
                )).orElse(null);
    }
    public ArticleInfo getCachedArticle(Long id) {
        String key="article:"+id;
        Article cachedArticle = redisTemplate.opsForValue().get(key);
        if (cachedArticle != null) {
            System.out.println("Article found in Redis: " + cachedArticle);
            return new ArticleInfo(
                    cachedArticle.getId(),
                    cachedArticle.getTitle(),
                    cachedArticle.getText(),
                    cachedArticle.getComments().stream().mapToInt(Comment::getRating).average().orElse(0)
                    );
        }else {
            System.out.println("Article not found in Redis, checking database...");
            Article article = articleRepository.findById(id).orElse(null);
            if (article != null) {
                redisTemplate.opsForValue().set(key, article, 60);
                return new ArticleInfo(
                        article.getId(),
                        article.getTitle(),
                        article.getText(),
                        article.getComments().stream().mapToInt(Comment::getRating).average().orElse(0)
                );
            }

        }
        return null;

    }
    public ArticleInfo getRatingArticles() {
        long count = articleRepository.count();
        long articleNum=new Random().nextLong(1,count);
        return getCachedArticle(articleNum);
    }
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }
}
