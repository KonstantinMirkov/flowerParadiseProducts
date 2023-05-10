package app.service;

import app.model.entity.UserEntity;
import app.model.service.UserRegistrationServiceModel;

public interface UserService {
    void seedUsers();

    void registerAndLoginUser(UserRegistrationServiceModel serviceModel);

    boolean userNameExists(String username);

    UserEntity findByName(String username);

    UserEntity findById(Long id);
}
