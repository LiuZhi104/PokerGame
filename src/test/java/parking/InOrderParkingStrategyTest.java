package parking;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static parking.ParkingStrategy.NO_PARKING_LOT;

public class InOrderParkingStrategyTest {

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */
	    //given
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt receipt = new Receipt();
        receipt.setCarName("BMW");
        receipt.setParkingLotName("vvzz lot");
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        //when
        when(parkingLot.getName()).thenReturn("vvzz lot");
        when(car.getName()).thenReturn("BMW");
        Receipt mockReceipt = inOrderParkingStrategy.createReceipt(parkingLot,car);

        //then
        Assert.assertEquals(receipt.getCarName(),mockReceipt.getCarName());
        Assert.assertEquals(receipt.getParkingLotName(),mockReceipt.getParkingLotName());





    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        //given
        Receipt receipt = new Receipt();
        receipt.setCarName("bmw");
        receipt.setParkingLotName(NO_PARKING_LOT);
        Car car = mock(Car.class);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot("xx lot",0));

        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        //when
        when(car.getName()).thenReturn("bmw");
        Receipt noSpaceRecipt = inOrderParkingStrategy.park(parkingLots,car);

        //then
        Assert.assertEquals(receipt.getCarName(),noSpaceRecipt.getCarName());
        Assert.assertEquals(receipt.getParkingLotName(),noSpaceRecipt.getParkingLotName());



    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */


    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */

    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
