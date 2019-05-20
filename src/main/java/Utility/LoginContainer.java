package Utility;

import DTO.UserDTO;
import ORM.Manager.LoginManager;
import SecretJWTKey.constant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import java.security.Principal;
import java.util.HashMap;

@RequestScoped
public class LoginContainer {

    private Subject userSubject;

    @Inject
    private LoginManager loginManager;

    public UserDTO getUser(String username, String password) throws Exception {

        // 1. the definition of the credentials
        final CallbackHandler callbackHandler = callbacks->{
            for(final Callback callback : callbacks) {
                if(callback instanceof NameCallback) {
                    ((NameCallback) callback).setName(username);

                }
                else if(callback instanceof PasswordCallback) {
                    ((PasswordCallback) callback).setPassword(password.toCharArray());

                }
                else {
                    throw new UnsupportedCallbackException(callback);
                }
            }
        };

        // 2. the configuration
        final Configuration config = new Configuration() {
            @Override
            public AppConfigurationEntry[] getAppConfigurationEntry(String name) {

                return new AppConfigurationEntry[]{
                        new AppConfigurationEntry(
                                "Utility.LoginChecker",
                                AppConfigurationEntry.LoginModuleControlFlag.REQUIRED,
                                new HashMap<String, Object>() {
                                    {
                                    }
                                }
                        )
                };
            }
        };

        // if the config is not provided then, it looks for the system property java.security.auth.login.config
        // and loads the configuration from the file
        System.setProperty("java.security.auth.login.config",
                           Thread.currentThread().getContextClassLoader().getResource("jaas.config").toExternalForm());

        // 3. the API usage
        final LoginContext loginContext = new LoginContext("login", new Subject(), callbackHandler);

        // this will properly instantiate the login module and authenticate the user
        loginContext.login();

        // at the end of the authentication, the subject should contain the principals
        final Subject subject = loginContext.getSubject();
        this.userSubject = loginContext.getSubject();

        for(final Principal principal : userSubject.getPrincipals()) {
            UserDTO userDTO = (UserDTO) principal;
            String token =
                    Jwts.builder().setSubject(Integer.toString(userDTO.getUserId())).signWith(SignatureAlgorithm.HS256,
                                                                                              constant.key).compact();
            userDTO.setToken(loginManager.SaveToken(userDTO.getUserId(), token).getToken());

            return userDTO;
        }
        return null;

    }
}
