package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Video {

    private int videoId;
    private String title;
    private String author;
    private String creationDate;
    private String duration;
    private int views;
    private String description;
    private String format;
    private String url;
    private String miniature;

    public Video(int videoId, String title, String author, String creationDate, String duration, int views, String description, String format, String url, String miniature) {
        this.videoId = videoId;
        this.title = title;
        this.author = author;
        this.creationDate = creationDate;
        this.duration = duration;
        this.views = views;
        this.description = description;
        this.format = format;
        this.url = url;
        this.miniature = miniature;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getMiniature() {
        return miniature;
    }

    public void setMiniature(String miniature) {
        this.miniature = miniature;
    }
    public boolean storeVideoInDb() throws ClassNotFoundException, SQLException {

        Date sqlDate = new Date(System.currentTimeMillis());
        
        String INSERT_QUERY = "INSERT INTO VIDEOS "
            + "(videoId, title, author, creationDate, duration, views, description, format, url, miniature) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");

        
        PreparedStatement preparedStatement = c.prepareStatement(INSERT_QUERY);
        preparedStatement.setInt(1, this.getVideoId());
        preparedStatement.setString(2, this.getTitle());
        preparedStatement.setString(3, this.getAuthor());
        preparedStatement.setString(4, sqlDate.toString());
        preparedStatement.setString(5, this.getDuration());
        preparedStatement.setInt(6,  this.getViews()); //new videos always will have 0 views when created
        preparedStatement.setString(7, this.getDescription());
        preparedStatement.setString(8, this.getFormat());
        preparedStatement.setString(9, this.getUrl());
        preparedStatement.setString(10, this.getMiniature());
        preparedStatement.executeUpdate();
        return true;

    }
}
