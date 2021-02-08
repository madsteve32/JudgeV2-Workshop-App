package JudgeV2.demo.Service.impl;

import JudgeV2.demo.Service.RoleService;
import JudgeV2.demo.model.entity.RoleNameEnum;
import JudgeV2.demo.model.entity.Role;
import JudgeV2.demo.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {
        if (roleRepository.count() == 0) {
            Role admin = new Role(RoleNameEnum.ADMIN);
            Role user = new Role(RoleNameEnum.USER);

            roleRepository.save(admin);
            roleRepository.save(user);
        }
    }

    @Override
    public Role findRole(RoleNameEnum roleNameEnum) {
        return roleRepository.findByName(roleNameEnum).orElse(null);
    }
}
