package repositories.users;

import models.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long>{

    UserEntity findByUsernameAndPassword(String username, String password);
}