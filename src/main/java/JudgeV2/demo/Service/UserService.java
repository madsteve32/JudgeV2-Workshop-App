package JudgeV2.demo.Service;

import JudgeV2.demo.model.entity.RoleNameEnum;
import JudgeV2.demo.model.entity.User;
import JudgeV2.demo.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByNameAndPassword(String username, String password);

    void login(UserServiceModel userServiceModel);

    void logout();

    List<String> findAllUsernames();

    void changeRole(String username, RoleNameEnum roleNameEnum);

    User findById(long id);
}
