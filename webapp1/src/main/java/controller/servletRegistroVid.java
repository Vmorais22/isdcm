package controller;

import java.io.IOException;
import static java.lang.Integer.parseInt;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.Video;

@WebServlet(name = "servletListadoVid", urlPatterns = {"/servletListadoVid"})
public class servletRegistroVid extends HttpServlet {

    private static final String INSERT_QUERY = "INSERT INTO VIDEOS "
            + "(videoId, title, author, creationDate, duration, views, description, format) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM VIDEOS WHERE videoId = ?";

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
                response.sendRedirect("/webapp1/jsp/profileUsu.jsp");
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
        
            System.out.println("El video no existe");    
            Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            PreparedStatement preparedStatement = c.prepareStatement("SELECT MAX(VIDEOID) as VIDEOID FROM VIDEOS");
            ResultSet r = preparedStatement.executeQuery();
            r.next();

            Integer videoId = r.getInt("VIDEOID")+1;
            System.out.println("El proximo userid será " + videoId);
            Video newVideo = new Video(
                    videoId,
                    request.getParameter("title"),
                    "autor",//tendria que ser el username del perfil 
                    //LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")), //actual
                    "2015-12-17",
                    "13:30",//valor random (ThreadLocalRandom.current().nextInt(0, 10)).toString() + ":" + (ThreadLocalRandom.current().nextInt(0, 10)).toString()
                    0,
                    request.getParameter("description"),
                    request.getParameter("format")
            //new URL(request.getParameter("url")),
            //new URL(request.getParameter("image")),
            );
            System.out.println("Video object creado");

            return newVideo.storeVideoInDb();
            
    }
}
