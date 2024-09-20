package com.example.Real_Estate_Platform.Service;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Mediator;
import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Entity.Seller;
import com.example.Real_Estate_Platform.Model.BuyerModel;
import com.example.Real_Estate_Platform.Model.MediatorModel;
import com.example.Real_Estate_Platform.Model.PropertyModel;
import com.example.Real_Estate_Platform.Model.SellerModel;
import com.example.Real_Estate_Platform.Repository.BuyerRepo;
import com.example.Real_Estate_Platform.Repository.MediatorRepo;
import com.example.Real_Estate_Platform.Repository.PropertyRepo;
import com.example.Real_Estate_Platform.Repository.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MediatorService {
    @Autowired
    private MediatorRepo mediatorRepo;
    @Autowired
    private SellerRepo sellerRepo;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private PropertyRepo propertyRepo;
    @Autowired
    private BuyerRepo buyerRepo;
    @Autowired
    private BuyerService buyerService;

    public MediatorModel add(MediatorModel mediatorModel) {
        System.out.println("678");
        Mediator mediator=mediatorModel_entity(mediatorModel);
        return entity_mediatorModel(mediatorRepo.save(mediator));
    }

    private MediatorModel entity_mediatorModel(Mediator mediator) {
        MediatorModel mediatorModel=new MediatorModel();
        mediatorModel.setMid(mediator.getMid());
        mediatorModel.setMobile(mediator.getMobile());
        mediatorModel.setPassword(mediator.getPassword());
        mediatorModel.setUsername(mediator.getUsername());
        mediatorModel.setEmail(mediator.getEmail());
        mediatorModel.setSellerList(mediator.getSellerList());
        mediatorModel.setMname(mediator.getMname());
        return mediatorModel;
    }

    private Mediator mediatorModel_entity(MediatorModel mediatorModel) {
        Mediator mediator=new Mediator();
        mediator.setMid(mediatorModel.getMid());
        mediator.setMname(mediatorModel.getMname());
        mediator.setPassword(mediatorModel.getPassword());
        mediator.setMobile(mediatorModel.getMobile());
        mediator.setUsername(mediatorModel.getUsername());
        mediator.setMobile(mediatorModel.getMobile());
        mediator.setSellerList(mediatorModel.getSellerList());
        mediator.setEmail(mediatorModel.getEmail());
        return mediator;
    }

    public String mediatorLogin(String username, String password) {
        Mediator mediator=mediatorRepo.findByUsername(username);
        if(mediator!=null && mediator.getPassword().equals(password)){
            return "Login Successful";
        }
        return "Login Failed";
    }

    public List<SellerModel> getAllSellers() {
        List<SellerModel> sellerModelList = sellerRepo.findAll().stream()
                .map(sellerService::entity_sellerModel)
                .collect(Collectors.toList());

        return sellerModelList;
    }

    public List<BuyerModel> getAllBuyers() {
        List<BuyerModel> buyerModelList = buyerRepo.findAll().stream()
                .map(buyerService::entity_buyerModel)
                .collect(Collectors.toList());

        return buyerModelList;
    }
    public PropertyModel getPropertyInfo(int propertyId) {
        Optional<Property> propertyOptional = propertyRepo.findById(propertyId);
        if (propertyOptional.isPresent()) {
            Property property = propertyOptional.get();
            return sellerService.entity_propertyModel(property);
        }
        return null;
    }

    public List<BuyerModel> getBuyersForProperty(int propertyId) {
        Optional<Property> propertyOptional = propertyRepo.findById(propertyId);
        if (propertyOptional.isPresent()) {
            Property property = propertyOptional.get();
            List<Buyer> buyers = (List<Buyer>) property.getBuyers();
            List<BuyerModel> buyerModels = buyers.stream()
                    .map(buyer -> buyerService.entity_buyerModel(buyer))
                    .collect(Collectors.toList());

            return buyerModels;
        }
        return Collections.emptyList();
    }
    public List<Buyer> notifyMultipleBuyers(List<Buyer> buyers, Property property) {
        return buyers;
    }


}
