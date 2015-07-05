package ftpTransfer;

/**
 *
 * @author Kamindu
 * @Written ‎August ‎‎2012
 * @required Settings.java
 */
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.swing.ProgressMonitorInputStream;

//import java.net.*;        
public class FileClient {

    private static final Logger log = Logger.getLogger(FileClient.class.getName());

    private Socket sock;//socket that engages the connection
    private Settings set;//settings instance 
    private int fileCount = 0;
    /*       
     public static void main(String[] args) throws IOException {  
      
     FileClient client=new FileClient();
            
     client.sendFile("C:/Users/LaptopUser/Documents/Book Place/Downloads/Austen, Jane - Pride and Prejudice.xps");
     client.connectFTP();
     client.sendFile("C:/Users/LaptopUser/Documents/Book Place/Downloads/Collins, William Wilkie - Armadale.epub");
            
            
     }
     */

    public FileClient() {
        // connectFTP();
        set = new Settings();//create a sttings instance for getting connection settings

    }

    public void connectFTP() {
        try {

            //Socket sock = new Socket("127.0.0.1", 13267);  
            // sock = new Socket("127.0.0.1", 5000);
            //   sock = new Socket("192.168.1.2", 5000);
            sock = new Socket(set.getIPaddress(), set.getPort());

        } catch (UnknownHostException ex) {
            //System.out.println("Error Unknown host "+ex);
            log.log(Level.SEVERE, "Error Unknown host", ex);
        } catch (IOException ex) {
            //System.out.println("Error IOException "+ex);
            log.log(Level.SEVERE, "Error IOException", ex);
        }
    }

    //reads the file form disk and transfer.
    public void sendFile(String filepath) throws IOException {
              //Send file  
        // File myFile = new File("testing.zip");  //file

        File myFile = new File(filepath);  //file

        byte[] mybytearray = new byte[(int) myFile.length()];//load the entire file as bite arrary  

        FileInputStream fis = new FileInputStream(myFile);

        BufferedInputStream bis = new BufferedInputStream(fis);

              //BufferedInputStream bis = new BufferedInputStream(new ProgressMonitorInputStream(null, "Transfering "+myFile,fis));  
            //bis.read(mybytearray, 0, mybytearray.length);  
        DataInputStream dis = new DataInputStream(bis);
        dis.readFully(mybytearray, 0, mybytearray.length);

        OutputStream os = sock.getOutputStream();

        //Sending file name and file size to the server  
        DataOutputStream dos = new DataOutputStream(os);

        dos.writeInt(fileCount);//first send the file count

        dos.writeUTF(myFile.getParentFile().getName() + "/" + myFile.getName());//send the file name

        log.log(Level.INFO, "{0}/{1}", new String[]{myFile.getParentFile().getName(), myFile.getName()});
            //System.out.println();
        // dos.writeBytes(myFile.getParentFile().getName());

        log.log(Level.INFO, "Size {0}", mybytearray.length);
            //System.out.println("Size "+mybytearray.length);

        dos.writeLong(mybytearray.length);
        dos.write(mybytearray, 0, mybytearray.length);
        dos.flush();

            //Sending file data to the server  
        //        os.write(mybytearray, 0, mybytearray.length);  
        //        os.flush();  
        //Closing socket  
        sock.close();
    }

    /**
     * the file transfer is complete now we can disconnect
     */
    void disconnectFTP() {
        //TODO : so far it is done manually
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

}
