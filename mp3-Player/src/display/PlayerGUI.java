package display;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import javazoom.jl.player.Player;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;

public class PlayerGUI {

	private JFrame frame;
	private JTextArea pathField;

	private File songFile;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					
					PlayerGUI window = new PlayerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public PlayerGUI() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("AFzalMP3Player");
		frame.setBounds(100, 100, 291, 131);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JButton startBtn = new JButton("START");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Play Audio
				try {
					Player p=new Player(new FileInputStream(songFile));
					p.play();
				}catch(Exception ee) {
					JOptionPane.showMessageDialog(null, "NO file selected", "Error", JOptionPane.ERROR_MESSAGE);
					ee.printStackTrace();
				}				
			}
		});
		startBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		startBtn.setBounds(10, 44, 255, 43);
		frame.getContentPane().add(startBtn);
		
		pathField = new JTextArea();
		pathField.setForeground(Color.DARK_GRAY);
		pathField.setEditable(false);
		pathField.setText("Song Path");
		pathField.setBounds(10, 11, 184, 22);
		frame.getContentPane().add(pathField);
		
		JButton openBtn = new JButton("Open");
		openBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//OPen FIle
				open();
			}
		});
		openBtn.setBounds(204, 12, 61, 23);
		frame.getContentPane().add(openBtn);
	}
	
	private void open() {
		try {
			JFileChooser chooser=new JFileChooser();
			chooser.setDialogTitle("Choose Song To Load...");
			chooser.showOpenDialog(null);
			songFile=chooser.getSelectedFile();
			pathField.setText(songFile.getAbsolutePath());
			
			if(!songFile.getName().endsWith(".mp3")) {
				JOptionPane.showMessageDialog(null, "invalid file type selected", "error", JOptionPane.ERROR_MESSAGE);
				open();
			}
		}catch(Exception ee) {
			ee.printStackTrace();
		}
	}
}
