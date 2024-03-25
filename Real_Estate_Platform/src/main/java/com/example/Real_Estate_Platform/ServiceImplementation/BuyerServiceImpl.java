package com.example.Real_Estate_Platform.ServiceImplementation;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Model.BuyerModel;

import java.util.List;

public interface BuyerServiceImpl {
    void registerBuyer(BuyerModel buyerModel);
    BuyerModel loginBuyer(String username,String password);

    Buyer getBuyerByName(String buyerName);

    List<Buyer> getBuyer(String buyerName);
}
