package restWS;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	private String id;	//产品ID
	private String name;	//产品名称
	private float price;	//价格
	private String produceTime;	//生产日期
	private String produceAera;	//产地
	
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
