import by.training.task08xml.bean.Tariff;
import by.training.task08xml.service.exception.ServiceException;
import by.training.task08xml.service.parserimpl.DomParser;
import by.training.task08xml.service.parserimpl.SaxParser;
import by.training.task08xml.service.parserimpl.StaxParser;
import org.testng.annotations.Test;

public class Testing {
    SaxParser parser = new SaxParser();
    DomParser domParser = new DomParser();
    StaxParser staxParser = new StaxParser();
    private final static String PATH = "src/main/resources/data/tariffs.xml";

    @Test
    public void saxTest() throws ServiceException{
        for (Tariff tariff : parser.parse(PATH)) {
            System.out.println(tariff);
        }
    }

    @Test
    public void domTest() throws ServiceException {
        for (Tariff tariff : domParser.parse(PATH)) {
            System.out.println(tariff);
        }
    }

    @Test
    public void staxTest() throws ServiceException{
        for (Tariff tariff :staxParser.parse(PATH)) {
            System.out.println(tariff);
        }
    }
}
