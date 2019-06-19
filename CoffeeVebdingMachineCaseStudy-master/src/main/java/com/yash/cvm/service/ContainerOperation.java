package com.yash.cvm.service;


import org.apache.log4j.Logger;

import com.yash.cvm.model.BlackCoffee;
import com.yash.cvm.model.BlackTea;
import com.yash.cvm.model.Coffee;
import com.yash.cvm.model.InputScanner;
import com.yash.cvm.model.Container;
import com.yash.cvm.model.Tea;

public class ContainerOperation {

	private final static Logger logger = Logger.getLogger(ContainerOperation.class);
	private Integer refillAmount = 0;
	InputScanner inputScanner = new InputScanner();
	 
    Tea tea=new Tea();
    BlackTea blackTea=new BlackTea();
    Coffee coffee=new Coffee();
    BlackCoffee blackCoffee=new BlackCoffee();
    

	public boolean checkAvailabiltyOfRequiredMaterial(String productType, Integer quantity, Container product) {
		if (productType.equalsIgnoreCase("Tea")) {
			if ((product.getTeaContainerCapacity() - quantity * tea.getTeaQuantity() < 0)
					|| (product.getSugarContainerCapacity() - quantity * tea.getSugarQuantity() < 0)
					|| (product.getWaterContainerCapacity() - quantity * tea.getWaterQuantity() < 0)
					|| (product.getMilkContainerCapacity() - quantity * tea.getMilkQuantity() < 0)) {
				return false;
			}
			return true;
		} 
       if (productType.equalsIgnoreCase("Black Tea")) {

    	   if ((product.getTeaContainerCapacity() - quantity * blackTea.getTeaQuantity() < 0)
					||  (product.getWaterContainerCapacity() - quantity * blackTea.getWaterQuantity() < 0)
					|| (product.getSugarContainerCapacity() - quantity * blackTea.getSugarQuantity() < 0)) {
				return false;
			}
			return true;
		} else if (productType.equalsIgnoreCase("Coffee")) {
			if ((product.getCoffeeContainerCapacity() - quantity * coffee.getCoffeeQuantity()  < 0)
					|| (product.getSugarContainerCapacity() - quantity * coffee.getSugarQuantity() < 0)
					|| (product.getWaterContainerCapacity() - quantity * coffee.getWaterQuantity() < 0)
					|| (product.getMilkContainerCapacity() - quantity * coffee.getMilkQuantity() < 0)) {
				return false;
			}
			return true;
		} else if (productType.equalsIgnoreCase("Black Coffee")) {
			if ((product.getCoffeeContainerCapacity() - quantity * blackCoffee.getCoffeeQuantity() < 0)
					|| (product.getSugarContainerCapacity() - quantity * blackCoffee.getSugarQuantity() < 0)
					|| (product.getWaterContainerCapacity() - quantity * blackCoffee.getWaterQuantity() < 0)) {
				return false;
			}
			return true;
		}
       
       
		return false;
	}

	
	public void adjustContainerQuantity(String productType, Integer quantity, Container product) {
		if (productType.equalsIgnoreCase("Tea")) {
			product.setTeaContainerCapacity(product.getTeaContainerCapacity() - (quantity * ( tea.getTeaQuantity() + tea.getWasteedTeaQuantity())));
			product.setWaterContainerCapacity(product.getWaterContainerCapacity() - (quantity *( tea.getWaterQuantity() +  tea.getWastedWaterQuantity())));
			product.setSugarContainerCapacity(product.getSugarContainerCapacity() - (quantity * (tea.getSugarQuantity() +  tea.getWastedSugarQuantity())));
			product.setMilkContainerCapacity(product.getMilkContainerCapacity() - (quantity * (tea.getMilkQuantity() +  tea.getWastedMilkQuantity())));
            
			product.setTeaWasteAmount(product.getTeaWasteAmount() + quantity * tea.getWasteedTeaQuantity());
			product.setWaterWasteAmount(product.getWaterWasteAmount() + quantity * tea.getWastedWaterQuantity());
			product.setSugarWasteAmount(product.getSugarWasteAmount() + quantity * tea.getWastedSugarQuantity());
			product.setMilkWasteAmount(product.getMilkWasteAmount() + quantity * tea.getWastedMilkQuantity());

		}else if (productType.equalsIgnoreCase("Black Tea")) {
			
			product.setTeaContainerCapacity(product.getTeaContainerCapacity() - (quantity * (blackTea.getTeaQuantity()+blackTea.getWastedTeaQuantity())));
			product.setWaterContainerCapacity(product.getWaterContainerCapacity() - (quantity *( blackTea.getWaterQuantity()+blackTea.getWastedWaterQuantity())));
			product.setSugarContainerCapacity(product.getSugarContainerCapacity() - (quantity * ( blackTea.getSugarQuantity()+blackTea.getWastedSugarQuantity())));

	
			product.setWaterWasteAmount(product.getWaterWasteAmount() + quantity * blackTea.getWastedWaterQuantity());
			product.setSugarWasteAmount(product.getSugarWasteAmount() + quantity * blackTea.getWastedSugarQuantity());
			
		}else if (productType.equalsIgnoreCase("Coffee")) {
			product.setCoffeeContainerCapacity(product.getCoffeeContainerCapacity() - (quantity * (coffee.getCoffeeQuantity()+coffee.getWasteedCoffeeQuantity())));
			product.setMilkContainerCapacity(product.getMilkContainerCapacity() - (quantity * (coffee.getMilkQuantity()+coffee.getWastedMilkQuantity())));
			product.setSugarContainerCapacity(product.getSugarContainerCapacity() - (quantity * (coffee.getSugarQuantity()+coffee.getWastedSugarQuantity())));
			product.setWaterContainerCapacity(product.getWaterContainerCapacity() - (quantity * (coffee.getWaterQuantity()+coffee.getWastedWaterQuantity())));

			product.setCoffeeWasteAmount(product.getCoffeeWasteAmount() + quantity * coffee.getWasteedCoffeeQuantity());
			product.setMilkWasteAmount(product.getMilkWasteAmount() + quantity * coffee.getWastedMilkQuantity());
			product.setSugarWasteAmount(product.getSugarWasteAmount() + quantity * coffee.getWastedSugarQuantity());
			product.setWaterWasteAmount(product.getWaterWasteAmount() + quantity * coffee.getWastedWaterQuantity());

		} else if (productType.equalsIgnoreCase("Black Coffee")) {
			product.setCoffeeContainerCapacity(product.getCoffeeContainerCapacity() - (quantity * blackCoffee.getCoffeeQuantity()+blackCoffee.getWasteedCoffeeQuantity() ));
			product.setSugarContainerCapacity(product.getSugarContainerCapacity() - (quantity * (blackCoffee.getSugarQuantity()+blackCoffee.getWastedSugarQuantity())));
			product.setWaterContainerCapacity(product.getWaterContainerCapacity() - (quantity * (blackCoffee.getWaterQuantity()+blackCoffee.getWastedWaterQuantity())));

			product.setSugarWasteAmount(product.getSugarWasteAmount() + quantity * blackCoffee.getWastedSugarQuantity());
			product.setWaterWasteAmount(product.getWaterWasteAmount() + quantity * blackCoffee.getWastedWaterQuantity());

		}
		

	}

