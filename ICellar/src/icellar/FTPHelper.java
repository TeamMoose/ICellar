package icellar;

import org.apache.commons.net.ftp.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex Mazzeo
 */
public class FTPHelper 
{
    private static FTPClient client;
    private static final String ftpUser = "iCellar";
    private static final String ftpPass = "1cellar!";
    private static final String ftpSite = "ftp.reverteddesigns.com";
    private static final String baseFP = "/wineData/";
    
    public FTPHelper()
    {
        client = new FTPClient();
    }
    
    public static InputStream getFTPInputStream( String filepath )
    {
        client = new FTPClient();
        try {
            int reply;
            client.connect(ftpSite);

            // After connection attempt, you should check the reply code to verify
            // success.
            reply = client.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply))
            {
                client.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }
            client.login(ftpUser, ftpPass, "reverteddesignscom");
            
            return client.retrieveFileStream(filepath);
        } catch (UnknownHostException ex)
        {
            System.out.println(ex);
        }
            catch (SocketException ex) {
            Logger.getLogger(FTPHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FTPHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private static BufferedWriter getFTPBufferedWriter( String filepath )
     {
        client = new FTPClient();
        try {
            int reply;
            client.connect(ftpSite);

            // After connection attempt, you should check the reply code to verify
            // success.
            reply = client.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply))
            {
                client.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }
            client.login(ftpUser, ftpPass, "reverteddesignscom");
            return new BufferedWriter ( new OutputStreamWriter ( client.storeFileStream( filepath) ) );
        } catch (UnknownHostException ex)
        {
            System.out.println(ex);
        }
            catch (SocketException ex) {
            Logger.getLogger(FTPHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FTPHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void appendToFile( String filepath, String str )
    {
        String append = "";
        InputStream is = getFTPInputStream( baseFP + filepath );
        BufferedWriter bw;
        Scanner scan = new Scanner( is );
        
        while ( scan.hasNext() )
        {
            append += scan.nextLine() + "\n";
        }
        
        append += str;
        try {
            is.close();
            bw = getFTPBufferedWriter( baseFP + filepath );
            bw.write(append);
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(FTPHelper.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public static Cellar readCellarFromFile( String filepath )
    {
        InputStream is = getFTPInputStream( baseFP + filepath );
        Cellar result = new Cellar( is );
        try {
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(FTPHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
        
    }
    
    public static void writeCellarToFile( String filepath, Cellar myCellar )
    {
        BufferedWriter bw = getFTPBufferedWriter( baseFP + filepath );
        try {
            bw.write(myCellar.toString());
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(FTPHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static InputStream getAndroidInputStream( String filepath )
    {
        try {
            URL url = new URL("ftp://"+ftpUser+":"+ftpPass+"@"+ftpSite+baseFP+filepath+";type=i");
            URLConnection urlc = url.openConnection();
            return urlc.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(FTPHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
