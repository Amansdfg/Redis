package kz.kalabay.redis.controller;

import kz.kalabay.redis.model.Article;
import kz.kalabay.redis.model.ArticleInfo;
import kz.kalabay.redis.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
    @Autowired
    private  ArticleService articleService;
    @GetMapping("/trending")
    public ArticleInfo getRandomArticle(){
        return articleService.getRatingArticles();
    }
    @GetMapping("/article/{id}")
    public ArticleInfo getArticleById(@PathVariable("id") Long  id){
        return articleService.getCachedArticle(id);
    }
    @GetMapping("/{id}")
    public Article getArticle(@PathVariable("id") Long  id){
        return articleService.getArticleById(id);
    }
}
