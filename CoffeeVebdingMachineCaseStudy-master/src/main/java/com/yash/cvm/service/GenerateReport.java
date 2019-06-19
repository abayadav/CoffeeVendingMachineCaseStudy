package com.yash.cvm.service;


import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import com.yash.cvm.model.Container;
import com.yash.cvm.model.TotalSaleCost;

public class GenerateReport {
	
	
	private final static Logger logger = Logger.getLogger(GenerateReport.class);
	
	ContainerOperation containerOperation=new ContainerOperation();
	
	public GenerateReport() {
		
	}

	public void prepareReport(Container product, @SuppressWarnings("rawtypes") HashMap<String, List> totalSale, Integer totalQuantitySold,
			Double totalPrice) {

		logger.info("*****Tea-Cofee Sold*****");
		@SuppressWarnings("unchecked")
		List<TotalSaleCost> totalSaleCost = totalSale.get("total_Sale_Cost");
		for (TotalSaleCost e : totalSaleCost) {
			logger.info(e.getProductName() + ":" + e.getQuantity() + " cup :" + e.getCost());

		}
		logger.info("*****Total Product Sold And Price*****");
		logger.info("Total cups:" + totalQuantitySold);
		logger.info("Total:" + totalPrice + " Rs");

		logger.info("*****Available Quantity in Container******");
		logger.info("Tea:" + product.getTeaContainerCapacity());
		logger.info("Coffee:" + product.getCoffeeContainerCapacity());
		logger.info("Water:" + product.getWaterContainerCapacity());
		logger.info("Milk:" + product.getMilkContainerCapacity());
		logger.info("Sugar:" + product.getSugarContainerCapacity());
		
		
		//containerOperation.checkContainerStatus(product);
		
		logger.info("*****Waste Of Products*****");
		logger.info("Tea Wasted:" + product.getTeaWasteAmount());
		logger.info("Coffee Wasted:" + product.getCoffeeWasteAmount());
		logger.info("Water Wasted:" + product.getWaterWasteAmount());
		logger.info("Sugar Wasted:" + product.getSugarWasteAmount());
		logger.info("Milk Wasted:" + product.getMilkWasteAmount());

	}

}
