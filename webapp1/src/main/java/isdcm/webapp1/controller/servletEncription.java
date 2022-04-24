/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.webapp1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.crypto.NoSuchPaddingException;
import model.Video;

/**
 *
 * @author Victor
 */



@WebServlet(name = "servletEncription", urlPatterns = {"/servletEncription"})
public class servletEncription extends HttpServlet{
    File VIDEO_A_ENCRIPTAR;
    File VIDEO_ENCRIPTADO;
    File VIDEO_DESENCRIPTADO;
    
    private final static String SECRET_KEY_GENERATOR = "AES";
    SecretKey KEY;

    public servletEncription() throws NoSuchAlgorithmException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        KEY = kgen.generateKey();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException {
        ArrayList<Video> list = (ArrayList<Video>) request.getSession().getAttribute("listVideos");
        int indexTratado = Integer.parseInt(request.getParameter("vid").substring(0, request.getParameter("vid").length()-1));
        boolean encrypted = list.get(indexTratado).getEncripted();      
        
        try {
        environtmentInit();
        } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(servletEncription.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Hemos usado vídeos con enlaces, por lo que lo que haremos será encriptar un video que se encuentre en la carpeta de recursos de este proyecto
        if(encrypted) try {
        decryptVideo();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException  ex) {
        Logger.getLogger(servletEncription.class.getName()).log(Level.SEVERE, null, ex);
        }
        else try {
        encryptVideo();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException  ex) {
        Logger.getLogger(servletEncription.class.getName()).log(Level.SEVERE, null, ex);
        }

        list.get(indexTratado).setEncripted(!encrypted);
        request.getSession().setAttribute("listVideos", list);
        response.sendRedirect("/webapp1/jsp/listadoVid.jsp");
    }
    
    private void encryptVideo() throws FileNotFoundException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
  
        FileInputStream fis = new FileInputStream(VIDEO_A_ENCRIPTAR);
        File outfile = VIDEO_ENCRIPTADO;
        int read;
        if(!outfile.exists())
            outfile.createNewFile();


        FileOutputStream fos = new FileOutputStream(outfile);


        Cipher encipher = Cipher.getInstance("AES");


        encipher.init(Cipher.ENCRYPT_MODE, KEY);
        CipherInputStream cis = new CipherInputStream(fis, encipher);
       

        while((read = cis.read())!=-1)
                {
                    fos.write((char)read);
                    fos.flush();
                }   
        fos.close();
    }

    private void decryptVideo() throws FileNotFoundException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException  {
     
        File outfile = VIDEO_ENCRIPTADO;
        int read;
        File decfile = VIDEO_DESENCRIPTADO;
        if(!decfile.exists())
            decfile.createNewFile();
        FileInputStream encfis = new FileInputStream(outfile);
        FileOutputStream decfos = new FileOutputStream(decfile);
        Cipher decipher = Cipher.getInstance("AES");
        decipher.init(Cipher.DECRYPT_MODE, KEY);
        try (CipherOutputStream cos = new CipherOutputStream(decfos,decipher)) {
            while((read=encfis.read())!=-1)
            {
                cos.write(read);
                cos.flush();
            }
            cos.close(); 
        } 
    }

    private void environtmentInit() throws NoSuchAlgorithmException {
        VIDEO_A_ENCRIPTAR = new File("D:\\UPC\\MEI\\SEGUNDO\\ISDCM\\proyecto\\webapp1\\src\\main\\java\\isdcm\\webapp1\\resources\\video2.mp4");
        VIDEO_ENCRIPTADO = new File("D:\\UPC\\MEI\\SEGUNDO\\ISDCM\\proyecto\\webapp1\\src\\main\\java\\isdcm\\webapp1\\resources\\video2_enc.txt");
        VIDEO_DESENCRIPTADO = new File("D:\\UPC\\MEI\\SEGUNDO\\ISDCM\\proyecto\\webapp1\\src\\main\\java\\isdcm\\webapp1\\resources\\video2_dec.mp4");       
    }
}