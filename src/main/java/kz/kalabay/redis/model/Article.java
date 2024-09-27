package kz.kalabay.redis.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Data
@Entity
@RedisHash("Article")
public class Article {
    @Id
    private String id;
    private String title;
    private String text;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Comment> comments;
    public Article(String id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public Article() {

    }
}
