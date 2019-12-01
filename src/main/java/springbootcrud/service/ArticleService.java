package springbootcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootcrud.dao.ArticleMapper;
import springbootcrud.dto.Article;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public Article getArticleById(Integer id) {
        return articleMapper.getArticleById(id);
    }
}
