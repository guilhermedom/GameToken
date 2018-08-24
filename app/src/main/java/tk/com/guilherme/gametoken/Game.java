package tk.com.guilherme.gametoken;

public class Game {

    private int gameId;
    private String name;
    private String platform;
    private String imageUrl;
    private String trailerUrl;
    private String releaseDate;

    private int image;


    public Game(String id, String name, String platforms, String imageUrl, String trailerUrl, String releaseDate) {
        this.gameId = gameId;
        this.name = name;
        this.platform = platform;
        this.imageUrl = imageUrl;
        this.trailerUrl = trailerUrl;
        this.releaseDate = releaseDate;
    }

    public Game(int gameId, String name, String platform, String imageUrl, String trailerUrl, String releaseDate, int image) {
        this.gameId = gameId;
        this.name = name;
        this.platform = platform;
        this.imageUrl = imageUrl;
        this.trailerUrl = trailerUrl;
        this.releaseDate = releaseDate;
        this.image = image;
    }

    public int getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public int getImage() {
        return image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
