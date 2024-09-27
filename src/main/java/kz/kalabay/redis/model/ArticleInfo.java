package kz.kalabay.redis.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArticleInfo {
    private String id;
    private String title;
    private String text;
    private double rating;
}
