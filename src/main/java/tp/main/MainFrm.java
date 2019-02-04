package tp.main;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JToolBar;
import javax.swing.JTable;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MainFrm {

	private JFrame frame;
	private JTable table;

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
		
		GsonBuilder builder = new  GsonBuilder();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 620, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.65);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(panel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "People list", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(panel);
		panel.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		JToolBar toolBar = new JToolBar();
		panel.add(toolBar, "cell 0 0,growx");
		
		JButton btnNewButton = new JButton("Add");
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Edit");
		toolBar.add(btnNewButton_2);
		
		table = new JTable();
		panel.add(table, "cell 0 1,grow");
	}

}
