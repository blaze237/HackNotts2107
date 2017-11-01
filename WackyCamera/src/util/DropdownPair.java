package util;


public class DropdownPair {

	private String label;
	private String filepath;

	public DropdownPair(String label, String filepath) {
		this.label = label;
		this.filepath = filepath;
	}

	public String getLabel() {
		return label;
	}

	public String getFilepath() {
		return filepath;
	}
}
