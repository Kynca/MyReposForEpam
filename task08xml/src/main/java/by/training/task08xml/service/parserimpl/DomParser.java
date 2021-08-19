package by.training.task08xml.service.parserimpl;

import by.training.task08xml.bean.Tariff;
import by.training.task08xml.bean.TariffWithMinutes;
import by.training.task08xml.bean.TariffWithoutMinutes;
import by.training.task08xml.service.Parser;
import by.training.task08xml.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements Parser {
    private List<Tariff> tariffs;
    private DocumentBuilder docBuilder;
    private final Logger debugLog = LogManager.getLogger("debugLog");

    public List<Tariff> parse(String filename) throws ServiceException {
        this.tariffs = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory xsdFactory = SchemaFactory.newInstance(constant);
            Schema schema = xsdFactory.newSchema(new File("F:/Tasks/task08xml/src/main/resources/data/tariffs.xsd"));

            // set schema
            factory.setNamespaceAware(true);
            factory.setValidating(false);
            factory.setSchema(schema);
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        Document doc;
        try {
            doc = docBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList tariffsList = root.getElementsByTagName("tariffWithMinutes");
            for (int i = 0; i < tariffsList.getLength(); i++) {
                Element tariffElement = (Element) tariffsList.item(i);
                TariffWithMinutes tariffWithMinutes = new TariffWithMinutes();
                Tariff tariff = buildTariff(tariffElement, tariffWithMinutes);
                tariffs.add(tariff);
            }
            tariffsList = root.getElementsByTagName("tariffWithoutMinutes");
            for (int i = 0; i < tariffsList.getLength(); i++) {
                Element tariffElement = (Element) tariffsList.item(i);
                TariffWithoutMinutes tariffWithMinutes = new TariffWithoutMinutes();
                Tariff tariff = buildTariff(tariffElement, tariffWithMinutes);
                tariffs.add(tariff);
            }
        } catch (SAXException e) {
            throw new ServiceException("parse exception", e);
        } catch (IOException e) {
            throw new ServiceException("file not found");
        }
        return tariffs;
    }

    private Tariff buildTariff(Element element, Tariff tariff) throws ServiceException{
        String tempString;
        double tempDouble;
            tariff.setName(getElementTextContent(element, "name"));
            tariff.setOperatorName(getElementTextContent(element, "operatorName"));
            tempDouble = Double.parseDouble(getElementTextContent(element, "payroll"));
            tariff.setPayroll(tempDouble);
            tariff.setDate(getElementTextContent(element, "appearanceDate"));
            Tariff.TariffParameters parameters = tariff.getParameters();
            parameters.setFreeInternet(getElementTextContent(element, "freeInternet"));
            parameters.setWifiHosting(getElementTextContent(element, "wifiHosting"));
            NodeList bonuses = element.getElementsByTagName("bonuses");
            if (bonuses.getLength() > 0) {
                tempString = getElementTextContent(element, "favorite");
                if(!tempString.equals("nothing")) {
                    parameters.setFavorite(Boolean.parseBoolean(tempString));
                }
                tempString = getElementTextContent(element, "subscription");
                if(!tempString.equals("nothing")){
                    parameters.setSubscription(tempString);
                }
                tempString = getElementTextContent(element, "accumulation");
                if(!tempString.equals("nothing")){
                    parameters.setAccumulation(Boolean.parseBoolean(tempString));
                }
//                debugLog.debug(tempString);
            }
        if (tariff instanceof TariffWithMinutes) {
            ((TariffWithMinutes) tariff).setFreeOut(getElementTextContent(element, "freeOut"));
            ((TariffWithMinutes) tariff).setFreeWithin(getElementTextContent(element, "freeWithin"));
        } else if (tariff instanceof TariffWithoutMinutes) {
            tempDouble = Double.parseDouble(getElementTextContent(element, "withinNetwork"));
            ((TariffWithoutMinutes) tariff).setWithingNetwork(tempDouble);
            debugLog.debug(tariff.getName());
            tempDouble = Double.parseDouble(getElementTextContent(element, "outOfNetwork"));
            ((TariffWithoutMinutes) tariff).setOutOfNetwork(tempDouble);
        }
            return tariff;
    }

    private String getElementTextContent(Element element, String elementName) throws ServiceException {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        if(node == null){
            return "nothing";
        }
        String text = node.getTextContent();
        return text;
    }
}
