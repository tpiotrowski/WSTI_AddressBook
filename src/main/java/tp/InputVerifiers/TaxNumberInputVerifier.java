package tp.InputVerifiers;

import java.awt.Color;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.UIManager;

import tp.InputVerifiers.Common.InputVerifierExtened;

public class TaxNumberInputVerifier extends InputVerifierExtened {

	private Color goodFieldColor = UIManager.getColor("TextField.background");

	public TaxNumberInputVerifier() {

	}

	@Override
	public boolean verify(JComponent input) {

		var field = ((JTextField) input);

		field.setToolTipText("");
		field.setBackground(goodFieldColor);

		var text = field.getText();
		if (text.equals("")) {
			return true;
		} else {

			text = text.replaceAll("[^\\.0123456789]", "");
			if (text.length() != 10) {
				field.setToolTipText("Input is to chort tax. Minimum 10 digits");
				field.setBackground(Color.RED);

				FireOnVerifiy(input, false);
				return false;
			}
			var weights = new int[] { 6, 5, 7, 2, 3, 4, 5, 6, 7 };

			var sum = 0;
			for (int i = 0; i < 9; i++) {
				var number = Character.getNumericValue(text.charAt(i));
				sum += number * weights[i];
			}

			var controlDigit = Character.getNumericValue(text.charAt(9));

			if (sum % 11 != controlDigit) {
				field.setToolTipText("This is not valid Tax Id number");
				field.setBackground(Color.RED);
				FireOnVerifiy(input, false);
				return false;
			}

			FireOnVerifiy(input, true);
			return true;
		}
	}
}
