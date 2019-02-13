package tp.panels;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class TabPanelTitle extends JPanel {

	JButton btnClose;
	JLabel titleLabel;
	
	/**
	 * Create the panel.
	 */
	public TabPanelTitle() {
		setLayout(new MigLayout("", "[][]", "[]"));
		
		titleLabel = new JLabel("New label");
		add(titleLabel, "cell 0 0");
		
		btnClose = new JButton("x");
		btnClose.setVerticalAlignment(SwingConstants.TOP);
		btnClose.setBorderPainted(false);
		btnClose.setFocusPainted(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setOpaque(false);
		
		add(btnClose, "cell 1 0");
		this.setOpaque(false);

	}


	public void setCloseButtonEventListener(ActionListener l)
	{
		btnClose.addActionListener(l);
	}
	
	public String getTitle() {
		return this.titleLabel.getText();
	}

	public void setTitleLabel(String titleLabel) {
		this.titleLabel.setText(titleLabel);
	}
	
	

}
