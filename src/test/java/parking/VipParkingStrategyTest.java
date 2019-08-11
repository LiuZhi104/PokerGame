package parking;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {
    public static final String CAR_NAME_AA111 = "AA111";
    @Mock
    private CarDao carDao;

    @InjectMocks
    private VipParkingStrategy injectVipParkingStrategy = spy(new VipParkingStrategy());

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */

	    //given
        VipParkingStrategy spyVipstrategy = spy(new VipParkingStrategy());
        ParkingLot parkingLot = new ParkingLot("xx lot",1);
        parkingLot.getParkedCars().add(createMockCar("owen grr"));
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot);
        Car car = new Car("AA");
//       when(spyVipstrategy.isAllowOverPark(car)).thenReturn(true);

        //when
//        Car car = mock(Car.class);
//        when(car.getName()).thenReturn("AA");
//       when(carDao.isVip(car.getName())).thenReturn(true);
        spyVipstrategy.park(parkingLots,car);

        //
        verify(spyVipstrategy,times(1)).createReceipt(parkingLot,car);
    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        //given
        VipParkingStrategy spyVipStrategy = spy(new VipParkingStrategy());
        ParkingLot parkingLot = new ParkingLot("xx lot",1);
        parkingLot.getParkedCars().add(createMockCar("bmw"));
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot);
        Car car = createMockCar("gtr");
        //when
        doReturn(false).when(spyVipStrategy).isAllowOverPark(car);
        spyVipStrategy.park(parkingLots,car);

        //then
        verify(spyVipStrategy,times(0)).createReceipt(parkingLot,car);
        verify(spyVipStrategy,times(1)).createNoSpaceReceipt(car);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not JMockit) and @InjectMocks
         */
        //given
        ParkingLot parkingLot = new ParkingLot("xx lot",1);
        parkingLot.getParkedCars().add(createMockCar("bmw"));
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot);
        Car car = new Car("AAA");

        //when
        when(carDao.isVip(any())).thenReturn(true);
        injectVipParkingStrategy.park(parkingLots,car);
        //then

        verify(injectVipParkingStrategy,times(1)).createReceipt(parkingLot,car);

    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not JMockit) and @InjectMocks
         */


    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not JMockit) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not JMockit) and @InjectMocks
         */
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
