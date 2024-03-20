package com.example.Real_Estate_Platform.Security;

import com.example.Real_Estate_Platform.Entity.Buyer;
import com.example.Real_Estate_Platform.Entity.Mediator;
import com.example.Real_Estate_Platform.Entity.Seller;
import com.example.Real_Estate_Platform.Repository.BuyerRepo;
import com.example.Real_Estate_Platform.Repository.MediatorRepo;
import com.example.Real_Estate_Platform.Repository.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserInfoDetailsService implements UserDetailsService {
    @Autowired
    MediatorRepo mediatorRepo;
    @Autowired
    SellerRepo sellerRepo;
    @Autowired
    BuyerRepo buyerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (mediatorRepo.existsByUsername(username)) {
            Mediator mediator = mediatorRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Mediator not found " + username));
            return new MediatorInfo(mediator);
        }
        if (sellerRepo.existsByUsername(username)) {
            Seller seller = sellerRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Seller not found " + username));
            return new SellerInfo(seller);
        }
        Buyer buyer = buyerRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Buyer not found " + username));
        return new BuyerInfo(buyer);
    }
}
