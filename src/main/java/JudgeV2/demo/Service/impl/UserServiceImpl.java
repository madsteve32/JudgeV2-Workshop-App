package JudgeV2.demo.Service.impl;

import JudgeV2.demo.Service.RoleService;
import JudgeV2.demo.Service.UserService;
import JudgeV2.demo.model.entity.RoleNameEnum;
import JudgeV2.demo.model.entity.User;
import JudgeV2.demo.model.service.UserServiceModel;
import JudgeV2.demo.repository.UserRepository;
import JudgeV2.demo.security.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, CurrentUser currentUser, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        user.setRole(roleService.findRole(RoleNameEnum.USER));

        userRepository.save(user);
    }

    @Override
    public UserServiceModel findUserByNameAndPassword(String username, String password) {

        return userRepository
                .findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void login(UserServiceModel userServiceModel) {
        currentUser
                .setId(userServiceModel.getId())
                .setUsername(userServiceModel.getUsername())
                .setRole(userServiceModel.getRole().getName());
    }

    @Override
    public void logout() {
        currentUser.setUsername(null);
    }

    @Override
    public List<String> findAllUsernames() {
        return userRepository.findAllUsernames();
    }

    @Override
    public void changeRole(String username, RoleNameEnum roleNameEnum) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            if (user.get().getRole().getName() != roleNameEnum) {
                user.get().setRole(roleService.findRole(roleNameEnum));

                userRepository.save(user.get());
            }
        }
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }
}
