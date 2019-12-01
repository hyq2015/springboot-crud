package springbootcrud.dto;

public class ArticleShow extends Article {
    private UserShow user;

    public UserShow getUser() {
        return user;
    }

    public void setUser(UserShow user) {
        this.user = user;
    }
}
