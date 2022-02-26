package controller;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.PreparedStatement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Exceptions.UserAlreadyExistsException;
import controller.Exceptions.VideoAlreadyExistsException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import model.Video;

@WebServlet(name = "servletListadoVid", urlPatterns = {"/servletListadoVid"})
public class servletUsuarios extends HttpServlet{
    
    private static final String INSERT_QUERY = "INSERT INTO USERS "
        + "(userId, username, realName, surname, password, email, age, description, photo) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
   private static final String SELECT_BY_ID = "SELECT * FROM VIDEOS WHERE userId = ?";

    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
            // ... codigo para una peticion GET
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException

    {
        try 
        {
           createNewUser(request); 
        }
        catch(UserAlreadyExistsException e) 
        {
            
        }
        
        try {
            response.setStatus(HttpServletResponse.SC_OK);
                } 
                catch (Exception ex) {
                    System.err.println(ex);
                }
    }
    
     private static boolean exists ( int videoId ) {
        try {
                        Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr21;user=pr21;password=pr21");

            PreparedStatement preparedStatement = c.prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, videoId );
            
            if(preparedStatement.executeQuery().next()) return true;

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        
        return false;
    }
        /*catch (Exception e)
        {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }*/

    private void createNewUser(HttpServletRequest request) throws UserAlreadyExistsException, MalformedURLException {
        if(!exists(parseInt(request.getParameter("userId")))) 
        {
             Usuario newUser = new Usuario( 
                                parseInt(request.getParameter("userId")),
                                request.getParameter("username"),
                                request.getParameter("realName"),
                                request.getParameter("surName"),
                                request.getParameter("password"),
                                request.getParameter("email"), 
                                parseInt(request.getParameter("age")), 
                                request.getParameter("description"), 
                                new URL(request.getParameter("photo"))
                                );
            try {
                
               Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr21;user=pr21;password=pr21");
               PreparedStatement preparedStatement = c.prepareStatement(INSERT_QUERY);
               preparedStatement.setInt(1, newUser.getUserid());
               preparedStatement.setString(2, newUser.getUserName());
               preparedStatement.setString(3, newUser.getRealName() );
               preparedStatement.setString(4, newUser.getSurName());
               preparedStatement.setString(5, newUser.getPassword());
               preparedStatement.setString(6, newUser.getEmail());
               preparedStatement.setInt(7, newUser.getAge());
               preparedStatement.setString(8, newUser.getPhoto().toString());
               //preparedStatement.setBlob(6, new ByteArrayInputStream(video.getDescription().getBytes()) );
            } 
            catch (SQLException ex) 
            {
                System.err.println(ex);
            }   
        }
        else 
        {
            throw new UserAlreadyExistsException();
        }
    }
}