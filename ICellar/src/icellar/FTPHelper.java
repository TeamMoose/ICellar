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
    
    /**
     * 
     */
    public FTPHelper()
    {
        client = new FTPClient();
    }
    
    /**
     * Gets the InputStream from the correct file on the ftp server
     * @param filepath
     * @return  the InputStream to the file on the ftp server.
     */
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
    
    /**
     * Gets a BufferedWriter to the file on the ftp server
     * @param filepath
     * @return a BufferedWriter to the file on the ftp server
     */
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
    
    /**
     * Appends str to the end of the file at filepath on the ftp server.
     * @param filepath
     * @param str
     */
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
    
    /**
     * Loads a cellar from a file on the ftp server
     * @param filepath
     * @return a Cellar that is populated with the file on the ftp server.
     */
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
    
    /**
     * Writes the contents of a cellar to a file on the ftp server
     * @param filepath
     * @param myCellar
     */
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
}
