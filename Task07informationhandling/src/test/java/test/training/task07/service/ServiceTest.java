package test.training.task07.service;

import by.training.task07.dao.FileOperations;
import by.training.task07.service.actionimpl.ServiceActionImpl;
import by.training.task07.service.intepreterimpl.RpnTransformer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

public class ServiceTest {
    final String PATH ="F:\\Tasks\\Task07informationhandling\\src\\test\\resources\\";
    FileOperations operations = new FileOperations();
    ServiceActionImpl action = new ServiceActionImpl();
    RpnTransformer transformer = new RpnTransformer();

    @DataProvider(name = "data for parser")
    public Object[][] parserData() {
        return new Object[][]{
                {PATH + "test1.txt"}
        };
    }

    @Test(description = "parser test", dataProvider = "data for parser")
    public void parseAndCollectTest(String path) throws Exception {
        action.parseText(path);
        String result = operations.readFile(path);
        result = result.replaceAll(" {2,}","\t");
        assertEquals(action.collectText(), result);
    }

    @DataProvider(name = "rpn data")
    public Object[][] parseToCalcExpression(){
        return new Object[][]{
                {"30>>>3", 3},
                {"~6&9|(3&4)", 9},
                {"(8^5|1&2<<(2|5>>2&71))|1200", 1213},
                {"~12^1", -14}
        };
    }

    @Test(description = "testing parsing expression to result", dataProvider = "rpn data")
    public void parseToRpnTest(String expression, int result){
        assertEquals(transformer.calculateExp(expression), result);
    }

}
