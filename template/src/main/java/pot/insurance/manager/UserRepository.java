package pot.insurance.manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pot.insurance.manager.User;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {}
