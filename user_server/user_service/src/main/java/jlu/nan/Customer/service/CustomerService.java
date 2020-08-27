package jlu.nan.Customer.service;

public interface CustomerService {
    Boolean checkData(String data, Integer type);

    void sendCode(String tel);
}
