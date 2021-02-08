package JudgeV2.demo.repository;

import JudgeV2.demo.model.entity.RoleNameEnum;
import JudgeV2.demo.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleNameEnum roleNameEnum);
}
