package controller.comparer.xmi;

import java.util.ArrayList;

public class XmiAssociationElement extends XmiBaseElement {

	private final static int MEMBER_END_SIZE = 2;
	private String navigableOwnedEnd = "";
	private ArrayList<XmiMemberEndElement> memberEnd = new ArrayList<XmiMemberEndElement>(MEMBER_END_SIZE);
	
	public XmiAssociationElement(String id, String name, String type) {
		super(id, name, type, "");
	}

	public void addMemberEnd(XmiMemberEndElement element) {
		// if at maximum size, remove first element
		if (memberEnd.size() == MEMBER_END_SIZE) {
			memberEnd.remove(0);
		}
		
		// add element at the end
		memberEnd.add(element);
	}
	
	public ArrayList<XmiMemberEndElement> getMemberEnds() {
		return memberEnd;
	}

	/**
	 * @return the navigableOwnedEnd
	 */
	public String getNavigable() {
		return navigableOwnedEnd;
	}

	/**
	 * @param navigable the navigableOwnedEnd to set
	 */
	public void setNavigable(String navigable) {
		this.navigableOwnedEnd = navigable;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((memberEnd == null) ? 0 : memberEnd.hashCode());
		result = prime
				* result
				+ ((navigableOwnedEnd == null) ? 0 : navigableOwnedEnd
						.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof XmiAssociationElement)) {
			return false;
		}
		XmiAssociationElement other = (XmiAssociationElement) obj;
		if (memberEnd == null) {
			if (other.memberEnd != null) {
				return false;
			}
		} else if (!memberEnd.equals(other.memberEnd)) {
			return false;
		}
		if (navigableOwnedEnd == null) {
			if (other.navigableOwnedEnd != null) {
				return false;
			}
		} else if (!navigableOwnedEnd.equals(other.navigableOwnedEnd)) {
			return false;
		}
		return true;
	}
}
