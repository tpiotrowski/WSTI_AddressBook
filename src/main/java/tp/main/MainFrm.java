package tp.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.text.IconView;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;

import com.google.gson.GsonBuilder;

import net.miginfocom.swing.MigLayout;
import tp.interfaces.IContactListEditor;
import tp.panels.ContactsListPanel;
import tp.panels.TabPanelTitle;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Dimension;

//import org.apache.commons.io;

public class MainFrm {

	private JFrame frame;
	private JTabbedPane tabbedPane;

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

	HashMap<String, PaneInfo> openedDataBasesHashMap = new HashMap<String, PaneInfo>();

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(1000, 500));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(windowListener());
		frame.setBounds(100, 100, 995, 535);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New file");

		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open file");

		mnFile.add(mntmOpen);

		JMenuItem mntmSaveFileAll = new JMenuItem("Save all");
		mntmSaveFileAll.addActionListener(s -> persistAll());
		mnFile.add(mntmSaveFileAll);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		// Action listeners

		mntmOpen.addActionListener(s -> {
			openOrCreateFile(tabbedPane, true);
		});

		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mntmNew) {
					openOrCreateFile(tabbedPane, false);
				}
			}

		});
	}

	private void openOrCreateFile(JTabbedPane tabbedPane, Boolean isOpen) {

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Address book db", "abdb");

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(filter);
		int retVal = -1;

		if (isOpen) {

			retVal = fileChooser.showOpenDialog(frame);

		} else {
			retVal = fileChooser.showSaveDialog(frame);
		}

		if (retVal == JFileChooser.APPROVE_OPTION) {

			addDbEditorTab(tabbedPane, fileChooser);
		}
	}

	private File addFileExtensionIfNotExists(File file, String extension) {

		if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase(extension)) {
			// filename is OK as-is
		} else {
			file = new File(file.toString() + "." + extension); // append .xml if "foo.jpg.xml" is OK
			file = new File(file.getParentFile(), FilenameUtils.getBaseName(file.getName()) + "." + extension);
		}

		return file;
	}

	
	Boolean lastIsDirtyBoolean = false;
	private void addDbEditorTab(JTabbedPane tabbedPane, JFileChooser fileChooser) {
		File selectedFile = fileChooser.getSelectedFile();

		selectedFile = addFileExtensionIfNotExists(selectedFile, "abdb");

		String dbFilePath = selectedFile.getAbsolutePath();

		if (!openedDataBasesHashMap.containsKey(dbFilePath)) {

			var dbEditor = new ContactsListPanel();
			dbEditor.setDirtyChangedEventListener((isDirty, source) -> {

				var index = -1;

				if (openedDataBasesHashMap.containsKey(source)) {
					var paneInfo = openedDataBasesHashMap.get(source);
					index = paneInfo.tabIndex;
				}

				if (index == -1)
					return;

				var titleComponent = (TabPanelTitle) tabbedPane.getTabComponentAt(index);

				if (isDirty && isDirty != lastIsDirtyBoolean) 
				{
					var title = titleComponent.getTitle();

					title = title + " *";

					titleComponent.setTitleLabel(title);
				}
				
				if(!isDirty && isDirty != lastIsDirtyBoolean){
					var title = titleComponent.getTitle();

					title = title.replace(" *", "");

					titleComponent.setTitleLabel(title);
				}
				
				lastIsDirtyBoolean = isDirty;

			});

			dbEditor.setSource(dbFilePath, !selectedFile.exists());

			tabbedPane.add(dbFilePath, dbEditor);

			tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(dbEditor),
					getTitlePanel(tabbedPane, dbEditor, dbFilePath));

			var tabIndex = tabbedPane.getTabCount() - 1;

			var paneInfo = new PaneInfo();
			paneInfo.editor = dbEditor;
			paneInfo.tabIndex = tabIndex;

			openedDataBasesHashMap.put(dbFilePath, paneInfo);

		}

	}

	void persistAll() {
		for (PaneInfo paneInfo : openedDataBasesHashMap.values()) {
			if (paneInfo.editor.isDirty()) {
				try {
					paneInfo.editor.persist();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	Boolean hasUnsavedChanges() {
		for (PaneInfo paneInfo : openedDataBasesHashMap.values()) {
			if (paneInfo.editor.isDirty()) {
				return true;
			}
		}
		return false;
	}

	private WindowAdapter windowListener() {
		return new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				if (hasUnsavedChanges()) {
					if (JOptionPane.showConfirmDialog(frame,
							"You have unsaved changes! Are you sure you want to close this window?", "Close Window?",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
				} else {
					System.exit(0);
				}

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		};
	}

	class PaneInfo {
		public int tabIndex;
		public IContactListEditor editor;
	}

	private JPanel getTitlePanel(final JTabbedPane tabbedPane, final ContactsListPanel panel, String title) {
		TabPanelTitle titlePanel = new TabPanelTitle();
		titlePanel.setTitleLabel(title);
		titlePanel.setCloseButtonEventListener(l -> {

			var index = tabbedPane.indexOfComponent(panel);

			String dirtyKey = isEditorDirtyByIndex(index);

			if (dirtyKey != null && !dirtyKey.isEmpty()) {

				if (JOptionPane.showConfirmDialog(frame,
						"You have unsaved changes! Are you sure you want to close this editor?",
						"Close database editor?", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

					tabbedPane.remove(panel);
					openedDataBasesHashMap.remove(dirtyKey);

				}
			} else {
				tabbedPane.remove(panel);
				openedDataBasesHashMap.remove(panel.getSource());
			}

		});

		return titlePanel;
	}

	String isEditorDirtyByIndex(int index) {
		for (var entry : openedDataBasesHashMap.entrySet()) {

			var key = entry.getKey();
			var value = entry.getValue();
			if (value.tabIndex == index && value.editor.isDirty())
				return key;
		}

		return null;
	}

}
