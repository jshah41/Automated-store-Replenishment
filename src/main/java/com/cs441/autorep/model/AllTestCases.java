package com.cs441.autorep.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TCStockReportGeneration.class, TCViewInventory.class, TCRequestForRep.class, TCLogin.class, TCViewTranLogs.class })
public class AllTestCases {

}
