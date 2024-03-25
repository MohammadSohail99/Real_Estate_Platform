package com.example.Real_Estate_Platform.Service;

import com.example.Real_Estate_Platform.Conversion.Conversion;
import com.example.Real_Estate_Platform.Entity.Mediator;
import com.example.Real_Estate_Platform.Entity.Property;
import com.example.Real_Estate_Platform.Entity.Seller;
import com.example.Real_Estate_Platform.Model.SellerModel;
import com.example.Real_Estate_Platform.Repository.MediatorRepo;
import com.example.Real_Estate_Platform.Repository.PropertyRepo;
import com.example.Real_Estate_Platform.Repository.SellerRepo;
import com.example.Real_Estate_Platform.ServiceImplementation.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerService implements SellerServiceImpl {
    @Autowired
    private SellerRepo sellerRepo;
    @Autowired
    private PropertyRepo propertyRepo;
    @Autowired
    private MediatorRepo mediatorRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Conversion conversion;
    @Override
    public Seller registerSeller(SellerModel sellerModel, String username) {
        if (sellerRepo.findByUsername(sellerModel.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Seller seller = conversion.sellerModel_entity(sellerModel);
        Mediator mediator = mediatorRepo.findAll()
                .stream()
                .filter(m -> m.getMname().equals(username))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Mediator not found for username: " + username));

        seller.setMediator(mediator);
        return sellerRepo.save(seller);
    }

    @Override
    public SellerModel loginSeller(String username, String password) {
        Seller seller = sellerRepo.findByUsername(username).get();
        if (seller != null && passwordEncoder.matches(password,seller.getPassword())) {
            return conversion.entity_sellerModel(seller);
        }
        return null;
    }
    @Override
    public List<Seller> getAllSellers(int mid) {
        return sellerRepo.findAll().stream().filter(seller -> seller.getMediator().getMid()==mid).collect(Collectors.toList());
    }
    public Seller getSellerById(int sellerId) {
        Optional<Seller> sellerOptional = sellerRepo.findById(sellerId);
        return sellerOptional.orElse(null);
    }
    @Override
    public List<Property> getProperties(int sellerId) {
        Seller seller = getSellerById(sellerId);
        if (seller != null) {
            return seller.getPropertyList();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Seller> getSeller() {
        return  sellerRepo.findAll();
    }
}
