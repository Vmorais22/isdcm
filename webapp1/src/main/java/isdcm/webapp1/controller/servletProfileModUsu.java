/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.webapp1.controller;

import controller.Exceptions.UserDontExistsException;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;

/**
 *
 * @author Victor
 */
@WebServlet(name = "servletProfileModUsu", urlPatterns = {"/servletProfileModUsu"})

public class servletProfileModUsu extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // none  
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(updateUser(request)){
                request.getSession().setAttribute("currentUser", request.getParameter("username"));
                response.sendRedirect("/webapp1/jsp/profileUsu.jsp");
            }
            response.setStatus(HttpServletResponse.SC_OK);
            
        } 
        catch (UserDontExistsException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.err.println("User don't exists");
        }
        catch (Exception e)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.err.println("Unexpected error ocurred: " + e);
        }              

        
    }

    private static boolean exists(String username) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
        PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM USERS WHERE username = ?");
        preparedStatement.setString(1, username);
        return preparedStatement.executeQuery().next();
    }
    
    private boolean updateUser(HttpServletRequest request) throws  MalformedURLException, SQLException, ClassNotFoundException, UserDontExistsException {
        String currentUsername = request.getSession().getAttribute("currentUser").toString();
        if (exists(currentUsername)) {
            Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            PreparedStatement preparedStatement = c.prepareStatement("SELECT USERID, PHOTO FROM USERS WHERE username = ?");
            preparedStatement.setString(1, currentUsername);
            ResultSet r = preparedStatement.executeQuery();
            r.next();
            Integer userId = r.getInt("USERID");
            String photo = r.getString("PHOTO");
 
            Usuario newUser = new Usuario(
                    userId,
                    request.getParameter("username"),
                    request.getParameter("realName"),
                    request.getParameter("surName"),
                    request.getParameter("passwd"),
                    request.getParameter("email"),
                    parseInt(request.getParameter("age")),
                    request.getParameter("description"),
                    request.getParameter("photo").isEmpty() ? photo : request.getParameter("photo")
            );
            return newUser.updateUserInDb(userId);
        } 
        else 
        {
            throw new UserDontExistsException();
        }
    }
}
