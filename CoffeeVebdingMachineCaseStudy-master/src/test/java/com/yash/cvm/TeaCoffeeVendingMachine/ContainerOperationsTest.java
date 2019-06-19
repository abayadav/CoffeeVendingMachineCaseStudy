package com.yash.cvm.TeaCoffeeVendingMachine;


import static org.mockito.Mockito.when;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import com.yash.cvm.model.InputScanner;
import com.yash.cvm.model.Container;
import com.yash.cvm.service.ContainerOperation;

@RunWith(MockitoJUnitRunner.class)
public class ContainerOperationsTest {

	@InjectMocks
	private ContainerOperation containerOperations;

	@Mock
	private Container container;
	
	@Mock
	private InputScanner inputScanner;

	

	@Test
	public void adjustContainerQuantityShouldUpdateQuantitiesWhenOrderIsTea() {

		Container containerForTea = new Container();

		containerOperations.adjustContainerQuantity("Tea", 1, containerForTea);

		assertEquals((Integer) 1, containerForTea.getTeaWasteAmount());
		assertEquals((Integer) 4, containerForTea.getMilkWasteAmount());
		assertEquals((Integer) 5, containerForTea.getWaterWasteAmount());
		assertEquals((Integer) 2, containerForTea.getSugarWasteAmount());

	}

	@Test
	public void adjustContainerQuantityShouldUpdateQuantitiesWhenOrderIsBlackTea() {

		Container containerForBlackTea = new Container();

		containerOperations.adjustContainerQuantity("Black Tea", 1, containerForBlackTea);

		assertEquals((Integer) 12, containerForBlackTea.getWaterWasteAmount());
		assertEquals((Integer) 2, containerForBlackTea.getSugarWasteAmount());

	}

	@Test
	public void adjustContainerQuantityShouldUpdateQuantitiesWhenOrderIsCoffee() {

		Container containerForCoffee = new Container();

		containerOperations.adjustContainerQuantity("Coffee", 1, containerForCoffee);

		assertEquals((Integer) 1, containerForCoffee.getCoffeeWasteAmount());
		assertEquals((Integer) 3, containerForCoffee.getWaterWasteAmount());
		assertEquals((Integer) 8, containerForCoffee.getMilkWasteAmount());
		assertEquals((Integer) 2, containerForCoffee.getSugarWasteAmount());

	}
	
	@Test
	public void adjustContainerQuantityShouldUpdateQuantitiesWhenOrderIsBlackCoffee() {

		Container containerForBlackCoffee = new Container();

		containerOperations.adjustContainerQuantity("Black Coffee", 1, containerForBlackCoffee);

		assertEquals((Integer) 12, containerForBlackCoffee.getWaterWasteAmount());
		assertEquals((Integer) 2, containerForBlackCoffee.getSugarWasteAmount());

	}
	
	@Test
	public void adjustContainerQuantityShouldUpdateQuantitiesWhenOrderIsNull() {

		Container containerForNull = new Container();

		containerOperations.adjustContainerQuantity("", 1, containerForNull);

	}


	@Test
	public void shouldRefillTeaContainer() {

		when(container.getTeaContainerCapacity()).thenReturn(1200);
		when(inputScanner.nextInt()).thenReturn(700);

		containerOperations.reFillContainer(1, container);

	}
	
	@Test
	public void shouldRefillCoffeeContainer() {

		when(container.getCoffeeContainerCapacity()).thenReturn(1200);

		when(inputScanner.nextInt()).thenReturn(700);
		containerOperations.reFillContainer(2, container);

	}

	@Test
	public void shouldRefillMilkContainer() {

		when(container.getMilkContainerCapacity()).thenReturn(8000);
		when(inputScanner.nextInt()).thenReturn(1500);

		containerOperations.reFillContainer(3, container);

	}

	@Test
	public void shouldRefillWaterContainer() {

		when(container.getWaterContainerCapacity()).thenReturn(100);

		containerOperations.reFillContainer(4, container);

	}

	@Test
	public void shouldRefillSugarContainer() {

		when(container.getSugarContainerCapacity()).thenReturn(100);

		containerOperations.reFillContainer(5, container);
	}

