package model;

public class Video {

    private int videoId;
    private String title;
    private String author;
    private String creationDate;
    private String duration;
    private int views;
    private String description;
    private String format;

    public Video(int videoId, String title, String author, String creationDate, String duration, int views, String description, String format) {
        this.videoId = videoId;
        this.title = title;
        this.author = author;
        this.creationDate = creationDate;
        this.duration = duration;
        this.views = views;
        this.description = description;
        this.format = format;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean storeVideoInDb throws ClassNotFoundException, SQLException {
                    System.out.println("Llegamos al modelo");

           private static final String INSERT_QUERY = "INSERT INTO VIDEOS "
            + "(videoId, title, author, creationDate, duration, views, description, format) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

           Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            PreparedStatement preparedStatement = c.prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, newVideo.getVideoId());
            preparedStatement.setString(2, newVideo.getTitle());
            preparedStatement.setString(3, newVideo.getAuthor());
            preparedStatement.setString(4, newVideo.getCreationDate());
            preparedStatement.setString(5, newVideo.getDuration());
            preparedStatement.setInt(6,  newVideo.getViews()); //new videos always will have 0 views when created
            preparedStatement.setString(7, newVideo.getDescription());
            preparedStatement.setString(8, newVideo.getFormat());
System.out.println("Statement preparado");
           //ResultSet r = preparedStatement.executeUpdate();
           int aux = preparedStatement.executeUpdate();
           //preparedStatement.setBlob(6, new ByteArrayInputStream(video.getDescription().getBytes()) );
System.out.println("hecho");
           return true;

    }
}
