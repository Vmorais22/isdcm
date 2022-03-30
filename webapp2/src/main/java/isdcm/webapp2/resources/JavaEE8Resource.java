package isdcm.webapp2.resources;

import com.google.gson.Gson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import model.Video;

/**
 *
 * @author
 */
@Path("javaee8")
public class JavaEE8Resource {
   
    /**
     * Sample of GET method
     * @param titulo
     * @param autor
     * @param año
     * @param mes
     * @param dia
     * @return
     */
    @Path("getInfo")
    @GET    
    @Produces("text/html")
    public String getInfo (@QueryParam("titulo") String titulo, @QueryParam("autor") String autor, @QueryParam("año") String año, @QueryParam("mes") String mes, @QueryParam("dia") String dia ) {

        try
        {
           ResultSet result = new Video().searchVideosBy(titulo,autor,año,mes,dia);
           
           if(result != null) {
               List<Video> videosFound = new ArrayList<>();                
               while(result.next()){
                   Video video = new Video( result.getInt("videoId"),
                                            result.getString("title"),
                                            result.getString("author"),
                                            result.getInt("creationYear"),
                                            result.getInt("creationMonth"),
                                            result.getInt("creationDay"),
                                            result.getString("duration"),
                                            result.getInt("views"),
                                            result.getString("description"),
                                            result.getString("format"),
                                            result.getString("url"),
                                            result.getString("miniature"));
                   videosFound.add(video); 
               }
               
               Gson gson = new Gson();  
               String body = gson.toJson(videosFound);  
               return body;
           }
           else {
               return null;
           }
        }
        catch(ClassNotFoundException | SQLException e){
            System.err.print("errorcito al canto causado por: " + e);
        } 
        return null;
    }
   
    /**
     * Sample of POST method
     *
     * @param info
     * @param fecha
     * @return
     */
    @Path("postInfo")  
    @POST    
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/html")
    public String postInfo (  @FormParam("info") String info,
                              @FormParam("fecha") String fecha)
    {                
        return "<html><head></head> <body> Informaci&oacute;n recibida " + info + " en fecha " + fecha + " </body></html>";
    }    
   
    /**
     * Sample of PUT method
     *
     * @param id
     * @return
     */
    @Path("putInfo")  
    @PUT    
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/html")
    public String putInfo (@QueryParam("id") String id) 
    {    
        try
        {
           String videoId = id.substring(0,id.length()-1);
           new Video().updateVideoById(Integer.parseInt(videoId)); 
        }
        catch(ClassNotFoundException | NumberFormatException | SQLException e){
        }
        
        return "<html><head></head> <body> Informaci&oacute;n recibida " + id + " en fecha " + id + " </body></html>";
    }
}

