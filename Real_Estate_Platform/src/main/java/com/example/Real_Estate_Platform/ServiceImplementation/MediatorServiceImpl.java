package com.example.Real_Estate_Platform.ServiceImplementation;

import com.example.Real_Estate_Platform.Entity.Mediator;
import com.example.Real_Estate_Platform.Model.MediatorModel;
import com.example.Real_Estate_Platform.Model.PropertyModel;

import java.util.List;

public interface MediatorServiceImpl {
    Mediator registerMediator(MediatorModel mediatorModel);

    MediatorModel loginMediator(String username, String password);

    List<MediatorModel> getAllMediators();
}
