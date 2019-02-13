package tp.panels;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;

public class TabPanelTitle extends JPanel {

	JButton btnClose;
	JLabel titleLabel;
	
	/**
	 * Create the panel.
	 */
	public TabPanelTitle() {
		setOpaque(false);
		setMaximumSize(new Dimension(32767, 17));
		setFocusTraversalKeysEnabled(false);
		setFocusable(false);
		setBorder(null);
		setLayout(new MigLayout("", "[][]", "[]"));
		
		titleLabel = new JLabel("New label");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(titleLabel, "cell 0 0,growx");
		
		btnClose = new JButton("x");
		btnClose.setBorder(null);
		btnClose.setVerticalAlignment(SwingConstants.TOP);
		btnClose.setBorderPainted(false);
		btnClose.setFocusPainted(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setOpaque(false);
		
		add(btnClose, "cell 1 0,alignx right");

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
