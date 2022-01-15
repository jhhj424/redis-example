package example.redis.redisexample.redis.service;

import example.redis.redisexample.redis.controller.ContentDto;
import example.redis.redisexample.redis.domain.Content;
import example.redis.redisexample.redis.domain.ContentRepository;
import example.redis.redisexample.redis.util.RedisListCacheEvict;
import example.redis.redisexample.redis.util.RedisListCacheable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentServiceImpl {

    private final ContentRepository repo;

    @Transactional
    @RedisListCacheEvict(value = "CONTENTLIST")
    public ContentDto addContent(ContentDto dto) throws Exception {
        Content content = Content.builder().contentName(dto.getContentName()).contentType(dto.getContentType()).build();
        return ContentDto.createDTO(repo.save(content));
    }

    @Transactional
    @RedisListCacheEvict(value = "CONTENTLIST")
    @CacheEvict(value = "content", key = "{#p0.contentId}")
    public void removeContent(ContentDto dto) throws Exception {
        repo.deleteById(dto.getContentId());
    }

    @Cacheable(value = "content", key = "{#p0.contentId}")
    public ContentDto findContent(ContentDto dto) throws Exception {
        log.info("start findContent .. ");
        Content content = repo.findById(dto.getContentId()).orElse(null);
        return ContentDto.createDTO(content);
    }

    @RedisListCacheable(value = "CONTENTLIST")
    public List<ContentDto> findAll() throws Exception {
        List<ContentDto> contentDtos = new ArrayList<ContentDto>();
        List<Content> contents = repo.findAll();
        for (Content content : contents) {
            contentDtos.add(ContentDto.createDTO(content));
        }
        return contentDtos;
    }
}
