package com.yash.cvm.TeaCoffeeVendingMachine;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.yash.cvm.model.BlackCoffee;
import com.yash.cvm.model.BlackTea;
import com.yash.cvm.model.Coffee;
import com.yash.cvm.model.InputScanner;
import com.yash.cvm.model.Container;
import com.yash.cvm.model.Tea;
import com.yash.cvm.service.ContainerOperation;
import com.yash.cvm.service.GenerateReport;




@RunWith(MockitoJUnitRunner.class)
public class OrderTest {

	@InjectMocks
	private Order order;

	@Mock
	private Container container;
	

	@Mock
	private ContainerOperation containerOperations;

	

	@Mock
	private InputScanner inputScanner;

	@Mock
	private GenerateReport generateReportImpl;

	@Mock
	private Appender appenderMock;
	
	
	@Mock
	private Tea tea;
	
	@Mock
	private BlackTea blackTea;
	
	@Mock
	private Coffee coffee;
	
	@Mock
	private BlackCoffee blackCoffee;
	
	@Before 
	public void setupAppender(){
		
		Logger.getRootLogger().addAppender(appenderMock);
	}
	
	@After
	public void removeAppender(){
		Logger.getRootLogger().removeAppender(appenderMock);
	}
	
	
	@Test
	public void getMenuShouldAllowToOrderTea() {

		Container conatiner1 = new Container();

		when(containerOperations.checkAvailabiltyOfRequiredMaterial("Tea", 1, conatiner1)).thenReturn(true);
		
		doNothing().when(containerOperations).adjustContainerQuantity("Tea", 1, conatiner1);
		when(inputScanner.nextInt()).thenReturn(1).thenReturn(1);
		
		when(tea.getTeaPrice()).thenReturn(10.0);
        
		order.startMenu(conatiner1);
		assertTrue(containerOperations.checkAvailabiltyOfRequiredMaterial("Tea", 1, conatiner1));
		verify(appenderMock,times(7)).doAppend((LoggingEvent) Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToOrderBlackTea() {

		Container conatiner1 = new Container();

		when(containerOperations.checkAvailabiltyOfRequiredMaterial("Black Tea", 1, conatiner1)).thenReturn(true);
		
		doNothing().when(containerOperations).adjustContainerQuantity("Black Tea", 1, conatiner1);
		when(inputScanner.nextInt()).thenReturn(2).thenReturn(1);

		when(blackTea.getBlackTeaPrice()).thenReturn(5.0);
		
		order.startMenu(conatiner1);
        assertTrue(containerOperations.checkAvailabiltyOfRequiredMaterial("Black Tea", 1, conatiner1));
		verify(appenderMock,times(7)).doAppend((LoggingEvent) Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToOrderCoffee() {

		Container container1 = new Container();

		when(containerOperations.checkAvailabiltyOfRequiredMaterial("Coffee", 1, container1)).thenReturn(true);
		
		doNothing().when(containerOperations).adjustContainerQuantity("Coffee", 1, container1);
		when(inputScanner.nextInt()).thenReturn(3).thenReturn(1);

		when(coffee.getCoffeePrice()).thenReturn(15.0);
		order.startMenu(container1);

		assertTrue(containerOperations.checkAvailabiltyOfRequiredMaterial("Coffee", 1, container1));
		verify(appenderMock,times(7)).doAppend((LoggingEvent) Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToOrderBlackCoffee() {

		Container container1 = new Container();

		when(containerOperations.checkAvailabiltyOfRequiredMaterial("Black Coffee", 1, container1)).thenReturn(true);
		
		doNothing().when(containerOperations).adjustContainerQuantity("Black Coffee", 1, container1);
		when(inputScanner.nextInt()).thenReturn(4).thenReturn(1);

		when(blackCoffee.getCoffeePrice()).thenReturn(10.0);
		order.startMenu(container1);

		assertTrue(containerOperations.checkAvailabiltyOfRequiredMaterial("Black Coffee", 1, container1));
		verify(appenderMock,times(7)).doAppend((LoggingEvent) Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToOrderResetContainers() {

		Container container1 = new Container();

		doNothing().when(containerOperations).resetContainer(container1);
		when(inputScanner.nextInt()).thenReturn(5).thenReturn(1);

		order.startMenu(container1);

		verify(containerOperations).resetContainer(container1);
		verify(appenderMock,times(4)).doAppend((LoggingEvent) Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToOrderRefillContainers() {

		Container container1 = new Container();

		container1.setTeaContainerCapacity(1000);

		doNothing().when(containerOperations).reFillContainer(1, container1);
		when(inputScanner.nextInt()).thenReturn(6).thenReturn(1).thenReturn(1);

		order.startMenu(container1);

		verify(containerOperations).reFillContainer(1, container1);
		verify(appenderMock,times(7)).doAppend((LoggingEvent) Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToCheckContainersStatus() {

		Container container1 = new Container();

		doNothing().when(containerOperations).checkContainerStatus(container1);
		when(inputScanner.nextInt()).thenReturn(7).thenReturn(1);

		order.startMenu(container1);

		verify(containerOperations).checkContainerStatus(container1);
		verify(appenderMock,times(4)).doAppend((LoggingEvent) Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToGenerateReport() {

		Container container1 = new Container();

		@SuppressWarnings("rawtypes")
		HashMap<String, List> totalSalePercontainer = new HashMap<String, List>();

		doNothing().when(generateReportImpl).prepareReport(container1, totalSalePercontainer, 0, 0.0);
		when(inputScanner.nextInt()).thenReturn(8).thenReturn(1);

		order.startMenu(container1);
		
		verify(appenderMock,times(5)).doAppend((LoggingEvent) Mockito.anyObject());
	}

	@Test 
	public void getMenuShouldHandleExceptionWhenAvalabilityIsFalse() {

		Container container1 = new Container();

		when(containerOperations.checkAvailabiltyOfRequiredMaterial("Tea", 10, container1)).thenReturn(false);

		when(inputScanner.nextInt()).thenReturn(1).thenReturn(10).thenReturn(1);

		order.startMenu(container1);

		assertFalse(containerOperations.checkAvailabiltyOfRequiredMaterial("Tea", 10, container1));
		
		verify(appenderMock,times(7)).doAppend((LoggingEvent) Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAskForMenuWhenInputIsInvalid() {

		Container container1 = new Container();

		when(inputScanner.nextInt()).thenReturn(10).thenReturn(1);

		order.startMenu(container1);
		
		verify(appenderMock, times(5)).doAppend((LoggingEvent) Mockito.anyObject());
	}

	@Test
	public void getMenuShouldReturnExtraAmountWhenInserted() {

		Container container1 = new Container();
		
		when(inputScanner.nextInt()).thenReturn(1).thenReturn(1).thenReturn(12).thenReturn(1);
		when(containerOperations.checkAvailabiltyOfRequiredMaterial("Tea", 1, container1)).thenReturn(true);
		
		doNothing().when(containerOperations).adjustContainerQuantity("Tea", 1, container1);
		
		order.startMenu(container1);
		verify(appenderMock,times(7)).doAppend((LoggingEvent) Mockito.anyObject());
	}
	

	
	@Test
	public void shouldExitFromSystemorder(){
		
		Container container1 = new Container();
		when(inputScanner.nextInt()).thenReturn(9);
		
		order.startMenu(container1);
		
		verify(appenderMock,times(5)).doAppend((LoggingEvent) Mockito.anyObject());
		
		
	}

}
