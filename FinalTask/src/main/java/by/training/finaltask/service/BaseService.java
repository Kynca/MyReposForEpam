package by.training.finaltask.service;

import by.training.finaltask.service.transaction.Transaction;

public abstract class BaseService {
    protected Transaction transaction = null;

    public void setTransaction(Transaction transaction){this.transaction = transaction;}
}
