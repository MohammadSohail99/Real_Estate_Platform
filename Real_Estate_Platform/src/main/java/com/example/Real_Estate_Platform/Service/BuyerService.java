package com.example.Real_Estate_Platform.Service;

import com.example.Real_Estate_Platform.Conversion.Conversion;
import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Mediator;
import com.example.Real_Estate_Platform.Model.BuyerModel;
import com.example.Real_Estate_Platform.Repository.BuyerRepo;
import com.example.Real_Estate_Platform.Repository.MediatorRepo;
import com.example.Real_Estate_Platform.Repository.PropertyRepo;
import com.example.Real_Estate_Platform.Repository.SellerRepo;
import com.example.Real_Estate_Platform.ServiceImplementation.BuyerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuyerService implements BuyerServiceImpl {
    @Autowired
    private BuyerRepo buyerRepo;
    @Autowired
    private PropertyRepo propertyRepo;
    @Autowired
    private SellerRepo sellerRepo;
    @Autowired
    private MediatorRepo mediatorRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Conversion conversion;
    @Override
    public void registerBuyer(BuyerModel buyerModel) {
        if (buyerRepo.findByUsername(buyerModel.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        Buyer buyer=conversion.buyerModel_entity(buyerModel);
        buyerRepo.save(buyer);
    }
    @Override
    public BuyerModel loginBuyer(String username, String password) {
        System.out.println(password);
        Buyer buyer = buyerRepo.findByUsername(username).get();
        if (passwordEncoder.matches(password,buyer.getPassword())) {
            return conversion.entity_buyerModel(buyer);
        }
        return null;
    }
    @Override
    public Buyer getBuyerByName(String buyerName){
        System.out.println(buyerName);
        System.out.println(buyerRepo.findAll());
        return buyerRepo.findAll().stream().filter(buyer -> buyer.getBname()
                .equalsIgnoreCase(buyerName)).findFirst().get();
    }
    @Override
    public List<Buyer> getBuyer(String buyerName){
        return buyerRepo.findAll().stream().filter(buyer -> buyer.getBname().equalsIgnoreCase(buyerName)).collect(Collectors.toList());
    }
}
