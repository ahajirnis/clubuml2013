package controller.comparer.xmi;


public class XmiBaseElement {

	private static final String DEFAULT_VISIBILITY = "public";
	
	private String id;
	private String name;
	private String type;
	private String visibility;

	public XmiBaseElement(String id, String name, String type, String visibility) {
		
		this.id = id;
		this.name = name;
		this.type = type;
		setVisibility(visibility);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		
		if (visibility == null) {
			this.visibility = DEFAULT_VISIBILITY;
		} else if (visibility.isEmpty()) {
			this.visibility = DEFAULT_VISIBILITY;
		} else {
			this.visibility = visibility;
		}
	}

	public String getUmlType() {
		return type;
	}

	public void setUmlType(String umlType) {
		this.type = umlType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ID: " + this.id + ", NAME: " + this.name + ", TYPE: " + this.type + ", VISIBILITIY: " + this.visibility;
		
	}
}
