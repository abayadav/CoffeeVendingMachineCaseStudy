package com.yash.cvm.TeaCoffeeVendingMachine;
import com.yash.cvm.model.Container;


public class TeaCoffeeVendingMachineMain {

	private static Order order=new Order();
	private static  Container product=new Container();
	
	
	public static void main(String[] args) {
		
		 order.startMenu(product);
	}
}
