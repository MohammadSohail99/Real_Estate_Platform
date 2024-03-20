package com.example.Real_Estate_Platform.ServiceImplementation;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Model.BuyerModel;

public interface BuyerServiceImpl {
    void registerBuyer(BuyerModel buyerModel);
    BuyerModel loginBuyer(String username,String password);

    Buyer getBuyerById(int bid);
}
