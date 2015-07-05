package ftpTransfer;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kamindu
 * @Written ‎August ‎‎2012
 */
public class Settings {

    private static final Logger log = Logger.getLogger(Settings.class.getName());

    private String IPaddress = "127.0.0.1";//default ip
    private int port = 5000;//default port

    private File file;

    public Settings() {
        file = new File("Settings.txt"); //read settings from file.

        if (file.exists()) {
            readSettings();

        } else {
            try {
                //what to do when file doesn't exists
                //creat a new file with default settings. 
                PrintWriter priWri = new PrintWriter(new BufferedWriter(new FileWriter(file)));

                priWri.println(IPaddress);
                priWri.println(String.valueOf(port));

                priWri.flush();
            } catch (IOException ex) {

                log.log(Level.SEVERE, "File writer error", ex);
            }

        }
    }

    /**
     * This will read the settings from a text file. if an error occur the
     * default settings will be loaded.
     */
    private void readSettings() {

        try {

            BufferedReader bufReader = new BufferedReader(new FileReader(file));

            if (bufReader.ready()) {
                setIPaddress(bufReader.readLine());
                System.out.println("read ip " + getIPaddress());
                setPort(bufReader.readLine());
                System.out.println("Read port " + getPort());
            } else {
                System.out.println("Default Settings loaded...");
            }

        } catch (FileNotFoundException ex) {
            //System.err.println("Error reading " + ex);
            log.log(Level.SEVERE, "Error reading Settings file: file not exist", ex);
        } catch (IOException exception) {
            log.log(Level.SEVERE, "Error IO ", exception);
            //System.err.println("Error IO " + exception);
        }

    }

    public void setIPaddress(String IPaddress) {

        this.IPaddress = IPaddress.trim();//remove white space noices to get only ip
    }

    public String getIPaddress() {
        return IPaddress;
    }

    //file is read and send the port as a string
    public void setPort(String port) {
        try {
            setPort(Integer.valueOf(port));
        } catch (NumberFormatException nfex) {//if in a case of non integer input
            log.log(Level.SEVERE, "Error reading port from settings file ", nfex);
        }
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}
