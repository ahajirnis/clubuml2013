package controller.comparer.xmi;

public class XmiParameterElement extends XmiBaseElement{

	private static final String DIRECTION_DEFAULT = "in";
	
	private String direction;
	
	public XmiParameterElement(String id, String name, String type,
			String visibility, String direction) {
		super(id, name, type, visibility);
		
		if (direction == null) {
			this.setDirection(DIRECTION_DEFAULT);
		} else if (direction.isEmpty()) {
			this.setDirection(DIRECTION_DEFAULT);
		}
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	@Override
	public String toString() {
		return "Parameter: " + super.toString();
	}

}
