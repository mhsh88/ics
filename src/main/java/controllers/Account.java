package controllers;

import com.payAm.core.ebean.BaseController;
import com.payAm.core.ebean.BaseDao;
import com.payAm.core.i18n.CoreMessagesCodes;
import daos.users.TokenActionDao;
import daos.users.UserDao;
import dtos.users.UserView;
import enumerations.security.ActionTokenType;
import models.users.TokenActionEntity;
import models.users.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.users.UsersRepository;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2015/Nov/14 - 9:43 AM
 */
@RestController
@RequestMapping("/login")
public class Account extends BaseController<UserEntity, Long, UserView> {

    @Inject
    UserDao userDao;

    @Inject
    TokenActionDao tokenActionDao;

    @Autowired
    public Account(CrudRepository<UserEntity, Long> repo) {
        super(repo);
    }

    @Override
    public BaseDao getDao() {
        return userDao;
    }

    @Autowired
    UsersRepository repository;


    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity doLogin(@RequestBody UserEntity user) {
//        UserEntity user = new UserEntity();
//        user.username = request().body().asJson().get("username").asText();
//        user.password = request().body().asJson().get("password").asText();
//        UserEntity user = Json.fromJson(request().body().asJson(), UserEntity.class);
//        user = super.repo.f.repo.findByUsernamePassword(user);
        user = repository.findByUsernameAndPassword(user.username, user.password);
        if (user == null) {
            return new ResponseEntity<>(CoreMessagesCodes.ERROR_LOGIN_NOT_FOUND, HttpStatus.UNAUTHORIZED);
        }
        TokenActionEntity tokenAction = tokenActionDao.create(ActionTokenType.ACCESS, TokenActionDao.generateToken(), user);
        return ResponseEntity.ok().body(tokenAction);//ok(Json.toJson(tokenAction));
    }

}