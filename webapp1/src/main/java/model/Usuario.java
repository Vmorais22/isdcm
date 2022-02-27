/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author defie
 */
public class Usuario {

    private int userid;
    private String userName;
    private String realName;
    private String surName;
    private String password;
    private String email;
    private int age;
    private String description;
    private URL photo;

    public Usuario() {
        
    }
    public Usuario(int userid, String userName, String realName, String surName, String password, String email, int age, String description, URL photo) {
        this.userid = userid;
        this.userName = userName;
        this.realName = realName;
        this.surName = surName;
        this.password = password;
        this.email = email;
        this.age = age;
        this.description = description;
        this.photo = photo;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getPhoto() {
        return photo;
    }

    public void setPhoto(URL photo) {
        this.photo = photo;
    }

    public String queryTest(String user, String passwd) {
        String result = "El usuario existe";
        Connection c = null;
        try {
            PreparedStatement statement;
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr21;user=pr21;password=pr21");
            String query = "select count(*) from usuarios where id_user = ? and password = ?";
            statement = c.prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, passwd);   
            ResultSet r = statement.executeQuery();
            if (r.next())
            {
                if (r.getInt(1) == 0)
                    result = "El usuario no existe";
            }          
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        } finally {
            try {
                if (c != null) 
                    c.close();                
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
        return result;                        
    }
}
