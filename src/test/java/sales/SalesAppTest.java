package sales;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class SalesAppTest {

	@Mock
	private SalesReportDao salesReportDao;

	@Mock
	private SalesDao salesDao;

	@InjectMocks
	private SalesApp injectSalesApp = spy(new SalesApp());
	@Test
	public void testcheckData(){
		//given
		Sales sales = mock(Sales.class);
		SalesApp salesApp = new SalesApp();

		//when
		when(sales.getEffectiveFrom()).thenReturn(new Date(new Date().getTime() - 24*60*60*1000));
		when(sales.getEffectiveTo()).thenReturn(new Date(new Date().getTime() + 24*60*60*1000));
		Boolean result = salesApp.checkData(sales);

		//then
		Assert.assertEquals(false,result);
	}
	@Test
	public void testfilterReportData(){
		//given
		Sales sales = mock(Sales.class);
		SalesReportData salesReportData = mock(SalesReportData.class);
		List<SalesReportData> salesReportDataList = new ArrayList<>();
		List<SalesReportData> filteredReportDataList = new ArrayList<SalesReportData>();

		//when
		when(salesReportData.getType()).thenReturn("SalesActivity");
		when(salesReportData.isConfidential()).thenReturn(true);
		salesReportDataList.add(salesReportData);
		when(salesReportDao.getReportData(sales)).thenReturn(salesReportDataList);
		injectSalesApp.filterReportData(true,filteredReportDataList,salesReportDao.getReportData(sales));

		//than
		Assert.assertEquals(1,filteredReportDataList.size());
		Assert.assertEquals("SalesActivity",filteredReportDataList.get(0).getType());
	}

	@Test
	public void testGetSalesReportData(){
		//given
		Sales sales = mock(Sales.class);
		SalesReportData salesReportData = mock(SalesReportData.class);
		List<SalesReportData> salesReportDataList = new ArrayList<>();

		//when
		when(salesReportData.getType()).thenReturn("SalesActivity");
		when(salesReportData.isConfidential()).thenReturn(true);
		when(salesReportDao.getReportData(sales)).thenReturn(salesReportDataList);
		salesReportDataList.add(salesReportData);
		List<SalesReportData> tempList = injectSalesApp.getSalesReportData(1, salesReportDao.getReportData(sales));

		//then
		Assert.assertEquals(1,tempList.size());
		Assert.assertEquals("SalesActivity",tempList.get(0).getType());
	}

	@Test
	public void testGetStrings(){
		//given//when
		List<String> headers =  injectSalesApp.getStrings(true);

		//then
		Assert.assertEquals("Time",headers.get(3));
	}

	@Test
	public void testGenerateReport() {
		//given
		Sales sales = mock(Sales.class);
		SalesReportData salesReportData = mock(SalesReportData.class);
		List<SalesReportData> salesReportDataList = new ArrayList<>();
		List<SalesReportData> filteredReportDataList = new ArrayList<SalesReportData>();
		SalesActivityReport salesActivityReport = new SalesActivityReport();


		//when
		when(salesDao.getSalesBySalesId("salesId")).thenReturn(sales);
		when(sales.getEffectiveFrom()).thenReturn(new Date(new Date().getTime() - 24*60*60*1000));
		when(sales.getEffectiveTo()).thenReturn(new Date(new Date().getTime() + 24*60*60*1000));
		when(salesReportData.getType()).thenReturn("SalesActivity");
		when(salesReportData.isConfidential()).thenReturn(true);
		salesReportDataList.add(salesReportData);
		when(injectSalesApp.generateReport(any(), any())).thenReturn(salesActivityReport);
		when(salesReportDao.getReportData(sales)).thenReturn(salesReportDataList);
		injectSalesApp.generateSalesActivityReport("salesId", 1,true,true);

		//then
		verify(injectSalesApp,times(1)).checkData(sales);
		verify(injectSalesApp,times(1)).getSalesReportData(1,salesReportDao.getReportData(sales));
		verify(injectSalesApp,times(1)).getStrings(true);
		verify(injectSalesApp,times(1)).uploadDocument(salesActivityReport);

	}
}
