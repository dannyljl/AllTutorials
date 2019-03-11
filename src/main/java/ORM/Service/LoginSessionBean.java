package ORM.Service;

import ORM.Entity.UserEntity;
import ORM.Manager.KweetManager;
import ORM.Manager.LoginManager;

import javax.ejb.Stateless;
import javax.inject.Inject;

//https://github.com/treehouse/giflib-hibernate/commit/f97a2828a466e849d8ae84884b5dce60a66cf412
//https://github.com/vivekkr12/cdi-hibernate/blob/master/src/main/java/org/ares/cdi/hibernate/dao/ProductDao.java
@Stateless
public class LoginSessionBean {

    @Inject
    private LoginManager loginManager;

    public UserEntity AttemptLogin(String username, String password){
        return loginManager.attemptLogin(username,password);
    }


}
