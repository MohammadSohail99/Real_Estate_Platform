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
        Mediator mediator=mediatorRepo.getReferenceById(1);
        buyer.setMediator(mediator);
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
    public Buyer getBuyerById(int bid) {
        return buyerRepo.getReferenceById(bid);
    }
}
