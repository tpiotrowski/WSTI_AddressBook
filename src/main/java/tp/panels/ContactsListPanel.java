package tp.panels;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JToolBar;
import javax.swing.JTable;

public class ContactsListPanel extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ContactsListPanel() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.65);
		add(splitPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Contacts", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(panel);
		panel.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		JToolBar toolBar = new JToolBar();
		panel.add(toolBar, "cell 0 0");
		
		table = new JTable();
		panel.add(table, "cell 0 1,grow");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Contact details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(panel_1);
	}
}
