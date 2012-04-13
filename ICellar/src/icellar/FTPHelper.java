package icellar;

import org.apache.commons.net.ftp.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex Mazzeo
 */
public class FTPHelper 
{
    public static FTPClient client;
    public static final String urlProtocol = "ftp://";
    public static final String ftpUser = "iCellar";
    public static final String ftpPass = "1cellar!";
    public static final String ftpSite = "ftp.reverteddesigns.com";
    public static final String ftpEnd = ";type=i";
    public static final String baseFilepath = "/iCellar/";
    
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
            System.out.println("Connected to " + ftpSite + ".");

            // After connection attempt, you should check the reply code to verify
            // success.
            reply = client.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply))
            {
                client.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }
            Boolean logged = client.login(ftpUser, ftpPass, "reverteddesignscom");
            reply = client.getReplyCode();
            client.changeToParentDirectory();
            FTPFile[] directs = client.listDirectories();
            String[] names = client.listNames();
            for ( String str : names )
            {
                System.out.println(str);
            }
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
    
    public static BufferedWriter getFTPOutputStream( String filepath )
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
            return new BufferedWriter ( new OutputStreamWriter ( client.storeFileStream(filepath) ) );
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
}
