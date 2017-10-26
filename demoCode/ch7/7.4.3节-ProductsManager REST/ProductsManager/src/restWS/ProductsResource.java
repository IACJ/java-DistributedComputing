package restWS;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.sun.jersey.spi.resource.Singleton;

@Produces("application/xml")
@Path("products")
@Singleton
public class ProductsResource {
	//��TreeMap���������в�Ʒ����Ϣ���൱�����ݿ⣩
	private TreeMap<String, Product> productMap=new TreeMap<String, Product>();
	
	//��ʼ�����캯������ʼ����Ʒ��Ϣ��TreeMap
	public ProductsResource(){
		Product product=new Product();
		product.setId("PRO-1");
		product.setName("CDT���� 8GB");
		product.setPrice((float)56.5);
		product.setProduceTime("2013-5-28");
		product.setProduceAera("ӡ��");
		addProduct(product);
	}
	
	@GET
	public List<Product> getProducts() {
		List<Product> products=new ArrayList<Product>();
		products.addAll(productMap.values());
		return products;
	}

	@GET
	@Path("{id}")
	public Product getProduct(@PathParam("id") String pId) {
		return productMap.get(pId);
	}

	@POST
	@Path("add")
	@Produces("text/html")
	@Consumes("application/xml")
	public String addProduct(Product product) {
		//�����ƷIDΪ��PRO-��+��Ϣ���Ʒ����+1
		int temp=productMap.size()+1;
		String newId="PRO-"+String.valueOf(temp);
		product.setId(newId);
		//�����Ʒʱ�����XML�ĵ����ð�����ƷID����Ϊ����ID��ϵͳ�Զ�����
		productMap.put(newId, product);
		return "�²�Ʒ��"+product.getName()+",�ɹ���ӽ���Ʒ��Ϣ�⣬��IDΪ��"+product.getId();		
	}

	@DELETE
	@Path("delete/{id}")
	public String deleteProduct(@PathParam("id") String pId) {
		//�����Ϣ���д��ڴ�pId�Ĳ�Ʒ��Ϣ����ɾ����
		if(productMap.containsKey(pId)){
			productMap.remove(pId);
			return "IDΪ��"+pId+"�Ĳ�Ʒ�ѳɹ�ɾ����";
		}
		else{
			return "��Ʒ��Ϣ���в�����IDΪ��"+pId+"�Ĳ�Ʒ��ɾ��ʧ�ܣ�";
		}
	}
	
	@PUT
	@Produces("text/html")
	@Consumes("application/xml")
	@Path("update/{id}")
	public String updateProduct(@PathParam("id") String pId, Product product) {
		//���²�Ʒ��Ϣʱ����ƷID���ܸ���
		if(productMap.containsKey(pId)){
			product.setId(pId);
			productMap.put(pId, product);
			return "IDΪ��"+pId+"�Ĳ�Ʒ�۸���³ɹ���";
		}
		else{
			return "��Ʒ��Ϣ���в�����IDΪ��"+pId+"�Ĳ�Ʒ������ʧ�ܣ�";
		}
	}
}