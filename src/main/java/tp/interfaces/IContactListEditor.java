package tp.interfaces;

public interface IContactListEditor {
	void persist() throws Exception;
	void setSource(String source, Boolean isNew);
	Boolean isDirty();
}
