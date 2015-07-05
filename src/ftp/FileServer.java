package ftp;

/**
 *
 * @author Kamindu 
 * @Written ‎August ‎‎2012
 */

//import java.net.*;     
import java.io.*;     
import java.net.ServerSocket;
import java.net.Socket;
    /**
     * This is the Server Main class of the File Transfer application.
     * This will Receive the files from clients
     * 
     * TODO - no CRC check is performed so file integrity problems may occur
     */    
    public class FileServer {      
        
      public static void main (String[] args ) throws IOException
      {     
          
        Server srv= new Server();//create server instance GUI

        int bytesRead;  
        int current = 0;
        int count=0;
        int fileCount=0;//total number of files to arrive
        
        ServerSocket serverSocket = null;  
        serverSocket = new ServerSocket(5000);//open port 5000 for communications  
        
        while(true) { //run as long as files are received 
            Socket clientSocket = null;  
            clientSocket = serverSocket.accept();//acepts the connection request from client
             
            srv.setIP(clientSocket.getInetAddress().toString());//update the ui with IP of client
            
            InputStream in = clientSocket.getInputStream();  //open connection with client
              
            DataInputStream clientData = new DataInputStream(in);   
            
            fileCount=clientData.readInt();//first receieve the file count
            srv.setTotalFileCount(count);
            String fileName = clientData.readUTF();     
           
            System.out.println(fileName);
            // OutputStream output = new FileOutputStream(fileName);
            File nf=new File("Received Files/"+fileName);//saves all files under Received Files folder
            
            srv.setFileReceive(fileName);//update the UI
            
            if (!(nf.getParentFile().exists())) {
                boolean a=  nf.getParentFile().mkdirs();//fix path dependencies of files
            }
                      
            OutputStream output = new FileOutputStream(nf);
            
            long size = clientData.readLong();     
            long temp=size;
            
            byte[] buffer = new byte[1024];//buffer of 1024 bites
            
            //the actual receiving of file data
            while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1)     
            {     
                output.write(buffer, 0, bytesRead);     
                size -= bytesRead; 
            
            //    System.out.println("left "+size+"   total  = "+temp);
                current=(int)(100-((double)size/temp)*100);
            //    System.out.println("  persentage  "+current);
                srv.setTransferProcess(current);//update the percentsge of progress
            }  
            
            
              current=0;
              count++;
              
              srv.setFileCount(fileCount);//need to update when another set of files receive
              
              srv.setTotalFileCount(count);
              srv.setTransferProcess(current);
              srv.setFileTransferProcess(count);
            // Closing the FileOutputStream handle  
            output.close();  
        }  
      }
      
     /**
      * Reset server for a new session
      */ 
    
      
      
    }  