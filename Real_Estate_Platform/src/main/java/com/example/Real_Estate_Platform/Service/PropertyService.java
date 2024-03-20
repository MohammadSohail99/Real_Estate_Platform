package com.example.Real_Estate_Platform.Service;

import com.example.Real_Estate_Platform.Conversion.Conversion;
import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Entity.Seller;
import com.example.Real_Estate_Platform.Model.PropertyModel;
import com.example.Real_Estate_Platform.Repository.BuyerRepo;
import com.example.Real_Estate_Platform.Repository.PropertyRepo;
import com.example.Real_Estate_Platform.Repository.SellerRepo;
import com.example.Real_Estate_Platform.ServiceImplementation.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService implements PropertyServiceImpl {
    @Autowired
    private PropertyRepo propertyRepo;
    @Autowired
    private SellerRepo sellerRepo;
    @Autowired
    private BuyerRepo buyerRepo;
    @Autowired
    private Conversion conversion;


    @Override
    public String saveProperty(PropertyModel propertyModel,int sid){
        if(propertyModel==null){
            return "Property not found";
        }
        else {
            Property property= conversion.propertyModel_entity(propertyModel);
            Seller seller=sellerRepo.getReferenceById(sid);
            property.setSeller(seller);
            propertyRepo.save(property);
            return "Property saved successfully";
        }
    }
    @Override
    public List<PropertyModel> getAllProperties(){
        List<Property> propertyList=propertyRepo.findAll();
        List<PropertyModel> propertyModelList=new ArrayList<>();

        propertyList.forEach(property -> {
            propertyModelList.add(conversion.entity_propertyModel(property));
        });
        return propertyModelList;
    }
    @Override
    public Property getPropertyById(int id) {
        Property property = propertyRepo.findAll().stream()
                .filter(i-> i.getId() == id).findFirst().get();
        return property;
    }
    @Override
    public void updateProperty(Property property) {
        Property existingProperty = propertyRepo.findById(property.getId()).orElse(null);
        if (existingProperty != null) {
            existingProperty.setTitle(property.getTitle());
            existingProperty.setAddress(property.getAddress());
            existingProperty.setPrice(property.getPrice());
            existingProperty.setArea(property.getArea());
            existingProperty.setSold(property.isSold());
            propertyRepo.save(existingProperty);
        }
        else {
            System.out.println("property not found for updating");
        }
    }
    @Override
    public void deletePropertyById(int id) {
        propertyRepo.deleteById(id);
    }
    @Override
    public double calculateTotalPriceForArea(double area, double price, int id, int bid) {
        Property property=propertyRepo.getReferenceById(id);
        property.setSold(true);
        property.setStatus("Sold");
        Buyer buyer=buyerRepo.getReferenceById(bid);
        property.setBuyer(buyer);
        propertyRepo.save(property);
        double totalPrice = area * price;
        return totalPrice;
    }
    @Override
    public List<Property> searchProperties(String address, double minPrice, double maxPrice, double minArea, double maxArea) {
        List<Property> propertyList = propertyRepo.findAll().stream()
                .filter(i -> i.getAddress().equalsIgnoreCase(address)
                        && i.getPrice() >= minPrice && i.getPrice() <= maxPrice
                        && i.getArea() >= minArea && i.getArea() <= maxArea)
                .toList();

        return propertyList;
    }
    @Override
    public List<Property> searchPropertyByLocation(String address) {
        List<Property> propertyList=propertyRepo.findAll().stream()
                .filter(i->i.getAddress().equalsIgnoreCase(address)).collect(Collectors.toList());
        return propertyList;
    }
    @Override
    public List<Property> getPropertiesBySellerId(int sellerId) {
        List<Property> allProperties = propertyRepo.findAll();
        List<Property> propertiesBySeller = allProperties.stream()
                .filter(property -> property.getSeller().getSid() == sellerId)
                .collect(Collectors.toList());
        return propertiesBySeller;
    }
    @Override
    public List<Buyer> getBuyersForProperty(int id) {
        List<Buyer> allBuyers = buyerRepo.findAll();
        List<Buyer> buyersForProperty = allBuyers.stream()
                .filter(buyer -> buyer.getPropertyList() != null && buyer.getPropertyList().stream().anyMatch(property -> property.getId() == id))
                .collect(Collectors.toList());
        return buyersForProperty;
    }
    @Override
    public double calculate(double area, double price, int propertyId) {
        return area*price;
    }
    @Override
    public List<Property> searchPropertyByArea(double minArea, double maxArea) {
        List<Property> propertyList = propertyRepo.findAll().stream()
                .filter(i -> i.getArea() >= minArea && i.getArea() <= maxArea)
                .toList();
        return propertyList;
    }
    @Override
    public List<Property> searchPropertyByPrice(double minPrice, double maxPrice) {
        List<Property> propertyList=propertyRepo.findAll().stream()
                .filter(i->i.getPrice()>=minPrice && i.getPrice()<=maxPrice)
                .collect(Collectors.toList());
        return propertyList;
    }
}
