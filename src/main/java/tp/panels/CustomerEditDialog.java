package tp.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import tp.model.Person;
import java.awt.Dimension;
import java.awt.Window.Type;

public class CustomerEditDialog extends JDialog implements ActionListener{
	JButton okButton;
	/**
	 * Create the dialog.
	 */
	public CustomerEditDialog() {
		super();	
		setResizable(false);
		setType(Type.POPUP);
		initializeComponent();
	}

	private void initializeComponent() {
		setBounds(100, 100, 573, 371);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		{
			panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				contactDetails = new ContactDetails();
				panel.add(contactDetails, BorderLayout.CENTER);
			}
		}
	}

	int returnValue = -1;
	private JButton cancelButton;
	private JPanel panel;
	private ContactDetails contactDetails;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okButton) {
			returnValue = JOptionPane.OK_OPTION;
		}
		if(e.getSource() == cancelButton) {
			returnValue = JOptionPane.CANCEL_OPTION;
		}
		dispose();
	}

	public int showDialog() {
		this.setTitle("Ala ma kota");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		
		this.setVisible(true);
		return returnValue;
	}
	
	public void setData(Person person) {
		contactDetails.setData(person);
	}

	public void bind(Person person) {
		contactDetails.bind(person);
	}

	public Person getData() {
		return contactDetails.getData();
	}

}