	public void resetContainer(Container product) {

		product.setTeaContainerCapacity(2000);
		product.setCoffeeContainerCapacity(2000);
		product.setMilkContainerCapacity(10000);
		product.setWaterContainerCapacity(15000);
		product.setSugarContainerCapacity(8000);
		logger.info("All Containers successfully Reset");

	}

	
	public void checkContainerStatus(Container product) {

		logger.info("****Quantity Available in Container****");
		logger.info("Tea:" + product.getTeaContainerCapacity());
		logger.info("Coffee:" + product.getCoffeeContainerCapacity());
		logger.info("Milk:" + product.getMilkContainerCapacity());
		logger.info("Sugar:" + product.getSugarContainerCapacity());
		logger.info("Water:" + product.getWaterContainerCapacity());

	}

	
	public void reFillContainer(Integer productID, Container product) {
	
			if (productID == 1 && product.getTeaContainerCapacity() < 2000) {
				logger.info("Please enter the amount of Tea needs to be Refilled But should be less than OR equal to  "+ (2000-product.getTeaContainerCapacity()));
				refillAmount=inputScanner.nextInt();
				
				if(refillAmount<=(2000-product.getTeaContainerCapacity())){
				   product.setTeaContainerCapacity(product.getTeaContainerCapacity()+refillAmount);
				   logger.info("Successfully Refilled");
				}else
				{
					logger.error("Tea Conatiner Overflow");
				}
				
			} else if (productID == 2 && product.getCoffeeContainerCapacity() < 2000) {
				
				logger.info("Current capacity of Coffee Container is :"+ (product.getCoffeeContainerCapacity()));
				logger.info("Please enter the amount of Coffee needs to be Refilled But should be less than OR equal to  "+(2000-product.getCoffeeContainerCapacity()));
				refillAmount=inputScanner.nextInt();
				
				if(refillAmount<=(2000-product.getCoffeeContainerCapacity())){
				   product.setCoffeeContainerCapacity(product.getCoffeeContainerCapacity()+refillAmount);
				   logger.info("Successfully Refilled");
				}else
				{
					logger.error("Coffee Conatiner Overflow");
				}
				
				
			} else if (productID == 3 && product.getMilkContainerCapacity() < 10000) {
				
				logger.info("Current capacity of Milk Container is :"+ (product.getMilkContainerCapacity()));
				logger.info("Please enter the amount of Coffee needs to be Refilled But should be less than OR equal to "+(10000-product.getMilkContainerCapacity()));
				refillAmount=inputScanner.nextInt();
				
				if(refillAmount<=(10000-product.getMilkContainerCapacity())){
				   product.setMilkContainerCapacity(product.getMilkContainerCapacity()+refillAmount);
				   logger.info("Successfully Refilled");
				}else
				{
					logger.error("Milk Conatiner Overflow");
				}
				
				
			} else if (productID == 4 && product.getWaterContainerCapacity() < 15000) {
				
				logger.info("Current capacity of Water Container is :"+ (product.getWaterContainerCapacity()));
				logger.info("Please enter the amount of Water needs to be Refilled But should be less than OR equal to "+(15000-product.getWaterContainerCapacity()));
				refillAmount=inputScanner.nextInt();
				
				if(refillAmount<=(15000-product.getWaterContainerCapacity())){
				   product.setWaterContainerCapacity(product.getWaterContainerCapacity()+refillAmount);
				   logger.info("Successfully Refilled");
				}else
				{
					logger.error("Water Conatiner Overflow");
				}
				
				
			} else if (productID == 5 && product.getSugarContainerCapacity() < 8000) {
				logger.info("Current capacity of Sugar Container is :"+ (product.getSugarContainerCapacity()));
				logger.info("Please enter the amount of Suagr needs to be Refilled But should be less than OR equal to  "+(8000-product.getSugarContainerCapacity()));
				refillAmount=inputScanner.nextInt();
				
				if(refillAmount<=(8000-product.getSugarContainerCapacity())){
				   product.setSugarContainerCapacity(product.getSugarContainerCapacity()+refillAmount);
				   logger.info("Successfully Refilled");
				}else
				{
					logger.error("Sugar Conatiner Overflow");
				}
				
				
			} 
		

	}



}
