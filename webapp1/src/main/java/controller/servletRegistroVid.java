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
import Utilities.DBConnection;
import controller.Exceptions.VideoAlreadyExistsException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Video;

@WebServlet(name = "servletListadoVid", urlPatterns = {"/servletListadoVid"})
public class servletRegistroVid extends HttpServlet{
    
    private static final String INSERT_QUERY = "INSERT INTO VIDEOS "
        + "(videoId, title, author, creationDate, duration, views, description, format) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
   private static final String SELECT_BY_ID = "SELECT * FROM VIDEOS WHERE videoId = ?";

    /**
     * servlet init without parameters
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException
    {
	
    }
    /**
     * servlet init with parameters
     * @param conf
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig conf) throws ServletException
    {
	super.init(conf);
	
    }   
    
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
           createNewVideo(request); 
        }
        catch(VideoAlreadyExistsException e) 
        {
            
        }
        
        try {
                    DBConnection.closeConnection();
                    response.setStatus(HttpServletResponse.SC_OK);
                } 
                catch (Exception ex) {
                    System.err.println(ex);
                }
    }
    
     private static boolean exists ( int videoId ) {
        try {
            PreparedStatement preparedStatement = DBConnection.getPreparedStatement(SELECT_BY_ID);
            
            preparedStatement.setInt(1, videoId );
            
            if(preparedStatement.executeQuery().next()) return true;

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally {
            try {
                DBConnection.closeConnection();
            } 
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
        return false;
    }
        /*catch (Exception e)
        {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }*/

    private void createNewVideo(HttpServletRequest request) throws VideoAlreadyExistsException {
        if(!exists(parseInt(request.getParameter("videoId")))) 
        {
             Video newVideo = new Video( 
                                parseInt(request.getParameter("videoId")),
                                request.getParameter("title"),
                                request.getParameter("author"),
                                request.getParameter("creationDate"),
                                request.getParameter("duration"),
                                parseInt(request.getParameter("views")), 
                                request.getParameter("description"), 
                                request.getParameter("format") 
                                //new URL(request.getParameter("url")),
                                //new URL(request.getParameter("image")),
                                );
            try {
                PreparedStatement preparedStatement = DBConnection.getPreparedStatement(INSERT_QUERY);

                preparedStatement.setInt(1, newVideo.getVideoId());
                preparedStatement.setString(2, newVideo.getTitle());
                preparedStatement.setString(3, newVideo.getAuthor() );
                preparedStatement.setString(4, newVideo.getCreationDate());
                preparedStatement.setString(5, newVideo.getDuration());
                preparedStatement.setInt(6, 0 ); //new videos always will have 0 views when created
                preparedStatement.setString(7, newVideo.getDescription());
                preparedStatement.setString(8, newVideo.getFormat());
                //preparedStatement.setBlob(6, new ByteArrayInputStream(video.getDescription().getBytes()) );
            } 
            catch (SQLException ex) 
            {
                System.err.println(ex);
            }   
        }
        else 
        {
            throw new VideoAlreadyExistsException();
        }
    }
}
