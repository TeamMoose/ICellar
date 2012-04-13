package icellar;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex Mazzeo
 */
public class FTPHelper 
{
    public static final String urlProtocol = "ftp://";
    public static final String ftpUser = "reverteddesignscom:";
    public static final String ftpPass = "Omega3d10!";
    public static final String ftpSite = "@ftp.reverteddesigns.com/iCellar";
    public static final String ftpEnd = ";type=i";
    public static final String baseURL = urlProtocol + ftpUser + ftpPass + ftpSite;
    
    public static InputStream getFTPInputStream( String filepath )
    {
        try {
            URL url = new URL( baseURL + filepath + ftpEnd );
            URLConnection urlc = url.openConnection();
            return urlc.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(FTPHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static BufferedWriter getFTPOutputStream( String filepath )
    {
        try {
            URL url = new URL( baseURL + filepath + ftpEnd );
            URLConnection urlc = url.openConnection();
            return new BufferedWriter( new OutputStreamWriter( urlc.getOutputStream() ) );
        } catch (IOException ex) {
            Logger.getLogger(FTPHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
