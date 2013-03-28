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
}
