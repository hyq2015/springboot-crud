package springbootcrud.common;

public class HttpResult {
    private String type;
    private HttpStatus status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
