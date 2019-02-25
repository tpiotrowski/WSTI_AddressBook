package tp.InputVerifiers.Common;

import java.util.ArrayList;

import javax.swing.InputVerifier;
import javax.swing.JComponent;

public abstract class InputVerifierExtened extends InputVerifier {

	private ArrayList<InputVerifierListener> listeners = new ArrayList<InputVerifierListener>();
	private ArrayList<InputVerifier> verifiers = new ArrayList<InputVerifier>(); 
	
	public void addVerifier(InputVerifier verifier) {
		if(verifiers.contains(verifier))
			return;
		
		verifiers.add(verifier);
	}
	
	public void removeVerifier(InputVerifier verifier) {
		if(verifiers.contains(verifier))
			return;
		
		verifiers.remove(verifier);
	}
	
	public void addInputVerifierListener(InputVerifierListener listener) {
		if(listeners.contains(listener))
			return;
		
		listeners.add(listener);
	}
	
	public void removeInputVerifierListener(InputVerifierListener listener) {
		if(listeners.contains(listener))
			listeners.remove(listener);
	}
	
	protected void FireOnVerifiy(JComponent component, Boolean isValid) {
		for (InputVerifierListener inputVerifierListener : listeners) {
			inputVerifierListener.onVerify(component, isValid);
		}
	}
	
	@Override
	public boolean verify(JComponent input) {
		Boolean result = true;
	
		for (InputVerifier inputVerifier : verifiers) {
			var currentResult = inputVerifier.verify(input);
			result = result && currentResult;
			FireOnVerifiy(input, result);
		}
		
		return result;
	}
	
	
}