package restWS;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	private String id;	//��ƷID
	private String name;	//��Ʒ����
	private float price;	//�۸�
	private String produceTime;	//��������
	private String produceAera;	//����
	
	public Product() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getProduceTime() {
		return produceTime;
	}
	public void setProduceTime(String produceTime) {
		this.produceTime = produceTime;
	}
	public String getProduceAera() {
		return produceAera;
	}
	public void setProduceAera(String produceAera) {
		this.produceAera = produceAera;
	}
}
