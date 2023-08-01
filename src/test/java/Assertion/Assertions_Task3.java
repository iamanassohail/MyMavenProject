package Assertion;

import org.testng.Assert;

public class Assertions_Task3 {
    public static void asserting1(int totalpages, int totalrecords, String msg){
        Assert.assertEquals(totalpages, totalrecords, msg);
    }

    public static void asserting2(int employeeId, String msg){
        Assert.assertNotNull(employeeId, msg);
    }

    public static void asserting4_1(int statuscode, int actualcode){
        Assert.assertEquals(statuscode, actualcode);
    }

    public static void asserting4_2(String updatedname, String expected, String msg){
        Assert.assertEquals(updatedname, expected, msg);
    }

    public static void asserting5(int statuscode, int actualcode, String msg){
        Assert.assertEquals(statuscode, actualcode, msg);
    }
}
