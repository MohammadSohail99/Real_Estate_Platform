package com.example.Real_Estate_Platform.ServiceImplementation;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Model.PropertyModel;

import java.util.List;

public interface PropertyServiceImpl {
    String saveProperty(PropertyModel propertyModel, int sid);

    List<PropertyModel> getAllProperties();

    Property getPropertyById(int id);

    void updateProperty(Property property);

    void deletePropertyById(int id);

    double calculateTotalPriceForArea(double area, double price, int id, int bid);

    List<Property> searchProperties(String address, double minPrice, double maxPrice, double minArea, double maxArea);

    List<Property> searchPropertyByLocation(String address);

    List<Property> getPropertiesBySellerId(int sellerId);

    List<Buyer> getBuyersForProperty(int id);

    double calculate(double area, double price, int propertyId);

    List<Property> searchPropertyByArea(double minArea, double maxArea);

    List<Property> searchPropertyByPrice(double minPrice, double maxPrice);
}
