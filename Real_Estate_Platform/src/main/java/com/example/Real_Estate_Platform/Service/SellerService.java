package com.example.Real_Estate_Platform.Service;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Mediator;
import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Entity.Seller;
import com.example.Real_Estate_Platform.Model.MediatorModel;
import com.example.Real_Estate_Platform.Model.PropertyModel;
import com.example.Real_Estate_Platform.Model.SellerModel;
import com.example.Real_Estate_Platform.Repository.BuyerRepo;
import com.example.Real_Estate_Platform.Repository.MediatorRepo;
import com.example.Real_Estate_Platform.Repository.PropertyRepo;
import com.example.Real_Estate_Platform.Repository.SellerRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService {
    @Autowired
    private SellerRepo sellerRepo;
    @Autowired
    private PropertyRepo propertyRepo;
    @Autowired
    private MediatorRepo mediatorRepo;
    @Autowired
    private BuyerRepo buyerRepo;
    
//    public SellerModel add(SellerModel sellerModel) {
//        Seller seller=sellerModel_entity(sellerModel);
//        return entity_sellerModel(sellerRepo.save(seller));
//    }

    public SellerModel entity_sellerModel(Seller seller) {
        SellerModel sellerModel=new SellerModel();
        sellerModel.setSid(seller.getSid());
        sellerModel.setSname(seller.getSname());
        sellerModel.setEmail(seller.getEmail());
        sellerModel.setMobile(seller.getMobile());
        sellerModel.setPropertyList(seller.getPropertyList());
        sellerModel.setMediator(seller.getMediator());
        sellerModel.setUsername(seller.getUsername());
        sellerModel.setPassword(seller.getPassword());
        return sellerModel;
    }

    public Seller sellerModel_entity(SellerModel sellerModel) {
        Seller seller=new Seller();
        seller.setSid(sellerModel.getSid());
        seller.setSname(sellerModel.getSname());
        seller.setEmail(sellerModel.getEmail());
        seller.setMobile(sellerModel.getMobile());
        seller.setPropertyList(sellerModel.getPropertyList());
        seller.setMediator(sellerModel.getMediator());
        seller.setPassword(sellerModel.getPassword());
        seller.setUsername(sellerModel.getUsername());
        return seller;
    }
    public PropertyModel entity_propertyModel(Property property){
        PropertyModel propertyModel=new PropertyModel();
        propertyModel.setId(property.getId());
        propertyModel.setTitle(property.getTitle());
        propertyModel.setArea(property.getArea());
        propertyModel.setSeller(property.getSeller());
        propertyModel.setPrice(property.getPrice());
        propertyModel.setSold(property.isSold());
        propertyModel.setAddress(property.getAddress());
        return  propertyModel;
    }
    public Property propertyModel_entity(PropertyModel propertyModel){
        Property property=new Property();
        property.setId(propertyModel.getId());
        property.setTitle(propertyModel.getTitle());
        property.setAddress(propertyModel.getAddress());
        property.setPrice(propertyModel.getPrice());
        property.setSeller(propertyModel.getSeller());
        property.setSeller(propertyModel.getSeller());
        property.setSold(propertyModel.isSold());
        property.setArea(propertyModel.getArea());
        return property;
    }
    public PropertyModel addProperty(PropertyModel propertyModel, int id) {
        Seller seller = sellerRepo.getReferenceById(id);
        Property property=propertyModel_entity(propertyModel);
        property.setSeller(seller);
        Property property1 = propertyRepo.save(property);
        return entity_propertyModel(property1);
    }

    public List<PropertyModel> getAllProperties(int id) {
        List<PropertyModel> propertyModel = new ArrayList<>();
        for(Property property : propertyRepo.findAll()){
            PropertyModel propertyModel1 = entity_propertyModel(property);
            propertyModel.add(propertyModel1);
        }
        return propertyModel;
    }

    public SellerModel add(int id,SellerModel sellerModel) {
        Mediator mediator=mediatorRepo.getReferenceById(id);
        Seller seller=sellerModel_entity(sellerModel);
        seller.setMediator(mediator);
        return entity_sellerModel(sellerRepo.save(seller));
    }

    public String sellerLogin(String username, String password) {
        Seller seller=sellerRepo.findByUsername(username);
        if(seller!=null && seller.getPassword().equals(password)){
            return "Login Successful";
        }
        return "Login Failed";
    }
    public List<Buyer> notifyMultipleBuyers(List<Buyer> buyers, Property property) {
        return buyers;
    }
    public void approveBuyer(int sellerId, int buyerId) {
        Optional<Seller> optionalSeller = sellerRepo.findById(sellerId);
        Optional<Buyer> optionalBuyer = buyerRepo.findById(buyerId);

        if (optionalSeller.isPresent() && optionalBuyer.isPresent()) {
            Seller seller = optionalSeller.get();
            Buyer buyer = optionalBuyer.get();

            if (buyer.getSellersToApprove().contains(buyer)) {
                buyer.getSellersToApprove().remove(buyer);
                buyer.getSellersToApprove().remove(seller);

                Property property = getPropertyForBuyer(seller, buyer);
                property.setSold(true);
                propertyRepo.save(property);

                sellerRepo.save(seller);
                buyerRepo.save(buyer);
            }
        }
    }

    private Property getPropertyForBuyer(Seller seller, Buyer buyer) {
        for (Property property : seller.getPropertyList()) {
            if (property.getBuyers().contains(buyer)) {
                return property;
            }
        }
        return null;
    }

}
