package example.redis.redisexample.redis.controller;

import example.redis.redisexample.redis.domain.Content;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("contentDto")
public class ContentDto implements Serializable {

    private static final long serialVersionUID = 3077465140312258030L;

    @Id
    String contentId;
    String contentName;
    String contentType;

    public static ContentDto createDTO(Content content) {
        return ContentDto.builder()
                .contentId(content.getContentId())
                .contentName(content.getContentName())
                .contentType(content.getContentType())
                .build();
    }

}
