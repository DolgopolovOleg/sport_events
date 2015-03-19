package com.myapp.controller;

import com.myapp.common.ActivationCodeReason;
import com.myapp.common.SocialMediaService;
import com.myapp.dao.ActivationDao;
import com.myapp.entity.Activation;
import com.myapp.entity.User;
import com.myapp.mail_utils.MessageProducer;
import com.myapp.registration.DuplicateEmailException;
import com.myapp.registration.RegistrationForm;
import com.myapp.service.UserService;
import com.myapp.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.*;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@SessionAttributes("user")
public class RegController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivationDao activationDao;

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private DataSource dataSource;

    @Autowired
    ConnectionRepository connectionRepository;

    @Autowired
    UsersConnectionRepository usersConnectionRepository;

    @Autowired
    SocialAuthenticationProvider socialAuthenticationProvider;

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        //TODO: if user logged in then goto linked account, otherwise check if this user exist and logging in otherwise goto registration
        Connection<?> connection = ProviderSignInUtils.getConnection(request);
        RegistrationForm registration = createRegistrationDTO(connection);

        if(connection != null){

            JdbcUsersConnectionRepository connectionRepository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
            ConnectionRepository cr = connectionRepository.createConnectionRepository(registration.getEmail());
            cr.addConnection(connection);

        }

        model.addAttribute("user", registration);

        return "users_registration";
    }

    private RegistrationForm createRegistrationDTO(Connection<?> connection) {
        RegistrationForm dto = new RegistrationForm();

        if (connection != null) {
            UserProfile socialMediaProfile = connection.fetchUserProfile();
            dto.setEmail(socialMediaProfile.getEmail());
            dto.setFirstName(socialMediaProfile.getFirstName());
            dto.setLastName(socialMediaProfile.getLastName());

            ConnectionKey providerKey = connection.getKey();
            dto.setSignInProvider(SocialMediaService.valueOf(providerKey.getProviderId().toUpperCase()));
        }

        return dto;
    }

    private String buildRedirectUrl(WebRequest request){
        HttpServletRequest nativeRequest = (HttpServletRequest)request;
        return nativeRequest.getRequestURL().toString();
    }










    @RequestMapping(value ="/signin")
    public void signinActions(WebRequest request, HttpServletResponse response) throws IOException {
//        Connection<?> connection = ProviderSignInUtils.getConnection(request);
//        UserProfile socialMediaProfile = connection.fetchUserProfile();

        FacebookConnectionFactory connectionFactory = (FacebookConnectionFactory) connectionFactoryLocator.getConnectionFactory("facebook");
        OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri(buildRedirectUrl(request));
        String authorizeUrl = oauthOperations.buildAuthorizeUrl(params);
        response.sendRedirect(authorizeUrl);
    }

    @RequestMapping(value ="/linked/{providerId}", method = RequestMethod.GET)
    public String linkedSocialUser(@PathVariable String providerId,
                                   @RequestParam("code") String authorizationCode){
        FacebookConnectionFactory connectionFactory = (FacebookConnectionFactory) connectionFactoryLocator.getConnectionFactory("facebook");
        OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
        AccessGrant accessGrant = oauthOperations.exchangeForAccess(authorizationCode, "http://localhost:18080/linked/facebook", null);
        Connection<Facebook> connection = connectionFactory.createConnection(accessGrant);
        return "/";
    }

    @RequestMapping(value ="/user/register", method = RequestMethod.POST)
    public String registerUserAccount(@Valid @ModelAttribute("user") RegistrationForm userAccountData,
                                      BindingResult result,
                                      WebRequest request) throws DuplicateEmailException {
        if (result.hasErrors()) {
            return "user/registrationForm";
        }

        User registered = createUserAccount(userAccountData, result);

        if (registered == null) {
            return "user/registrationForm";
        }
        SecurityUtil.logInUser(registered);
        ProviderSignInUtils.handlePostSignUp(registered.getUsername(), request);

        return "redirect:/";
    }

    private User createUserAccount(RegistrationForm userAccountData, BindingResult result) {
        User registered = null;

        try {
            registered = userService.registerNewUserAccount(userAccountData);
            Activation activation = new Activation(registered, ActivationCodeReason.REGISTRATION);
            //TODO: activation service instead activationDao
            activationDao.saveOrUpdate(activation);
            messageProducer.sendUserRegistrationMessage(activation);
        }
        catch (DuplicateEmailException ex) {
            addFieldError(
                    "user",
                    "email",
                    userAccountData.getEmail(),
                    "NotExist.user.email",
                    result);
        }

        return registered;
    }

    private void addFieldError(String objectName, String fieldName, String fieldValue,  String errorCode, BindingResult result) {
        FieldError error = new FieldError(
                objectName,
                fieldName,
                fieldValue,
                false,
                new String[]{errorCode},
                new Object[]{},
                errorCode
        );

        result.addError(error);
    }

}
