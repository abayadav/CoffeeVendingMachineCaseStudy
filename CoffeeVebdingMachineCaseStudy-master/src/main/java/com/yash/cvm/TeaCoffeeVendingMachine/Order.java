package com.yash.cvm.TeaCoffeeVendingMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.yash.cvm.model.BlackCoffee;
import com.yash.cvm.model.BlackTea;
import com.yash.cvm.model.Coffee;
import com.yash.cvm.model.InputScanner;
import com.yash.cvm.model.Container;
import com.yash.cvm.model.Tea;
import com.yash.cvm.model.TotalSaleCost;
import com.yash.cvm.service.ContainerOperation;
import com.yash.cvm.service.GenerateReport;


public class Order {
	
	private final static Logger logger = Logger.getLogger(Order.class);
	
	
	private Integer totalQuantitySold = 0;
    private Double total = 0.0;
   
 
    private Integer refillOption = 0;
    
    @SuppressWarnings("rawtypes")
	HashMap<String, List> totalSalePercontainer = new HashMap<String,List>();
    List<TotalSaleCost> totalSaleCostList = new ArrayList<TotalSaleCost>();
	
    InputScanner inputScanner = new InputScanner();
    ContainerOperation containerOperations = new ContainerOperation();
    GenerateReport generateReport = new GenerateReport();
	

	
    Tea tea=new Tea();
    BlackTea blackTea=new BlackTea();
    Coffee coffee=new Coffee();
    BlackCoffee blackCoffee=new BlackCoffee();
    
    public Order(){
    	
    }
    

	public void startMenu(Container product) {
		
		logger.info("****Welcome****");
	
		int askUser;
		
		do {
			

			logger.info("1.Tea \n2.Black Tea \n3.Coffee \n4.Black Coffee \n5.Reset Container \n6.Refill Container \n7.Check Container Status \n8.Report \n9.Exit");
			
			int quantityOfOrder = 0;
			logger.info("Please enter the order number:");
			int selectedOrder = inputScanner.nextInt();
			
			if(selectedOrder<=4){
				logger.info("Please enter the quantity of drink required:");
				quantityOfOrder= inputScanner.nextInt();
			}
			
			switch (selectedOrder) {
			case 1:
				logger.info("You have ordered " + quantityOfOrder + " Cup of Tea");
				prepareOrder("Tea",tea.getTeaPrice(), quantityOfOrder, product);
				
				break;
	
				
			case 2:
				
				logger.info("You have ordered " + quantityOfOrder + " Cup of Black Tea");
				prepareOrder("Black Tea",blackTea.getBlackTeaPrice(), quantityOfOrder, product);
				
				break;

			case 3:
				
				logger.info("You have ordered " + quantityOfOrder + " Cup of  Coffee");
				prepareOrder("Coffee", coffee.getCoffeePrice(), quantityOfOrder, product);
				break;
			
			case 4:
				
				logger.info("You have ordered " + quantityOfOrder + " Cup of  Black Coffee");
				prepareOrder("Black Coffee",blackCoffee.getCoffeePrice(), quantityOfOrder, product);
				break;
		
			case 5:
				containerOperations.resetContainer(product);
				break;
			
			case 6:
				
				logger.info("****Refill Container****");
				logger.info("\n1.Tea\n2.Coffee\n3.Milk\n4.Sugar\n5.Water");
				logger.info("Select Coantiner you have to refill :");
				refillOption = inputScanner.nextInt();
				containerOperations.reFillContainer(refillOption, product);
				break;
			
			case 7:
				containerOperations.checkContainerStatus(product);
				break;
			
			case 8:
				
				logger.info("****Report****");
				generateReport.prepareReport(product, totalSalePercontainer, totalQuantitySold, total);
				break;
			
			

			default:
				
				logger.warn("You have entered invalid option!");
				break;
			}
			

			logger.info("Enter 0 for Menu Or 1 to Exit:");
			askUser = inputScanner.nextInt();
			if(askUser==1){
				System.out.println("Thank you!!!");
			}
		} while (askUser==0);
		
	}



	public void prepareOrder(String orderType, double costPerUnit, int quantity, Container product) {
	
			
			if (containerOperations.checkAvailabiltyOfRequiredMaterial(orderType, quantity, product)) {

						containerOperations.adjustContainerQuantity(orderType, quantity, product);
                        
						logger.info("Your drink is ready!!");
		               
						totalQuantitySold = totalQuantitySold + quantity;
						total = total + costPerUnit * quantity;
						
						
						totalSaleCostList.add(new TotalSaleCost(1, "Tea", 0, 0.0));
						totalSaleCostList.add(new TotalSaleCost(2, "Black Tea", 0, 0.0));
						totalSaleCostList.add(new TotalSaleCost(3, "Coffee", 0, 0.0));
						totalSaleCostList.add(new TotalSaleCost(4, "Black Coffee", 0, 0.0));
						
						
                       	for (TotalSaleCost totalCost : totalSaleCostList) {
							if (totalCost.getProductName().equals(orderType)) {
								
								TotalSaleCost totalSaleCost = new TotalSaleCost();
								totalSaleCost.setCost(totalCost.getCost() + quantity * costPerUnit);
								totalSaleCost.setQuantity(totalCost.getQuantity() + quantity);
								totalSaleCost.setProductName(totalCost.getProductName());
								totalSaleCost.setProductID(totalCost.getProductID());

								totalSaleCostList.set(totalSaleCostList.indexOf(totalCost), totalSaleCost);

							}

						}

						totalSalePercontainer.put("total_Sale_Cost", totalSaleCostList);
					}
		 else {
			 logger.error("Require quantity is not available, Please try again!");

			}
		
	}


}
