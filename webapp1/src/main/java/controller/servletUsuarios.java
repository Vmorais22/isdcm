package controller;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Exceptions.UserAlreadyExistsException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.Usuario;

@WebServlet(name = "servletListadoVid", urlPatterns = {"/servletListadoVid"})
public class servletUsuarios extends HttpServlet {

    private static final String INSERT_QUERY = "INSERT INTO USERS "
            + "(userId, username, realName, surname, password, email, age, description, photo) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM VIDEOS WHERE userId = ?";

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
       /* try {
            
            createNewUser(request);
            response.setStatus(HttpServletResponse.SC_OK);
        } 
        catch (UserAlreadyExistsException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.err.println("User already exists");
        }
        catch (Exception e)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.err.println("Unexpected error ocurred: " + e);
        }*/                
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String username = request.getParameter("username");
            String passwd = request.getParameter("passwd");
            
            boolean result = new Usuario().queryTest(username, passwd);
            
            if (result){
                request.getSession().setAttribute("currentUser", username);
                //request.setAttribute("dataProfile", new Usuario().getProfile(username));
                response.sendRedirect("/webapp1/jsp/profileUsu.jsp");
            }
            else response.sendRedirect(request.getContextPath());    
        }
    }

    private static boolean exists(int userId) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr21;user=pr21;password=pr21");
        PreparedStatement preparedStatement = c.prepareStatement(SELECT_BY_ID);
        preparedStatement.setInt(1, userId);
        return preparedStatement.executeQuery().next();
    }
    
    private void createNewUser(HttpServletRequest request) throws UserAlreadyExistsException, MalformedURLException, SQLException, ClassNotFoundException {
        if (!exists(parseInt(request.getParameter("userId")))) {
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
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr21;user=pr21;password=pr21");
            PreparedStatement preparedStatement = c.prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, newUser.getUserid());
            preparedStatement.setString(2, newUser.getUserName());
            preparedStatement.setString(3, newUser.getRealName());
            preparedStatement.setString(4, newUser.getSurName());
            preparedStatement.setString(5, newUser.getPassword());
            preparedStatement.setString(6, newUser.getEmail());
            preparedStatement.setInt(7, newUser.getAge());
            preparedStatement.setString(8, newUser.getPhoto().toString());
            //preparedStatement.setBlob(6, new ByteArrayInputStream(video.getDescription().getBytes()) );      
        } 
        else 
        {
            throw new UserAlreadyExistsException();
        }
    }
}
