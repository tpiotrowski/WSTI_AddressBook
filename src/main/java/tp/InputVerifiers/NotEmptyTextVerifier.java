package tp.InputVerifiers;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.UIManager;

import tp.InputVerifiers.Common.InputVerifierExtened;

public class NotEmptyTextVerifier extends InputVerifierExtened {

	private Color goodFieldColor = UIManager.getColor("TextField.background");

	@Override
	public boolean verify(JComponent input) {

		var field = ((JTextField) input);

		field.setToolTipText("");
		field.setBackground(goodFieldColor);

		var text = field.getText();
		if (text.equals("")) {
			input.setToolTipText("Field can't be empty.");
			input.setBackground(Color.RED);
			FireOnVerifiy(input, false);
			return false;
		}
		FireOnVerifiy(input, true);
		return true;
	}

}
