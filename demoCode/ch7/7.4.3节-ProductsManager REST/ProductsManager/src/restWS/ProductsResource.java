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
	//用TreeMap来保存所有产品的信息（相当于数据库）
	private TreeMap<String, Product> productMap=new TreeMap<String, Product>();
	
	//初始化构造函数，初始化商品信息库TreeMap
	public ProductsResource(){
		Product product=new Product();
		product.setId("PRO-1");
		product.setName("CDT优盘 8GB");
		product.setPrice((float)56.5);
		product.setProduceTime("2013-5-28");
		product.setProduceAera("印尼");
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
		//添加新品ID为“PRO-”+信息库产品总量+1
		int temp=productMap.size()+1;
		String newId="PRO-"+String.valueOf(temp);
		product.setId(newId);
		//添加新品时输入的XML文档不用包含产品ID，因为这里ID由系统自动生成
		productMap.put(newId, product);
		return "新产品："+product.getName()+",成功添加进产品信息库，其ID为："+product.getId();		
	}

	@DELETE
	@Path("delete/{id}")
	public String deleteProduct(@PathParam("id") String pId) {
		//如果信息库中存在此pId的产品信息，则删除它
		if(productMap.containsKey(pId)){
			productMap.remove(pId);
			return "ID为："+pId+"的产品已成功删除！";
		}
		else{
			return "产品信息库中不存在ID为："+pId+"的产品，删除失败！";
		}
	}
	
	@PUT
	@Produces("text/html")
	@Consumes("application/xml")
	@Path("update/{id}")
	public String updateProduct(@PathParam("id") String pId, Product product) {
		//更新产品信息时，产品ID不能更改
		if(productMap.containsKey(pId)){
			product.setId(pId);
			productMap.put(pId, product);
			return "ID为："+pId+"的产品价格更新成功！";
		}
		else{
			return "产品信息库中不存在ID为："+pId+"的产品，更新失败！";
		}
	}
}