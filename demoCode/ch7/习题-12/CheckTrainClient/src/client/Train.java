package client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for train complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="train">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="destination" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="reachTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remainder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="startPlace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trainNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "train", propOrder = { "destination", "price", "reachTime",
		"remainder", "startPlace", "startTime", "trainNum" })
public class Train {

	protected String destination;
	protected float price;
	protected String reachTime;
	protected int remainder;
	protected String startPlace;
	protected String startTime;
	protected String trainNum;

	/**
	 * Gets the value of the destination property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Sets the value of the destination property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDestination(String value) {
		this.destination = value;
	}

	/**
	 * Gets the value of the price property.
	 * 
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Sets the value of the price property.
	 * 
	 */
	public void setPrice(float value) {
		this.price = value;
	}

	/**
	 * Gets the value of the reachTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReachTime() {
		return reachTime;
	}

	/**
	 * Sets the value of the reachTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReachTime(String value) {
		this.reachTime = value;
	}

	/**
	 * Gets the value of the remainder property.
	 * 
	 */
	public int getRemainder() {
		return remainder;
	}

	/**
	 * Sets the value of the remainder property.
	 * 
	 */
	public void setRemainder(int value) {
		this.remainder = value;
	}

	/**
	 * Gets the value of the startPlace property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStartPlace() {
		return startPlace;
	}

	/**
	 * Sets the value of the startPlace property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStartPlace(String value) {
		this.startPlace = value;
	}

	/**
	 * Gets the value of the startTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * Sets the value of the startTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStartTime(String value) {
		this.startTime = value;
	}

	/**
	 * Gets the value of the trainNum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTrainNum() {
		return trainNum;
	}

	/**
	 * Sets the value of the trainNum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTrainNum(String value) {
		this.trainNum = value;
	}

}
