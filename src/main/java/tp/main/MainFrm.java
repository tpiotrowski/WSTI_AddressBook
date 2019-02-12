package tp.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.text.IconView;

import com.google.gson.GsonBuilder;

import net.miginfocom.swing.MigLayout;
import tp.panels.ContactsListPanel;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class MainFrm {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm window = new MainFrm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrm() {
		initialize();

	}

	HashMap<String, Component> openedDataBasesHashMap = new HashMap<String, Component>();

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(windowListener());
		frame.setBounds(100, 100, 620, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New file");

		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open file");
		mnFile.add(mntmOpen);

		JMenuItem mntmSaveFile = new JMenuItem("Save File");
		mnFile.add(mntmSaveFile);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNew) {
					JFileChooser fileChooser = new JFileChooser();
					int retVal = fileChooser.showOpenDialog(frame);
					if (retVal == JFileChooser.APPROVE_OPTION) {

						addDbEditorTab(tabbedPane, fileChooser);
					}
				}
			}
		});
	}

	
	
	private void addDbEditorTab(JTabbedPane tabbedPane, JFileChooser fileChooser) {
		File selectedFile = fileChooser.getSelectedFile();

		String dbFilePath = selectedFile.getAbsolutePath();

		if (!openedDataBasesHashMap.containsKey(dbFilePath)) {

			var dbEditor = new ContactsListPanel();

			dbEditor.setSource(dbFilePath, !selectedFile.exists());

			openedDataBasesHashMap.put(dbFilePath, dbEditor);

			tabbedPane.addTab(dbFilePath, dbEditor);
		}

	}

	private WindowAdapter windowListener() {
		return new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(frame, 
			            "Are you sure you want to close this window?", "Close Window?", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
			            System.exit(0);
			        }
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		};
	}

}
