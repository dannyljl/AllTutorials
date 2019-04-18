package Utility;

import DTO.UserDTO;
import ORM.Entity.UserEntity;
import ORM.Manager.LoginManager;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

public class LoginChecker implements LoginModule {

    @Inject
    private LoginManager loginManager;
    private Subject subject;
    private CallbackHandler callbackHandler;

    // not used in this simple LoginModule but can be useful when LoginModule are stacked
    private Map<String, ?> sharedState;
    private Map<String, ?> options;


    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
    }

    @Override
    public boolean login() throws LoginException {
        final Callback[] callbacks = new Callback[] {
                new NameCallback("username"),
                new PasswordCallback("password", false)
        };
        try {
            callbackHandler.handle(callbacks);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }

        final String username = NameCallback.class.cast(callbacks[0]).getName();
        final char[] password = PasswordCallback.class.cast(callbacks[1]).getPassword();
        loginManager = CDI.current().select(LoginManager.class).get();
        UserDTO actualUser = loginManager.attemptLogin(username,new String(password));

        if (actualUser == null){
            return false;
        }

        subject.getPrincipals().add(actualUser);
        return true;
    }

    @Override
    public boolean commit() throws LoginException {
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
