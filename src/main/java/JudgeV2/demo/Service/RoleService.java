package JudgeV2.demo.Service;

import JudgeV2.demo.model.entity.RoleNameEnum;
import JudgeV2.demo.model.entity.Role;

public interface RoleService {
    void initRoles();

    Role findRole(RoleNameEnum roleNameEnum);
}
