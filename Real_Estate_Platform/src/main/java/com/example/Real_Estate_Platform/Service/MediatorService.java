package com.example.Real_Estate_Platform.Service;

import com.example.Real_Estate_Platform.Conversion.Conversion;
import com.example.Real_Estate_Platform.Entity.Mediator;
import com.example.Real_Estate_Platform.Model.MediatorModel;
import com.example.Real_Estate_Platform.Repository.MediatorRepo;
import com.example.Real_Estate_Platform.Repository.PropertyRepo;
import com.example.Real_Estate_Platform.Repository.SellerRepo;
import com.example.Real_Estate_Platform.ServiceImplementation.MediatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediatorService implements MediatorServiceImpl {
    @Autowired
    private MediatorRepo mediatorRepo;
    @Autowired
    private SellerRepo sellerRepo;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private PropertyRepo propertyRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Conversion conversion;
    @Override
    public Mediator registerMediator(MediatorModel mediatorModel) {
        if (mediatorRepo.existsByUsername(mediatorModel.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        Mediator mediator=conversion.mediatorModel_entity(mediatorModel);
        return mediatorRepo.save(mediator) ;
    }
    @Override
    public MediatorModel loginMediator(String username, String password) {
        Mediator mediator = mediatorRepo.findByUsername(username).get();
        if (mediator != null && passwordEncoder.matches(password,mediator.getPassword())) {
            return conversion.entity_mediatorModel(mediator);
        }
        return null;
    }

    @Override
    public List<MediatorModel> getAllMediators(){
        List<Mediator> mediatorList=mediatorRepo.findAll();
        List<MediatorModel> mediatorModelList=new ArrayList<>();

        mediatorList.forEach(mediator -> {
            mediatorModelList.add(conversion.entity_mediatorModel(mediator));
        });
        return mediatorModelList;
    }
    @Override
    public List<Mediator> getMediator(String mediatorName){
        return mediatorRepo.findAll().stream().filter(mediator -> mediator.getMname().
                equalsIgnoreCase(mediatorName)).collect(Collectors.toList());
    }
    @Override
    public Mediator getMediatorByName(String mediatorName){
        return mediatorRepo.findAll().stream().filter(mediator -> mediator.getMname().equalsIgnoreCase(mediatorName)).findFirst().get();
    }
}
