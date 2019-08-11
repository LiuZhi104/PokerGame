package sales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SalesApp {
	private SalesDao salesDao;
	private SalesReportDao salesReportDao;
	public void generateSalesActivityReport(String salesId, int maxRow, boolean isNatTrade, boolean isSupervisor) {
		if (salesId == null) {
			return;
		}
		List<SalesReportData> filteredReportDataList = new ArrayList<SalesReportData>();
		Sales sales = salesDao.getSalesBySalesId(salesId);

		if (checkData(sales)) return;

		List<SalesReportData> reportDataList = salesReportDao.getReportData(sales);

		filterReportData(isSupervisor, filteredReportDataList, reportDataList);

		List<SalesReportData> tempList = getSalesReportData(maxRow, reportDataList);
		filteredReportDataList = tempList;

		List<String> headers = getStrings(isNatTrade);

		SalesActivityReport report = this.generateReport(headers, reportDataList);

		uploadDocument(report);

	}

	public void uploadDocument(SalesActivityReport report) {
		EcmService ecmService = new EcmService();
		ecmService.uploadDocument(report.toXml());
	}

	public List<String> getStrings(boolean isNatTrade) {
		List<String> headers;
		if (isNatTrade) {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Time");
		} else {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time");
		}
		return headers;
	}

	public List<SalesReportData> getSalesReportData(int maxRow, List<SalesReportData> reportDataList) {
		List<SalesReportData> tempList = new ArrayList<SalesReportData>();
		for (int i=0; i < reportDataList.size() || i < maxRow; i++) {
			tempList.add(reportDataList.get(i));
		}
		return tempList;
	}

	public void filterReportData(boolean isSupervisor, List<SalesReportData> filteredReportDataList, List<SalesReportData> reportDataList) {
		for (SalesReportData data : reportDataList) {
			if ("SalesActivity".equalsIgnoreCase(data.getType())) {
				if (data.isConfidential()) {
					if (isSupervisor) {
						filteredReportDataList.add(data);
					}
				}else {
					filteredReportDataList.add(data);
				}
			}
		}
	}

	public boolean checkData(Sales sales) {
		Date today = new Date();
		if (today.after(sales.getEffectiveTo())
				|| today.before(sales.getEffectiveFrom())){
			return true;
		}
		return false;
	}

	public SalesActivityReport generateReport(List<String> headers, List<SalesReportData> reportDataList) {
		// TODO Auto-generated method stub
		return null;
	}

}
