package kz.kalabay.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleInfo {
    private Long id;
    private String title;
    private String text;
    private double rating;
}