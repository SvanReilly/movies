package com.aommr.movies;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2019510724532679595L;
	private JFrame frame;
    private JTextField hostnameField;
    private JTextField socketField;
    private JTextField nameField;
    private JTextField yearField;
    private JTextField lengthField;
    private JTextField calificationField;

    private Socket socket;
    private ObjectOutputStream outputStream;

    public Client() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("CLIENT WINDOW");
        frame.setBounds(100, 100, 370, 342);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblHostname = new JLabel("Hostname:");
        lblHostname.setBounds(20, 20, 100, 20);
        frame.getContentPane().add(lblHostname);

        hostnameField = new JTextField();
        hostnameField.setText("10.192.104.93");
        hostnameField.setBounds(146, 20, 150, 20);
        frame.getContentPane().add(hostnameField);
        hostnameField.setColumns(10);

        JLabel lblSocket = new JLabel("Port:");
        lblSocket.setBounds(20, 50, 100, 20);
        frame.getContentPane().add(lblSocket);

        socketField = new JTextField();
        socketField.setText("22222");
        socketField.setBounds(146, 50, 150, 20);
        frame.getContentPane().add(socketField);
        socketField.setColumns(10);

        JLabel lblMovieName = new JLabel("Name:");
        lblMovieName.setBounds(20, 80, 100, 20);
        frame.getContentPane().add(lblMovieName);

        nameField = new JTextField();
        nameField.setBounds(146, 80, 150, 20);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);

        JLabel lblYear = new JLabel("Year:");
        lblYear.setBounds(20, 110, 100, 20);
        frame.getContentPane().add(lblYear);

        yearField = new JTextField();
        yearField.setBounds(146, 110, 150, 20);
        frame.getContentPane().add(yearField);
        yearField.setColumns(10);

        JLabel lblMovieLenght = new JLabel("Length (in minutes):");
        lblMovieLenght.setBounds(20, 140, 155, 20);
        frame.getContentPane().add(lblMovieLenght);

        lengthField = new JTextField();
        lengthField.setBounds(146, 140, 150, 20);
        frame.getContentPane().add(lengthField);
        lengthField.setColumns(10);

        JLabel lblCalification = new JLabel("Calification (1-10):");
        lblCalification.setBounds(20, 170, 116, 20);
        frame.getContentPane().add(lblCalification);

        calificationField = new JTextField();
        calificationField.setBounds(146, 170, 150, 20);
        frame.getContentPane().add(calificationField);
        calificationField.setColumns(10);

               JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
            	sendMovie();
                } catch (NumberFormatException nf) {
                    JOptionPane.showMessageDialog(frame, "An error ocurred while trying to send a movie.");
                }
            }
        });
        sendButton.setBounds(145, 228, 100, 30);
        frame.getContentPane().add(sendButton);

        frame.setVisible(true);
    }

    private void connect() {
        try {
            String hostname = hostnameField.getText();
            int port = Integer.parseInt(socketField.getText());
            socket = new Socket(hostname, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error ocurred while trying to connect to the server.");
        }
    }

    private void sendMovie() {
    	connect();
    	Movie movie = new Movie("@1994@Default@2004@", 0, 0, 0);
    	try {
            if (socket == null || outputStream == null) {
                JOptionPane.showMessageDialog(frame, "Should start the server first, isn't?");
            }
            
            String nameVar = nameField.getText();
            int releaseYearVar = Integer.parseInt(yearField.getText());
            int lengthVar = Integer.parseInt(lengthField.getText());
            int calificationVar = Integer.parseInt(calificationField.getText());

            if (releaseYearVar < 1888) {
            	JOptionPane.showMessageDialog(frame, "Invalid value for year.");
                outputStream.writeObject(movie);
			} else if (lengthVar < 50 || lengthVar > 5100) {
				JOptionPane.showMessageDialog(frame, "Invalid value movie length.");
	            outputStream.writeObject(movie);
			} else if (calificationVar < 0 || calificationVar > 10) {
				JOptionPane.showMessageDialog(frame, "Invalid value for calification.");
	            outputStream.writeObject(movie);
			} else {
	            movie = new Movie(nameVar, releaseYearVar, lengthVar, calificationVar);
	            outputStream.writeObject(movie);
	            JOptionPane.showMessageDialog(frame, "Movie succesfully sent.");
			}

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid format for year, length or calification.");
				try {
					outputStream.writeObject(movie);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated catch block
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occured when sending that movie.");
        }
        
        
    	}

    public static void main(String[] args) {
        new Client();
    }
}
