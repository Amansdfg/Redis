package kz.kalabay.redis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Comment{
    @Id
    private Long id;
    private String content;
    private Integer rating;
}