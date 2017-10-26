package client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the client package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _CheckTrainResponse_QNAME = new QName(
			"http://service/", "checkTrainResponse");
	private final static QName _CheckTrain_QNAME = new QName("http://service/",
			"checkTrain");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: client
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link Train }
	 * 
	 */
	public Train createTrain() {
		return new Train();
	}

	/**
	 * Create an instance of {@link CheckTrainResponse }
	 * 
	 */
	public CheckTrainResponse createCheckTrainResponse() {
		return new CheckTrainResponse();
	}

	/**
	 * Create an instance of {@link CheckTrain }
	 * 
	 */
	public CheckTrain createCheckTrain() {
		return new CheckTrain();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link CheckTrainResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service/", name = "checkTrainResponse")
	public JAXBElement<CheckTrainResponse> createCheckTrainResponse(
			CheckTrainResponse value) {
		return new JAXBElement<CheckTrainResponse>(_CheckTrainResponse_QNAME,
				CheckTrainResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CheckTrain }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service/", name = "checkTrain")
	public JAXBElement<CheckTrain> createCheckTrain(CheckTrain value) {
		return new JAXBElement<CheckTrain>(_CheckTrain_QNAME, CheckTrain.class,
				null, value);
	}

}