	@Test
	public void shouldCheckContainerStatus() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getCoffeeContainerCapacity()).thenReturn(1200);
		when(container.getWaterContainerCapacity()).thenReturn(14000);
		when(container.getSugarContainerCapacity()).thenReturn(7000);
		when(container.getMilkContainerCapacity()).thenReturn(10000);

		containerOperations.checkContainerStatus(container);

	}

	@Test
	public void shouldResetContainers() {

		containerOperations.resetContainer(container);

	}

	@Test
	public void shouldReturnTrueForCheckAvailabilityWhenOrderIsTea() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		assertTrue(containerOperations.checkAvailabiltyOfRequiredMaterial("Tea", 1, container));

	}

	@Test
	public void shouldReturnTrueForCheckAvailabilityWhenOrderIsBlackTea() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		assertTrue(containerOperations.checkAvailabiltyOfRequiredMaterial("Black Tea", 1, container));

	}

	@Test
	public void shouldReturnTrueForCheckAvailabilityWhenOrderIsCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		assertTrue(containerOperations.checkAvailabiltyOfRequiredMaterial("Coffee", 1, container));

	}

	@Test
	public void shouldReturnTrueForCheckAvailabilityWhenOrderIsBlackCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		assertTrue(containerOperations.checkAvailabiltyOfRequiredMaterial("Black Coffee", 1, container));

	}

	@Test
	public void shouldReturnFalseForCheckAvailabilityWhenTeaContainerContainsLessThanRequiredForOrderTea() {

		when(container.getTeaContainerCapacity()).thenReturn(1);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Tea", 1, container));

	}

	@Test
	public void shouldReturnFalseForCheckAvailabilityWhenMilkContainerContainsLessThanRequiredForOrderTea() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(1);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabiltyOfRequiredMaterial("Tea", 1, container);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Tea", 1, container));
	}

	@Test
	public void shouldReturnFalseForCheckAvailabilityWhenWaterContainerContainsLessThanRequiredForOrderTea() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(2);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabiltyOfRequiredMaterial("Tea", 1, container);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Tea", 1, container));
	}

	@Test
	public void shouldReturnFalseForCheckAvailabilityWhenSugarContainerContainsLessThanRequiredForOrderTea() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(3);

		containerOperations.checkAvailabiltyOfRequiredMaterial("Tea", 1, container);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Tea", 1, container));
	}

	@Test
	public void shouldReturnFalseForCheckAvailabilityWhenTeaContainerContainsLessThanRequiredForOrderBlackTea() {

		when(container.getTeaContainerCapacity()).thenReturn(1);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabiltyOfRequiredMaterial("Black Tea", 1, container);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Black Tea", 1, container));

	}

	@Test
	public void shouldReturnFalseForCheckAvailabilityWhenWaterContainerContainsLessThanRequiredForOrderBlackTea() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getWaterContainerCapacity()).thenReturn(1);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabiltyOfRequiredMaterial("Black Tea", 1, container);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Black Tea", 1, container));

	}

	@Test
	public void shouldReturnFalseForCheckAvailabilityWhenSugarContainerContainsLessThanRequiredForOrderBlackTea() {

		when(container.getTeaContainerCapacity()).thenReturn(2000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(1);

		containerOperations.checkAvailabiltyOfRequiredMaterial("Black Tea", 1, container);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Black Tea", 1, container));
	}

	@Test
	public void shouldReturnFalseWhenCoffeeContainerContainsLessThanRequiredForOrderCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabiltyOfRequiredMaterial("Coffee", 1, container);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Coffee", 1, container));

	}

	@Test
	public void shouldReturnFalseWhenWaterContainerContainsLessThanRequiredForOrderCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(1);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabiltyOfRequiredMaterial("Coffee", 1, container);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Coffee", 1, container));
	}

	@Test
	public void shouldReturnFalseWhenMilkContainerContainsLessThanRequiredForOrderCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(4);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabiltyOfRequiredMaterial("Coffee", 1, container);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Coffee", 1, container));

	}

	@Test
	public void shouldReturnFalseWhenSugarContainerContainsLessThanRequiredForOrderCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2000);
		when(container.getMilkContainerCapacity()).thenReturn(10000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(5);

		containerOperations.checkAvailabiltyOfRequiredMaterial("Coffee", 1, container);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Coffee", 1, container));

	}

	@Test
	public void shouldReturnFalseWhenCoffeeContainerContainsLessThanRequiredForOrderBlackCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(1);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabiltyOfRequiredMaterial("Black Coffee", 1, container);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Black Coffee", 1, container));

	}

	@Test
	public void shouldReturnFalseWhenWaterContainerContainsLessThanRequiredForOrderBlackCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2000);
		when(container.getWaterContainerCapacity()).thenReturn(2);
		when(container.getSugarContainerCapacity()).thenReturn(8000);

		containerOperations.checkAvailabiltyOfRequiredMaterial("Black Coffee", 1, container);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Black Coffee", 1, container));

	}

	@Test
	public void shouldReturnFalseWhenSugarContainerContainsLessThanRequiredForOrderBlackCoffee() {

		when(container.getCoffeeContainerCapacity()).thenReturn(2000);
		when(container.getWaterContainerCapacity()).thenReturn(15000);
		when(container.getSugarContainerCapacity()).thenReturn(2);

		containerOperations.checkAvailabiltyOfRequiredMaterial("Black Coffee", 1, container);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Black Coffee", 1, container));

	}

	@Test
	public void shouldReturnFalseWhencontainerTypeIsUnknown() {

		containerOperations.checkAvailabiltyOfRequiredMaterial("Pepsi", 1, container);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Pepsi", 1, container));

	}
	
	

	@Test
	public void reFillContainershouldHandleExceptionWhenTeaContainerIsFull() {

		when(container.getTeaContainerCapacity()).thenReturn(1200);
		when(inputScanner.nextInt()).thenReturn(900);

		containerOperations.reFillContainer(1, container);

	}
	

	@Test
	public void reFillContainershouldHandleExceptionWhenCoffeeContainerIsFull() {

		when(container.getCoffeeContainerCapacity()).thenReturn(1200);
		when(inputScanner.nextInt()).thenReturn(900);

		containerOperations.reFillContainer(2, container);

	}

	@Test
	public void reFillContainershouldHandleExceptionWhenMilkContainerIsFull() {

		when(container.getMilkContainerCapacity()).thenReturn(7000);
		when(inputScanner.nextInt()).thenReturn(3500);

		containerOperations.reFillContainer(3, container);

	}

	@Test
	public void reFillContainershouldHandleExceptionWhenWaterContainerIsFull() {

		when(container.getWaterContainerCapacity()).thenReturn(11000);
		when(inputScanner.nextInt()).thenReturn(6000);

		containerOperations.reFillContainer(4, container);

	}

	@Test
	public void reFillContainershouldHandleExceptionWhenSugarContainerIsFull() {

		when(container.getSugarContainerCapacity()).thenReturn(6000);
		when(inputScanner.nextInt()).thenReturn(3000);

		containerOperations.reFillContainer(5, container);
	}
	

}
