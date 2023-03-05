package rest.builder;

//import rest.model.api.in.ICar;
import rest.model.api.in.IUser;
//import rest.model.api.out.ICarRepository;
import rest.model.api.out.IUserRepository;

import jakarta.inject.Inject;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.Default;

public class Builder {
    @Inject @Default
    private IUser userModel;

    @Inject @Default
    private IUserRepository userRepository;

//    @Inject @Default
//    private ICar carModel;
//
//    @Inject @Default
//    private ICarRepository carRepository;

    @Produces @Built
    public IUser buildUserModel() {
        userModel.injectRepository(userRepository);
        return userModel;
    }

//    @Produces @Built
//    public ICar buildCarModel() {
//        carModel.injectRepository(carRepository);
//        return carModel;
//    }
}
