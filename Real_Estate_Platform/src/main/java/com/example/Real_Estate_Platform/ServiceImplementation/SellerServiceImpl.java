package com.example.Real_Estate_Platform.ServiceImplementation;

import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Entity.Seller;
import com.example.Real_Estate_Platform.Model.SellerModel;

import java.util.List;

public interface SellerServiceImpl {
    Seller registerSeller(SellerModel sellerModel, String username);

    SellerModel loginSeller(String username, String password);

    List<Seller> getAllSellers(int mid);

    List<Property> getProperties(int sellerId);

    List<Seller> getSeller();

    Seller getSellerById(int sid);
}
