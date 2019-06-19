package com.yash.cvm.TeaCoffeeVendingMachine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import com.yash.cvm.TeaCoffeeVendingMachine.Order;
import com.yash.cvm.TeaCoffeeVendingMachine.TeaCoffeeVendingMachineMain;

@RunWith(MockitoJUnitRunner.class)
public class TeaCoffeeVendingMachineMainTest {

	@InjectMocks
	private TeaCoffeeVendingMachineMain teaCoffeeVendingMachine;

	@Mock
	private static com.yash.cvm.model.Container product;

	@Mock
	private static Order Order;

	@Test
	public void mainTest() {
	
		
	//	Mockito.doNothing().when(customerOrder).getMenu(product);
		
	//	TeaCoffeeVendingMachine.main(null);
		

	}

}
