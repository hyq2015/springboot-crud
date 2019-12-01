package springbootcrud.dao;

import org.springframework.stereotype.Repository;
import springbootcrud.dto.Article;

// @Mapper或者@MapperScan
@Repository
public interface ArticleMapper {
   public Article getArticleById(Integer id);
   public void insertArticle(Article article);

}
