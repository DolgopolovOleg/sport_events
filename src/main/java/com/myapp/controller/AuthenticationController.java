package com.myapp.controller;

import com.myapp.dao.ActivationDao;
import com.myapp.entity.User;
import com.myapp.entity.UserConnection;
import com.myapp.mail_utils.MessageProducer;
import com.myapp.service.UserConnectionService;
import com.myapp.service.UserService;
import com.myapp.utils.SecurityUtil;
import org.apache.commons.collections.map.MultiValueMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.*;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivationDao activationDao;

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    ConnectionRepository usersConnectionRepository;

    @Autowired
    UserConnectionService userConnectionService;

    private String buildRedirectUrl(NativeWebRequest request){
        HttpServletRequest nativeRequest = request.getNativeRequest(HttpServletRequest.class);
        return nativeRequest.getRequestURL().toString();
    }

    @RequestMapping(value ="/authenticate/{providerId}", method = RequestMethod.GET)
    public void letsDance(@PathVariable String providerId,
                          NativeWebRequest request,
                          HttpServletResponse response) throws IOException {
//        Connection<?> connection = ProviderSignInUtils.getConnection(request);
//        UserProfile socialMediaProfile = connection.fetchUserProfile();

        //TODO: handle wrong providerId
        FacebookConnectionFactory connectionFactory = (FacebookConnectionFactory) connectionFactoryLocator.getConnectionFactory(providerId);
        OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri(buildRedirectUrl(request));
        String authorizeUrl = oauthOperations.buildAuthorizeUrl(params);
        response.sendRedirect(authorizeUrl);
    }

    @RequestMapping(value ="/authenticate/{providerId}", params = {"code"}, method = RequestMethod.GET)
    public String afterDanceActions(@PathVariable String providerId,
                                   @RequestParam("code") String authorizationCode,
                                   NativeWebRequest request){
        FacebookConnectionFactory connectionFactory = (FacebookConnectionFactory) connectionFactoryLocator.getConnectionFactory("facebook");
        OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
        AccessGrant accessGrant = oauthOperations.exchangeForAccess(authorizationCode, buildRedirectUrl(request), null);
        Connection<Facebook> connection = connectionFactory.createConnection(accessGrant);

        // check user by user token
        User currentUser = userService.getLoggedUser();
        Connection<?> checkConnection;
        Boolean isNewConnection = false;

        try{
            checkConnection = usersConnectionRepository.getConnection(connection.getKey());
        }catch(NoSuchConnectionException noSuchConnectionException){
            isNewConnection = true;
            checkConnection = null;
        }

        if(currentUser == null){
            // if user not exist
            if(isNewConnection){
                // if connection not exist
                currentUser = userService.createUserFromUserProfile(connection.fetchUserProfile());
                SecurityUtil.logInUser(currentUser);
                userConnectionService.addConnection(connection, currentUser.getUserId().toString());
            }else{
                // if connection exist

                // find userId from this connection
                List<String> listUserIds = userConnectionService.findUserIdsWithConnection(connection);
                Set<String> setUserIds = new HashSet<String>();
                setUserIds.add(connection.getKey().toString());
                String userId = (String) userConnectionService.findUserIdsConnectedTo(providerId, setUserIds).toArray()[0];

                User user = userService.findById(new Integer(listUserIds.get(0)));

                // return token
                SecurityUtil.logInUser(user);
            }
        }else{
            // if user exist
            if(isNewConnection){
                // if connection not exist
                userConnectionService.createConnectionRepository(currentUser.get_id().toString()).addConnection(connection);
            }else{
                // if connection exist
            }
        }


        // if authentication exist
            // if connection exist
                // handle case with similar userconnection (e.g. if authenticate with facebook and try to connect with one)
            // else
                // save userconnection with userId
        // else authentication isn`t exist
            // if userconnection exist
                // get User and authenticate him
            // else
                // register User
                // authenticate User
                // save userconnection with userId

        return "redirect:/";
    }
}
