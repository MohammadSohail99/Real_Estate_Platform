package com.example.Real_Estate_Platform.Service;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Mediator;
import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Entity.Seller;
import com.example.Real_Estate_Platform.Model.BuyerModel;
import com.example.Real_Estate_Platform.Repository.BuyerRepo;
import com.example.Real_Estate_Platform.Repository.MediatorRepo;
import com.example.Real_Estate_Platform.Repository.PropertyRepo;
import com.example.Real_Estate_Platform.Repository.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuyerService {
    @Autowired
    private BuyerRepo buyerRepo;
    @Autowired
    private PropertyRepo propertyRepo;
    @Autowired
    private SellerRepo sellerRepo;
    @Autowired
    private MediatorRepo mediatorRepo;
    @Autowired
    private MediatorService mediatorService;
    @Autowired
    private SellerService sellerService;
    public BuyerModel add(int id,BuyerModel buyerModel) {
        Mediator mediator=mediatorRepo.findById(id).get();
        System.out.println(mediator);
        Buyer buyer=buyerModel_entity(buyerModel);
        buyer.setMediator(mediator);
        System.out.println(buyer.getMediator().getUsername());
        System.out.println(buyer);
        return entity_buyerModel(buyerRepo.save(buyer));
    }

    public BuyerModel entity_buyerModel(Buyer buyer) {
        System.out.println("12345");
        BuyerModel buyerModel=new BuyerModel();
        System.out.println(buyerModel);
        buyerModel.setBid(buyer.getBid());
        buyerModel.setBname(buyer.getBname());
        buyerModel.setMobile(buyer.getMobile());
        buyerModel.setEmail(buyer.getEmail());
        buyerModel.setAddress(buyer.getAddress());
        buyerModel.setMediator(buyer.getMediator());
        buyerModel.setUsername(buyer.getUsername());
        buyerModel.setPassword(buyer.getPassword());
        System.out.println(buyerModel);
        return  buyerModel;
    }

    private Buyer buyerModel_entity(BuyerModel buyerModel) {
        Buyer buyer=new Buyer();
        buyer.setBid(buyerModel.getBid());
        buyer.setBname(buyerModel.getBname());
        buyer.setMobile(buyerModel.getMobile());
        buyer.setMobile(buyerModel.getMobile());
        buyer.setEmail(buyerModel.getEmail());
        buyer.setAddress(buyerModel.getAddress());
        buyer.setMediator(buyerModel.getMediator());
        buyer.setPassword(buyerModel.getPassword());
        buyer.setUsername(buyerModel.getUsername());
        return buyer;
    }
    public String buyerLogin(String username, String password) {
        Buyer buyer=buyerRepo.findByUsername(username);
        if(buyer!=null && buyer.getPassword().equals(password)){
            return "Login Successful";
        }
        return "Login Failed";
    }
    public List<Property> searchProperties(String address, double price) {
        return propertyRepo.findAll().stream()
                .filter(property -> property.getAddress().equals(address) && property.getPrice() <= price)
                .collect(Collectors.toList());
    }
    public boolean buyProperty(int id, String address, double price, int bid) {
        Optional<Seller> optionalSeller = sellerRepo.findById(id);
        Optional<Buyer> optionalBuyer = buyerRepo.findById(bid);
        if (optionalSeller.isPresent() && optionalBuyer.isPresent()) {
            Seller seller = optionalSeller.get();
            Buyer buyer = optionalBuyer.get();
            Property property = propertyRepo.findBySellerAndAddressAndPrice(seller, address, price);
            if (property != null && !property.isSold()) {
                property.getBuyers().add(buyer);
                property.setStatus("Pending");
                propertyRepo.save(property);

                if (property.getBuyers().size() >= 2) {
                    List<Buyer> buyers = new ArrayList<>(property.getBuyers());
                    mediatorService.notifyMultipleBuyers(buyers, property);
                    sellerService.notifyMultipleBuyers(buyers, property);
                }
                seller.setBuyersToApprove((List<Buyer>) buyer);
                property.setStatus("Sold");
                sellerRepo.save(seller);

                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
