/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Exceptions.UserDontExistsException;
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
    
    public Usuario(){}
    
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

    public boolean queryTest(String username, String passwd) {
        boolean result = true;
        Connection c = null;
        try {
            PreparedStatement statement;
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            String query = "select count(*) from USERS where username = ? and password = ?";
            statement = c.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, passwd);
            ResultSet r = statement.executeQuery();
            if (r.next())
            {
                if (r.getInt(1) == 0)
                    result = false;
            }          
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println("error: "+e);
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

    public Object getProfile(String username) {
        Usuario result = null;
        Connection c = null;
        try {
            System.out.println("1");
            PreparedStatement statement;
            System.out.println("2");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("3");
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            System.out.println("4");String query = "select * from USERS where username = ?";
            System.out.println("5");statement = c.prepareStatement(query);
            System.out.println("6");statement.setString(1, username);
            System.out.println("7");ResultSet r = statement.executeQuery();
            System.out.println("8");
            if (r.next())
            {
                if (r.getInt(1) == 0)
                    throw new UserDontExistsException();
                else
                    result = new Usuario(r.getInt("userId"),r.getString("username"),r.getString("realName"),r.getString("surname"),r.getString("password"),r.getString("email"),r.getInt("age"),r.getString("description"),r.getURL("photo"));
                
                
            }
            System.out.println("9");
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println("error: "+e);
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
