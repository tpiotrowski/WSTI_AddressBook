package tp.InputVerifiers.Common;

import javax.swing.JComponent;

public interface InputVerifierListener {

	void onVerify(JComponent component, Boolean isValid);

}
