package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("favoriteId")
public class FavoriteBean {
    private int favoriteId;
    private int userId;
    private String url;
    private String comment;
    private int clickCount;

    public int getFavoriteId() {
        return favoriteId;
    }
    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getClickCount() {
        return clickCount;
    }
    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }
}
