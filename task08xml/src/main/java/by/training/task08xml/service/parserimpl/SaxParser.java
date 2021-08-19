package by.training.task08xml.service.parserimpl;

import by.training.task08xml.bean.Tariff;
import by.training.task08xml.bean.TariffWithMinutes;
import by.training.task08xml.bean.TariffWithoutMinutes;
import by.training.task08xml.service.Parser;
import by.training.task08xml.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxParser implements Parser {

    private static final Logger debugLog = LogManager.getLogger("debugLog");
    private List<Tariff> tariffs;
    private String[] dopInfo = new String[2];

    @Override
    public List<Tariff> parse(String filename) throws ServiceException {
        tariffs = new ArrayList<>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory xsdFactory = SchemaFactory.newInstance(constant);
            Schema schema = xsdFactory.newSchema(new File("F:/Tasks/task08xml/src/main/resources/data/tariffs.xsd"));
            // set schema
            factory.setNamespaceAware(true);
            factory.setValidating(false);
            factory.setSchema(schema);
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(filename), defaultHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
           throw new ServiceException(e);
        }
        return tariffs;
    }

    DefaultHandler defaultHandler = new DefaultHandler() {
        private String current = null;
        private Tariff tariff = null;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("tariffWithoutMinutes") || qName.equals("tariffWithMinutes")) {
                debugLog.debug(qName);
                tariff = new Tariff();
            } else {
                current = qName;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String temp = new String(ch, start, length);
            if (current != null) {
                switch (current) {
                    case "name":
                        tariff.setName(temp);
                        break;
                    case "operatorName":
                        tariff.setOperatorName(temp);
                        break;
                    case "payroll":
                        tariff.setPayroll(Double.parseDouble(temp));
                        break;
                    case "withinNetwork", "freeWithin":
                        debugLog.debug(current + " = " + temp);
                        dopInfo[0] = temp;
                        break;
                    case "outOfNetwork", "freeOut":
                        debugLog.debug(current + " = " + temp);
                        dopInfo[1] = temp;
                        break;
                    case "freeInternet":
                        tariff.getParameters().setFreeInternet(temp);
                        break;
                    case "wifiHosting":
                        tariff.getParameters().setWifiHosting(temp);
                        break;
                    case "appearanceDate":
                        tariff.setDate(temp);
                        break;
                    case "favorite":
                        tariff.getParameters().setFavorite(Boolean.parseBoolean(temp));
                        break;
                    case "accumulation":
                        tariff.getParameters().setAccumulation(Boolean.parseBoolean(temp));
                        break;
                    case "subscription":
                        tariff.getParameters().setSubscription(temp);
                        break;
                    default:
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("tariffWithoutMinutes")) {
                TariffWithoutMinutes newTariff = new TariffWithoutMinutes(tariff);
                newTariff.setWithingNetwork(Double.parseDouble(dopInfo[0]));
                newTariff.setOutOfNetwork(Double.parseDouble(dopInfo[1]));
                tariffs.add(newTariff);
            }else if(qName.equals("tariffWithMinutes")){
                TariffWithMinutes newTariff = new TariffWithMinutes(tariff);
                newTariff.setFreeWithin(dopInfo[0]);
                newTariff.setFreeOut(dopInfo[1]);
                tariffs.add(newTariff);
            }
            current = null;
        }
    };
}
