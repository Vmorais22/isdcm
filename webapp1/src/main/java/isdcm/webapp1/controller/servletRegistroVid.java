package isdcm.webapp1.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Exceptions.VideoAlreadyExistsException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Video;
import java.util.Random;

@WebServlet(name = "servletRegistroVid", urlPatterns = {"/servletRegistroVid"})
public class servletRegistroVid extends HttpServlet {

    private static final String INSERT_QUERY = "INSERT INTO VIDEOS "
            + "(videoId, title, author, creationDate, duration, views, description, format) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {    
                out.println("<html><body>Method not supported</body></html>");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("llego al do post");       
        
        try {
            if(createNewVideo(request)){
                response.sendRedirect("/webapp1/jsp/listadoVid.jsp");
            }
            response.setStatus(HttpServletResponse.SC_OK);
            
        } 
        catch (VideoAlreadyExistsException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.err.println("User already exists");
        }
        catch (Exception e)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.err.println("Unexpected error ocurred: " + e);
        }       
    }

    private boolean createNewVideo(HttpServletRequest request) throws VideoAlreadyExistsException, SQLException, ClassNotFoundException {
        System.out.println("Entro en createNewVideo");       
        
            Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            PreparedStatement preparedStatement = c.prepareStatement("SELECT MAX(VIDEOID) as VIDEOID FROM VIDEOS");
            ResultSet r = preparedStatement.executeQuery();
            r.next();
            
            Random random = new Random();
            int randomHours = random.nextInt(20);
            int randomMinutes = random.nextInt(59);
            int randomSeconds = random.nextInt(59);
            String duracionHours = String.valueOf(randomHours);
            String duracionMinutes = String.valueOf(randomMinutes);
            String duracionSeconds = String.valueOf(randomSeconds);

            Integer videoId = r.getInt("VIDEOID")+1;
            Video newVideo = new Video(
                    videoId,
                    request.getParameter("title"),
                    request.getSession().getAttribute("currentUser").toString(),//tendria que ser el username del perfil 
                    0,
                    0,
                    0,
                    duracionHours+":"+duracionMinutes+":"+duracionSeconds,
                    0,
                    request.getParameter("description"),
                    request.getParameter("format"),
                    request.getParameter("url"),
                    request.getParameter("miniature")
            );
            return newVideo.storeVideoInDb();
            
    }
}
