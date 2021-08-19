package by.training.task08xml.controller.console.impl;

import by.training.task08xml.bean.Tariff;
import by.training.task08xml.controller.console.Command;
import by.training.task08xml.service.Parser;
import by.training.task08xml.service.ServiceFactory;
import by.training.task08xml.service.exception.ServiceException;
import by.training.task08xml.service.parserimpl.DomParser;
import by.training.task08xml.service.parserimpl.SaxParser;
import by.training.task08xml.service.parserimpl.StaxParser;

import java.util.List;

public class GetInfo implements Command {

    @Override
    public List<Tariff> execute(String request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        List<Tariff> tariffs = null;
        Parser parser;
        String[] data = request.split("~");
        if(Integer.parseInt(data[1]) == 0){
             parser = serviceFactory.getDomParser();
        }else if(Integer.parseInt(data[1]) == 1){
             parser = serviceFactory.getSaxParser();
        }else {
             parser = serviceFactory.getStaxParser();
        }
        try {
            tariffs = parser.parse(data[2]);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            //logg
        }catch (NumberFormatException e){
            //logg
        }
        return tariffs;
    }
}
