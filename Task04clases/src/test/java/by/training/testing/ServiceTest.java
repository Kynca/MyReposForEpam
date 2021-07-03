package by.training.testing;

import by.training.classes04.bean.Bank;
import by.training.classes04.bean.Bill;
import by.training.classes04.bean.Client;
import by.training.classes04.service.impl.BankOperationsImpl;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class ServiceTest {
    BankOperationsImpl impl= new BankOperationsImpl();
    Bank bank=Bank.getInstance();

    @Test(description = "initialise testing")
    public void iniTest(){
        impl.initialise();
        boolean result=false;
        if(bank.getSizeOfClient()>1)result=true;
        assertEquals(result,true);
    }


    @DataProvider(name = "ScenarioCheck for Client creator")
    public Object[][] creators(){
        return new Object[][]{
                {"Man,pro221,2,100,200",true},
                {"Fon,ilo221,1,-2200",true},
                {"Skon,lerr12,3,100,200,-62.42",true},
                {"A,pro2211,2,100,200",false},
                {"An,scon312,2,100,200",false},
                {"Man,pro221,2,100,200",false},
                {"Hill,smork1,6,100,200,20,30,1,4.21",true},
                {"Sol1,proza21,2,100,200",false},
                {"Man1,pro223,2,100,200,300",true}
        };
    }

    @Test(description = "test with check all scenario", dataProvider = "ScenarioCheck for Client creator")
    public void creatorTest(String info,boolean result){
        assertEquals(impl.createUser(info),result);
    }

    @DataProvider(name="Scenario for all sum")
    public Object[][] billSum(){
       return new Object[][] {
               {new Bill[]{new Bill(100),new Bill(300),new Bill(-1000),new Bill( 300)},-300},
               {new Bill[]{new Bill(20)},20},
               {new Bill[]{new Bill(-102),new Bill(301),new Bill(-31.2),new Bill( 300),
                       new Bill(-21.2),},446.6}
       };
    }

    @Test(description = "test for sum of all bills", dataProvider = "Scenario for all sum")
    public void allSum(Bill[] bills,double result){
        bank.getListOfBills().clear();
        for(Bill bill:bills){
            bank.addBill(bill);
        }
        assertEquals(impl.sumBills(1),result);
    }

    @DataProvider(name="Scenario for positive sum")
    public Object[][] positiveSum(){
        return new Object[][] {
                {new Bill[]{new Bill(100),new Bill(300),new Bill(-1000),new Bill( 300)},700},
                {new Bill[]{new Bill(20)},20},
                {new Bill[]{new Bill(-102),new Bill(301),new Bill(-31.2),new Bill( 300),
                        new Bill(-21.2),},601}
        };
    }

    @Test(description = "test for sum of positive bills", dataProvider = "Scenario for positive sum")
    public void positiveSum(Bill[] bills,double result){
        bank.getListOfBills().clear();
        for(Bill bill:bills){
            bank.addBill(bill);
        }
        assertEquals(impl.sumBills(3),result);
    }

    @DataProvider(name="Scenario for negative sum")
    public Object[][] negativeSum(){
        return new Object[][] {
                {new Bill[]{new Bill(100),new Bill(300),new Bill(-1000),new Bill( 300)},-1000},
                {new Bill[]{new Bill(20)},0},
                {new Bill[]{new Bill(-102),new Bill(301),new Bill(-31.2),new Bill( 300),
                        new Bill(-21.2),},-154.4}
        };
    }

    @Test(description = "test for sum of negative bills", dataProvider = "Scenario for negative sum")
    public void negativeSum(Bill[] bills,double result){
        bank.getListOfBills().clear();
        for(Bill bill:bills){
            bank.addBill(bill);
        }
        assertEquals(impl.sumBills(2),result,0.01);
    }

    @DataProvider(name="Scenario for client sum")
    public Object[][] clientBills(){
        Client client=new Client("name","pop123");
        client.addBill(new Bill(300));
        client.addBill(new Bill(-100));
        bank.addClient(client);
        client=new Client("name1","123pop");
        Bill bill=new Bill(-300);
        bill.block();
        client.addBill(bill);
        client.addBill(new Bill(100));
        bank.addClient(client);
        return new Object[][]{
                {"pop123",200},
                {"123pop",100},
                {"1231231",Integer.MIN_VALUE}
        };
    }

    @Test(description = "testing can impl calculate sum of client bill",dataProvider ="Scenario for client sum" )
    public void clientSum(String pass,double sum){
        assertEquals(impl.sumClientBill(pass),sum);
    }

}
