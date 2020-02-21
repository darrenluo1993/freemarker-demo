package pers.darren.demo.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import pers.darren.demo.common.ConfigSingleton;
import pers.darren.demo.model.Product;

@WebServlet("/demo")
public class DemoServlet extends HttpServlet {

	private static final long serialVersionUID = -7663522394821355777L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 创建数据模型
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("user", "Darren Luo");
		Map<String, Object> latestProduct = new HashMap<String, Object>();
		latestProduct.put("name", "Orange");
		latestProduct.put("url", "/products/Orange.html");
		root.put("latestProduct", latestProduct);
		List<Product> productList = new ArrayList<Product>();
		productList.add(new Product("产品一", new BigDecimal(1000.5), 10000, "产品一的描述", "Darren Luo", new Date()));
		productList.add(new Product("产品二", new BigDecimal(1001.5), 10001, "产品二的描述", "Darren Luo", new Date()));
		productList.add(new Product("产品三", new BigDecimal(1002.5), 10002, "产品三的描述", "Darren Luo", new Date()));
		productList.add(new Product("产品四", new BigDecimal(1003.5), 10003, "产品四的描述", "Darren Luo", new Date()));
		root.put("productList", productList);

		// 获取FreeMarker配置
		Configuration configuration = ConfigSingleton.getInstance();
		// 获取模板并输出
		Template template = configuration.getTemplate("demo.ftlh");
		try {
			template.process(root, resp.getWriter());
		} catch (TemplateException e) {
			e.printStackTrace(resp.getWriter());
		}
	}
}