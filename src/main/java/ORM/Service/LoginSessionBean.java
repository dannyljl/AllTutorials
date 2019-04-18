package ORM.Service;

import DTO.UserDTO;
import ORM.Entity.UserEntity;
import ORM.Manager.KweetManager;
import ORM.Manager.LoginManager;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LoginSessionBean {

    @Inject
    private LoginManager loginManager;

    public UserDTO AttemptLogin(String username, String password){
        return loginManager.attemptLogin(username,password);
    }


}
