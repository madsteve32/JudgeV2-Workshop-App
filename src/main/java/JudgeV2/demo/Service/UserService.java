package JudgeV2.demo.Service;

import JudgeV2.demo.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByNameAndPassword(String username, String password);
}
