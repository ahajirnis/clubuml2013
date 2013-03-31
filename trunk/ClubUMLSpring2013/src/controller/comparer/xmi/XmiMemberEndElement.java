package controller.comparer.xmi;

public class XmiMemberEndElement extends XmiBaseElement {

	private AggregationValues aggregation; 
	private XmiValueElement lowerValue;
	private XmiValueElement upperValue; 
	private XmiAssociationElement association;
	private String associationId = "";
	
	public XmiMemberEndElement(String id, String name, String type,
			XmiAssociationElement association, AggregationValues aggregation) {

		this(id, name, type, association);
		
		this.aggregation = aggregation;
	}
	
	public XmiMemberEndElement(String id, String name, String type,
			AggregationValues aggregation) {

		this(id, name, type);
		
		this.aggregation = aggregation;
	}
	
	public XmiMemberEndElement(String id, String name, String type, XmiAssociationElement association) {

		this(id, name, type);
		
		this.association = association;
	}
	
	public XmiMemberEndElement(String id, String name, String type) {
		super(id, name, type, "");
	}

	public XmiAssociationElement getAssociation() {
		return association;
	}

	public void setAssociation(XmiAssociationElement association) {
		this.association = association;
	}

	/**
	 * @return the lowerValue
	 */
	public XmiValueElement getLowerValue() {
		return lowerValue;
	}

	/**
	 * @param lowerValue the lowerValue to set
	 */
	public void setLowerValue(XmiValueElement lowerValue) {
		this.lowerValue = lowerValue;
	}

	/**
	 * @return the upperValue
	 */
	public XmiValueElement getUpperValue() {
		return upperValue;
	}

	/**
	 * @param upperValue the upperValue to set
	 */
	public void setUpperValue(XmiValueElement upperValue) {
		this.upperValue = upperValue;
	}

	/**
	 * @return the aggregation
	 */
	public AggregationValues getAggregation() {
		return aggregation;
	}

	/**
	 * @param aggregation the aggregation to set
	 */
	public void setAggregation(AggregationValues aggregation) {
		this.aggregation = aggregation;
	}

	public String getAssociationId() {
		if (association != null) {
			return this.association.getId();
		} else if (associationId != null) {
			return associationId;
		}
		
		return "";
	}
	
	public void setAssociationId(String associationId) {
		this.associationId = associationId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((aggregation == null) ? 0 : aggregation.hashCode());
		result = prime * result
				+ ((association == null) ? 0 : association.hashCode());
		result = prime * result
				+ ((associationId == null) ? 0 : associationId.hashCode());
		result = prime * result
				+ ((lowerValue == null) ? 0 : lowerValue.hashCode());
		result = prime * result
				+ ((upperValue == null) ? 0 : upperValue.hashCode());
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
		if (!(obj instanceof XmiMemberEndElement)) {
			return false;
		}
		XmiMemberEndElement other = (XmiMemberEndElement) obj;
		if (aggregation != other.aggregation) {
			return false;
		}
		if (association == null) {
			if (other.association != null) {
				return false;
			}
		} else if (!association.equals(other.association)) {
			return false;
		}
		if (associationId == null) {
			if (other.associationId != null) {
				return false;
			}
		} else if (!associationId.equals(other.associationId)) {
			return false;
		}
		if (lowerValue == null) {
			if (other.lowerValue != null) {
				return false;
			}
		} else if (!lowerValue.equals(other.lowerValue)) {
			return false;
		}
		if (upperValue == null) {
			if (other.upperValue != null) {
				return false;
			}
		} else if (!upperValue.equals(other.upperValue)) {
			return false;
		}
		return true;
	}
}
