package ftp;

/**
 *
 * @author Kamindu 
 * @Written ‎August ‎‎2012
 */



import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.TitledBorder;


public class Server extends JFrame {

   private JLabel lblIp;//shows client ip
  
   private DefaultListModel list;
   private JProgressBar proFTransfer;//shows the progress of file transfer max set to 100 and value updated using setTransferProcess(int)
   private JProgressBar progTotFiles;//shows the file count
   
   private JLabel lblNo;//total file count display used along with lblNo_1
   private JLabel lblNo_1;//files that have received => output in format (xx of)
   
   private JLabel lblClient;//displays whos ip is showing
   /**
    * Launch the application.
    * Core Code of the server and GUI
    * 
    * TODO- implement Transfer button and Reset buttons (btnTransfer,btnReset )
    */
   
    /**
     * Launch the application.Core Code of the server and GUI
 
 TODO- implement Transfer button and Reset buttons (btnTransfer,btnReset )
     * 
     */
   
   /**
    * 
    * @param clientIP - ip of the client that is connected called from FileServer class
    */
    public void setIP(String clientIP)
        {
          lblIp.setText(clientIP); 
          lblClient.setText("Client");
        }
    
    /**
     * 
     * @param serverIP - set the server ip and label, called from FileServer class
     */
    public void setSIP(String serverIP)
        {
          lblIp.setText(serverIP); 
          lblClient.setText("Server");
        }  
        
        public void setFileCount(int num)
        {
            lblNo.setText(String.valueOf(num));
            progTotFiles.setMaximum(num);
        }        
        
        public void setFileReceive(String receiveFile)
        {
            list.addElement(receiveFile);
           
        }
        
        public void setTransferProcess(int val)
        {
            if (val>=0 && val <=100) {
                proFTransfer.setValue(val);
            }
 
           
        }        
        
        
    void setTotalFileCount(int count) {
        lblNo.setText(String.valueOf(count));
        
    }

    void setFileTransferProcess(int count) {
        lblNo_1.setText(String.valueOf(count) +" of ");
        
        progTotFiles.setValue(count);
    }
        
	/**
	 * Create the frame.
	 */
	public Server() {
              list =new DefaultListModel();
              
		setTitle("FTP Server");
		setBounds(100, 100, 547, 353);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblRecevingFiles = new JLabel("Receving files");
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
				
				
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Progress", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//no action is performed
				
				
			}
		});
                //-------------
                btnTransfer.setEnabled(false);
                //--------
		btnTransfer.setToolTipText("Transfer the selected Files to...");
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//reset the form
                                //noaction performed
				
			}
		});
                //-------------
                btnReset.setEnabled(false);
                //--------
                
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRecevingFiles)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(10)
										.addComponent(panel, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
									.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnExit)
										.addGap(32)))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnTransfer)
									.addGap(19))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(btnReset, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)))))
					.addGap(128))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRecevingFiles)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnReset)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnTransfer)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExit)
							.addGap(20))))
		);
		
		proFTransfer = new JProgressBar();
		proFTransfer.setMaximum(100);
                proFTransfer.setMinimum(0);
		JLabel lblFileTransfer = new JLabel("File Transfer");
		
		JLabel lblTotalFiles = new JLabel("Total Files");
		
		 progTotFiles = new JProgressBar();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFileTransfer)
						.addComponent(lblTotalFiles))
					.addGap(29)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(progTotFiles, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
						.addComponent(proFTransfer, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFileTransfer)
						.addComponent(proFTransfer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTotalFiles)
						.addComponent(progTotFiles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		lblClient = new JLabel("Server");//initially shows the Server Ip - will change to client when client ip is set
		
		lblIp = new JLabel("IP");
		
		JLabel lblFilesSent = new JLabel("Files Received");
		
		lblNo = new JLabel("No");
		
		 lblNo_1 = new JLabel("No1");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIp, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
								.addComponent(lblClient)
								.addComponent(lblFilesSent)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(28)
							.addComponent(lblNo_1)
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addComponent(lblNo, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblClient)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblIp)
					.addGap(29)
					.addComponent(lblFilesSent)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNo)
						.addComponent(lblNo_1))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JList lstFiles = new JList();
                lstFiles.setModel(list);
		scrollPane.setViewportView(lstFiles);
		getContentPane().setLayout(groupLayout);

                
                setVisible(true);
	}

    
    
}
