package example.redis.redisexample.redis.controller;

import example.redis.redisexample.redis.service.ContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ContentRestController {

    public final RedisTemplate redisTemplate;

    public final ContentService contentService;

    @PostMapping("/content/addContent")
    public ContentDto addContent(HttpServletRequest request, HttpServletResponse response, @RequestBody ContentDto dto) throws Exception {

        return contentService.addContent(dto);
    }

    @PostMapping("/content/removeContent")
    public String removeContent(HttpServletRequest request, HttpServletResponse response, ContentDto dto) throws Exception {
        contentService.removeContent(dto);
        return "OK";
    }


    @GetMapping("/content/findContent")
    public ContentDto findContent(HttpServletRequest request, HttpServletResponse response, ContentDto dto) throws Exception {
        ContentDto content1 = contentService.findContent(dto);

        ContentDto content2 = contentService.findContent(dto);

        return content1;
    }

    @GetMapping("/content/findAll")
    public List<ContentDto> findContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<ContentDto> contents1 = contentService.findAll();

        return contents1;
    }

}
