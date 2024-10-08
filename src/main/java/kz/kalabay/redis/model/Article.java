package kz.kalabay.redis.model;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("article")
public class  Article{
    @Id
    private Long id;
    private String title;
    private String text;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Comment> comments;
}