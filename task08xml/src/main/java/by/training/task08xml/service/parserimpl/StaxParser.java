package by.training.task08xml.service.parserimpl;

import by.training.task08xml.bean.Tariff;
import by.training.task08xml.bean.TariffWithMinutes;
import by.training.task08xml.bean.TariffWithoutMinutes;
import by.training.task08xml.service.Parser;
import by.training.task08xml.service.exception.ServiceException;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StaxParser implements Parser {
    private List<Tariff> tariffs;
    private String[] dopInfo = new String[2];

    @Override
    public List<Tariff> parse(String filename) throws ServiceException {
        tariffs = new ArrayList<>();
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader xmlReader = factory.createXMLStreamReader(new FileInputStream(filename));
            String tagName;
            while (xmlReader.hasNext()) {
                xmlReader.next();
                if (xmlReader.isStartElement()) {
                    tagName = xmlReader.getLocalName();
                    if (tagName.equals("tariffWithoutMinutes")) {
                        Tariff tariff = buildTariff(xmlReader);
                        TariffWithoutMinutes withoutMinutes = new TariffWithoutMinutes(tariff);
                        withoutMinutes.setWithingNetwork(Double.parseDouble(dopInfo[0]));
                        withoutMinutes.setOutOfNetwork(Double.parseDouble(dopInfo[1]));
                        tariffs.add(withoutMinutes);
                    } else if (tagName.equals("tariffWithMinutes")) {
                        Tariff tariff = buildTariff(xmlReader);
                        TariffWithMinutes withMinutes = new TariffWithMinutes(tariff);
                        withMinutes.setFreeWithin(dopInfo[0]);
                        withMinutes.setFreeOut(dopInfo[1]);
                        tariffs.add(withMinutes);
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new ServiceException(e);
        } catch (FileNotFoundException e) {
            throw new ServiceException("file not found");
        }
        return tariffs;
    }

    private Tariff buildTariff(XMLStreamReader reader) throws XMLStreamException {
        Tariff tariff = new Tariff();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (name) {
                        case "name":
                            tariff.setName(getXMLText(reader));
                            break;
                        case "operatorName":
                            name = getXMLText(reader);
                            tariff.setOperatorName(name);
                            break;
                        case "payroll":
                            tariff.setPayroll(Double.parseDouble(getXMLText(reader)));
                            break;
                        case "appearanceDate":
                            tariff.setDate(getXMLText(reader));
                            break;
                        case "freeInternet":
                            tariff.getParameters().setFreeInternet(getXMLText(reader));
                            break;
                        case "wifiHosting":
                            tariff.getParameters().setWifiHosting(getXMLText(reader));
                            break;
                        case "favorite":
                            tariff.getParameters().setFavorite(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case "subscription":
                            tariff.getParameters().setSubscription(getXMLText(reader));
                            break;
                        case "accumulation":
                            tariff.getParameters().setAccumulation(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case "withinNetwork", "freeWithin":
                            dopInfo[0] = getXMLText(reader);
                            break;
                        case "outOfNetwork", "freeOut":
                            dopInfo[1] = getXMLText(reader);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name.equals("tariffWithoutMinutes") || name.equals("tariffWithMinutes")) {
                        return tariff;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Student");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        try {
            String text = null;
            if (reader.hasNext()) {
                reader.next();
                text = reader.getText();
            }
            return text;
        }catch (IllegalStateException e){
            return "false";
        }
    }
}
