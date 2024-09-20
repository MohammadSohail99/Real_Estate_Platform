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
            Optional<Mediator> mediator = mediatorRepo.findAll().stream().filter(user -> user.getUsername().equals(username)).findFirst();
            return mediator.map(MediatorInfo::new).orElseThrow(() -> new UsernameNotFoundException("Mediator not found " + username));
        }
        if (sellerRepo.existsByUsername(username)) {
            Optional<Seller> seller = sellerRepo.findAll().stream().filter(user->user.getUsername().equals(username)).findFirst();
            return seller.map(SellerInfo::new).orElseThrow(() -> new UsernameNotFoundException("Seller not found " + username));
        }
        Optional<Buyer> buyer = buyerRepo.findAll().stream().filter(user->user.getUsername().equals(username)).findFirst();
        return buyer.map(BuyerInfo::new).orElseThrow(() -> new UsernameNotFoundException("Buyer not found " + username));
    }
}
