package by.training.task08xml.service;


import by.training.task08xml.bean.Tariff;
import by.training.task08xml.service.exception.ServiceException;

import java.util.List;

public interface Parser{
    public List<Tariff> parse(String filename) throws ServiceException;
}
