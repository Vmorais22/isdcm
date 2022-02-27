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
import java.sql.SQLException;
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
        try {
            createNewVideo(request);
            response.setStatus(HttpServletResponse.SC_OK);
        } 
        catch (VideoAlreadyExistsException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.err.println("Video already exists");
        }
        catch (Exception e)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.err.println("Unexpected error ocurred: " + e);
        }
    }

    private static boolean exists(int videoId) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr21;user=pr21;password=pr21");
        PreparedStatement preparedStatement = c.prepareStatement(SELECT_BY_ID);
        preparedStatement.setInt(1, videoId);
        return preparedStatement.executeQuery().next();
    }

    private void createNewVideo(HttpServletRequest request) throws VideoAlreadyExistsException, SQLException, ClassNotFoundException {
        if (!exists(parseInt(request.getParameter("videoId")))) {
            

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
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr21;user=pr21;password=pr21");
            PreparedStatement preparedStatement = c.prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, newVideo.getVideoId());
            preparedStatement.setString(2, newVideo.getTitle());
            preparedStatement.setString(3, newVideo.getAuthor());
            preparedStatement.setString(4, newVideo.getCreationDate());
            preparedStatement.setString(5, newVideo.getDuration());
            preparedStatement.setInt(6, 0); //new videos always will have 0 views when created
            preparedStatement.setString(7, newVideo.getDescription());
            preparedStatement.setString(8, newVideo.getFormat());
            //preparedStatement.setBlob(6, new ByteArrayInputStream(video.getDescription().getBytes()) );
        }
        else {
            throw new VideoAlreadyExistsException();
        }
    }
}
