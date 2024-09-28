package kz.kalabay.redis.controller;

import kz.kalabay.redis.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import kz.kalabay.redis.model.ArticleInfo;
@RestController
public class ArticleController{
    @Autowired
    private ArticleService articleService;
    @GetMapping("/article/{id}")
    public ArticleInfo getById(@PathVariable("id") Long id) {
        return articleService.getByCash(id);
    }
    @GetMapping("/trading")
    public ArticleInfo getRandomArticle(){
        return articleService.getByRandom();
    }
}