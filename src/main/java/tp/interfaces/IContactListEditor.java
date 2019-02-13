package tp.interfaces;

import tp.panels.IDirtyChangedEventListener;

public interface IContactListEditor {
	void persist() throws Exception;
	void setSource(String source, Boolean isNew);
	Boolean isDirty();
	void setDirtyChangedEventListener(IDirtyChangedEventListener e);
	String getSource();
}
