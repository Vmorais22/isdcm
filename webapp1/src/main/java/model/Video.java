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
    private int creationYear;
    private int creationMonth;
    private int creationDay;
    private String duration;
    private int views;
    private String description;
    private String format;
    private String url;
    private String miniature;
    private boolean encripted;

    public Video(int videoId, String title, String author, int creationYear, int creationMonth, int creationDay, String duration, int views, String description, String format, String url, String miniature) {
        this.videoId = videoId;
        this.title = title;
        this.author = author;
        this.creationYear = creationYear;
        this.creationMonth = creationMonth;
        this.creationDay = creationDay;
        this.duration = duration;
        this.views = views;
        this.description = description;
        this.format = format;
        this.url = url;
        this.miniature = miniature;
        this.encripted = false;
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

       public int getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }
    
    public int getCreationMonth() {
        return creationMonth;
    }

    public void setCreationMonth(int creationMonth) {
        this.creationMonth = creationMonth;
    }

    
    public int getCreationDay() {
        return creationDay;
    }

    public void setCreationDay(Integer creationDay) {
        this.creationDay = creationDay;
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
    
    public boolean getEncripted() {
        return this.encripted;
    }

    public void setEncripted(boolean encripted) {
        this.encripted = encripted;
    }
    public boolean storeVideoInDb() throws ClassNotFoundException, SQLException {

        Date sqlDate = new Date(System.currentTimeMillis());

            int year = Integer.parseInt(sqlDate.toString().substring(0 , 4));
            int month = Integer.parseInt(sqlDate.toString().substring(5 , 7));
            int day = Integer.parseInt(sqlDate.toString().substring(8 , 10));
        String INSERT_QUERY = "INSERT INTO VIDEOS "
            + "(videoId, title, author, creationYear, creationMonth, creationDay, duration, views, description, format, url, miniature) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");

        PreparedStatement preparedStatement = c.prepareStatement(INSERT_QUERY);
        preparedStatement.setInt(1, this.getVideoId());
        preparedStatement.setString(2, this.getTitle());
        preparedStatement.setString(3, this.getAuthor());
        preparedStatement.setInt(4, year);
        preparedStatement.setInt(5, month);
        preparedStatement.setInt(6, day);
        preparedStatement.setString(7, this.getDuration());
        preparedStatement.setInt(8,  this.getViews()); //new videos always will have 0 views when created
        preparedStatement.setString(9, this.getDescription());
        preparedStatement.setString(10, this.getFormat());
        preparedStatement.setString(11, this.getUrl());
        preparedStatement.setString(12, this.getMiniature());
        preparedStatement.executeUpdate();
        return true;
    }
}
