package springbootcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springbootcrud.dto.Article;
import springbootcrud.service.ArticleService;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/getArticleById")
    public Article getArticleById(@RequestParam(name = "id") Integer id) {
        return articleService.getArticleById(id);
    }
}
