package JudgeV2.demo.init;

import JudgeV2.demo.model.entity.RoleNameEnum;
import JudgeV2.demo.model.entity.Role;
import JudgeV2.demo.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DBInit(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role admin = new Role(RoleNameEnum.ADMIN);
            Role user = new Role(RoleNameEnum.USER);

            roleRepository.save(admin);
            roleRepository.save(user);
        }
    }
}
