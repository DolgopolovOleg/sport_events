//package com.myapp.controller;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.social.ApiBinding;
//import org.springframework.social.connect.*;
//import org.springframework.social.connect.support.OAuth2ConnectionFactory;
//import org.springframework.social.connect.web.ConnectSupport;
//import org.springframework.social.connect.web.ProviderSignInAttempt;
//import org.springframework.social.connect.web.ProviderSignInUtils;
//import org.springframework.social.connect.web.SignInAdapter;
//import org.springframework.social.facebook.api.Facebook;
//import org.springframework.social.facebook.api.FacebookProfile;
//import org.springframework.social.security.SocialUser;
//import org.springframework.social.support.URIBuilder;
//import org.springframework.social.vkontakte.api.VKontakte;
//import org.springframework.social.vkontakte.api.VKontakteProfile;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.servlet.view.RedirectView;
//
//import javax.annotation.Resource;
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by Allega on 05.03.15.
// */
//
//@Controller
//@RequestMapping("/signin")
//public class ProviderSigninController {
//
//    protected @Autowired
//    HttpServletRequest httpServletRequest;
//
//    private final static String ACCOUNTS_LINKED = "ACCOUNTS_LINKED";
//
//
//    private final ConnectionFactoryLocator connectionFactoryLocator;
//
//    private final UsersConnectionRepository usersConnectionRepository;
//
//    private final SignInAdapter signInAdapter;
//
//    private String signInUrl = "/signin";
//
//    private String signUpUrl = "/home";
//
//    private String postSignInUrl = "/";
//
//    private final ConnectSupport webSupport = new ConnectSupport();
//
//    /**
//     * Creates a new provider sign-in controller.
//     * @param connectionFactoryLocator the locator of {@link org.springframework.social.connect.ConnectionFactory connection factories} used to support provider sign-in.
//     * Note: this reference should be a serializable proxy to a singleton-scoped target instance.
//     * This is because {@link org.springframework.social.connect.web.ProviderSignInAttempt} are session-scoped objects that hold ConnectionFactoryLocator references.
//     * If these references cannot be serialized, NotSerializableExceptions can occur at runtime.
//     * @param usersConnectionRepository the global store for service provider connections across all users.
//     * Note: this reference should be a serializable proxy to a singleton-scoped target instance.
//     * @param signInAdapter handles user sign-in
//     */
//    @Inject
//    public ProviderSigninController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository, SignInAdapter signInAdapter) {
//        this.connectionFactoryLocator = connectionFactoryLocator;
//        this.usersConnectionRepository = usersConnectionRepository;
//        this.signInAdapter = signInAdapter;
//        this.webSupport.setUseAuthenticateUrl(true);
//    }
//
//    /**
//     * Sets the URL of the application's sign in page.
//     * Defaults to "/signin".
//     * @param signInUrl the signIn URL
//     */
//    public void setSignInUrl(String signInUrl) {
//        this.signInUrl = signInUrl;
//    }
//
//    /**
//     * Sets the URL to redirect the user to if no local user account can be mapped when signing in using a provider.
//     * Defaults to "/signup".
//     * @param signUpUrl the signUp URL
//     */
//    public void setSignUpUrl(String signUpUrl) {
//        this.signUpUrl = signUpUrl;
//    }
//
//    /**
//     * Sets the default URL to redirect the user to after signing in using a provider.
//     * Defaults to "/".
//     * @param postSignInUrl the postSignIn URL
//     */
//    public void setPostSignInUrl(String postSignInUrl) {
//        this.postSignInUrl = postSignInUrl;
//    }
//
//    /**
//     * Configures the base secure URL for the application this controller is being used in e.g. <code>https://myapp.com</code>. Defaults to null.
//     * If specified, will be used to generate OAuth callback URLs.
//     * If not specified, OAuth callback URLs are generated from web request info.
//     * You may wish to set this property if requests into your application flow through a proxy to your application server.
//     * In this case, the request URI may contain a scheme, host, and/or port value that points to an internal server not appropriate for an external callback URL.
//     * If you have this problem, you can set this property to the base external URL for your application and it will be used to construct the callback URL instead.
//     * @param applicationUrl the application URL value
//     */
//    public void setApplicationUrl(String applicationUrl) {
//        webSupport.setApplicationUrl(applicationUrl);
//    }
//
//    /**
//     * Process a sign-in form submission by commencing the process of establishing a connection to the provider on behalf of the user.
//     * For OAuth1, fetches a new request token from the provider, temporarily stores it in the session, then redirects the user to the provider's site for authentication authorization.
//     * For OAuth2, redirects the user to the provider's site for authentication authorization.
//     */
//    @RequestMapping(value="/{providerId}", method= RequestMethod.POST)
//    public RedirectView signIn(@PathVariable String providerId,
//                               @RequestParam(required=false, value="eid") Integer eventID,
//                               @RequestParam(required=false, value="code") String iCode,
//                               @RequestParam(value="iframe", required = false) Boolean iframe,
//                               @RequestParam(value="iframe_version", required = false) String iframe_version,
//                               NativeWebRequest request) {
//        ConnectionFactory<?> connectionFactory = connectionFactoryLocator.getConnectionFactory(providerId);
//
//        try {
//
//            MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
//            Map<String, String[]> params = request.getParameterMap();
//
//            String OAuthUrl = webSupport.buildOAuthUrl(connectionFactory, request, parameters);
//            //		OAuthUrl = OAuthUrl.replace("response_type=code", "response_type=token");
//            return new RedirectView(OAuthUrl);
//        } catch (Exception e) {
//            return redirect(URIBuilder.fromUri(signInUrl).queryParam("error", "provider").build().toString());
//        }
//    }
//
//    /**
//     * Process the authentication callback from an OAuth 1 service provider.
//     * Called after the member authorizes the authentication, generally done once by having he or she click "Allow" in their web browser at the provider's site.
//     * Handles the provider sign-in callback by first determining if a local user account is associated with the connected provider account.
//     * If so, signs the local user in by delegating to {@link SignInAdapter#signIn(String, org.springframework.social.connect.Connection, NativeWebRequest)}
//     * If not, redirects the user to a signup page to create a new account with {@link org.springframework.social.connect.web.ProviderSignInAttempt} context exposed in the HttpSession.
//     * @see org.springframework.social.connect.web.ProviderSignInAttempt
//     * @see org.springframework.social.connect.web.ProviderSignInUtils
//     */
//	/*@RequestMapping(value="/{providerId}", method=RequestMethod.GET, params="oauth_token")
//	public RedirectView oauth1Callback(@PathVariable String providerId, @RequestParam(required=false, value="eid") Integer eventID, NativeWebRequest request) {
//		return this.signIn(providerId, eventID, request);
//		try {
//			OAuth1ConnectionFactory<?> connectionFactory = (OAuth1ConnectionFactory<?>) connectionFactoryLocator.getConnectionFactory(providerId);
//			Connection<?> connection = webSupport.completeConnection(connectionFactory, request);
//			return handleSignIn(connection, request);
//		} catch (Exception e) {
//			return redirect(URIBuilder.fromUri(signInUrl).queryParam("error", "provider").build().toString());
//		}
//	}*/
//
//    /**
//     * Process the authentication callback from an OAuth 2 service provider.
//     * Called after the user authorizes the authentication, generally done once by having he or she click "Allow" in their web browser at the provider's site.
//     * Handles the provider sign-in callback by first determining if a local user account is associated with the connected provider account.
//     * If so, signs the local user in by delegating to {@link SignInAdapter#signIn(String, org.springframework.social.connect.Connection, NativeWebRequest)}.
//     * If not, redirects the user to a signup page to create a new account with {@link org.springframework.social.connect.web.ProviderSignInAttempt} context exposed in the HttpSession.
//     * @see org.springframework.social.connect.web.ProviderSignInAttempt
//     * @see org.springframework.social.connect.web.ProviderSignInUtils
//     */
//    @RequestMapping(value="/{providerId}", method=RequestMethod.GET, params="code")
//    public RedirectView oauth2Callback(@PathVariable String providerId,
//                                       @RequestParam("code") String code,
//                                       NativeWebRequest request) {
//        try {
//            OAuth2ConnectionFactory<?> connectionFactory = (OAuth2ConnectionFactory<?>) connectionFactoryLocator.getConnectionFactory(providerId);
//            Connection<?> connection = webSupport.completeConnection(connectionFactory, request);
//
//
//            return handleSignIn(connection, request);
//        } catch (Exception e) {
//            return redirect(URIBuilder.fromUri(signInUrl).queryParam("error", "provider").build().toString());
//        }
//    }
//
//    /**
//     * Process the authentication callback when neither the oauth_token or code parameter is given, likely indicating that the user denied authorization with the provider.
//     * Redirects to application's sign in URL, as set in the signInUrl property.
//     */
//    @RequestMapping(value="/{providerId}", method=RequestMethod.GET)
//    public RedirectView canceledAuthorizationCallback() {
//        return redirect(signInUrl);
//    }
//
//    // internal helpers
//
//    private RedirectView handleSignIn(Connection<?> connection,
//                                      NativeWebRequest request) {
//
//        List<String> userIds = usersConnectionRepository.findUserIdsWithConnection(connection);
//
//
//        //Integer currentInviteReply = (Integer)httpServletRequest.getSession().getAttribute(Constants.CURRENT_INVITE_ID);
//        //Integer currentRecipientReply = (Integer)httpServletRequest.getSession().getAttribute(Constants.CURRENT_RECIPIENT_ID);
//        if (userIds.size() == 0) {
//            ProviderSignInAttempt signInAttempt = new ProviderSignInAttempt(connection, connectionFactoryLocator, usersConnectionRepository);
//            request.setAttribute(ProviderSignInAttempt.SESSION_ATTRIBUTE, signInAttempt, RequestAttributes.SCOPE_SESSION);
//            String emailobtained = this.handleSignup(request);
//            //if no email was obtained
//
//        } else if (userIds.size() == 1) {
//            usersConnectionRepository.createConnectionRepository(userIds.get(0)).updateConnection(connection);
//            String originalUrl = signInAdapter.signIn(userIds.get(0), connection, request);
//
//        } else {
//            return redirect(URIBuilder.fromUri(signInUrl).queryParam("error", "multiple_users").build().toString());
//        }
//
//        return redirect(URIBuilder.fromUri(signInUrl).queryParam("error", "multiple_users").build().toString());
//    }
//
//    private RedirectView redirect(String url) {
//        return new RedirectView(url, true);
//    }
//
//    private static final String PROVIDER_ERROR_ATTRIBUTE = "social.provider.error";
//
//
//
//    public String handleSignup(NativeWebRequest nativeRequest){
//
//        Facebook facebook = null;
//        VKontakte vKontakte = null;
//        String emailobtained = null;
//
//        Connection<?> connection = ProviderSignInUtils.getConnection(nativeRequest);
//        if (connection != null) {
//            //logger.info("IN the connection");
//            // populate new User from social connection user profile
//            UserProfile userProfile = connection.fetchUserProfile();
////            Date now = Utility.getMoscowDate();
////            User userTemp = new User();
////            userTemp.setCreateDate(now);
////            userTemp.setLastLogin(now);
////
////            CreateUserForm cuf = new CreateUserForm();
//            ApiBinding apiBinding = (ApiBinding) connection.getApi();
//            if(apiBinding instanceof Facebook){
//                facebook = (Facebook) apiBinding;
//                FacebookProfile fbp = facebook.userOperations().getUserProfile();
//                //cuf.setUserName("fb" + fbp.getId());
//                //cuf.setUserName(fbp.getUsername()+Constants.FACEBOOK_USER);
////                String fbuid = ((fbp.getUsername()!=null)?fbp.getUsername():fbp.getId())+Constants.FACEBOOK_USER;
////                cuf.setUserName(fbuid);
////                userTemp.setFirstName(fbp.getFirstName());
////                userTemp.setLastName(fbp.getLastName());
////                if(fbp.getLocation()!=null)
////                    userTemp.setCity(fbp.getLocation().getUsername());
////                if(fbp.getLocale()!=null)
////                    userTemp.setCountry(fbp.getLocale().getCountry());
//
//                List<org.springframework.social.facebook.api.Reference> languages = fbp.getLanguages();
////                if((languages!=null)&&(languages.isEmpty())){
////                    userTemp.setLanguage(languages.get(0).getUsername());
////                }
//
////                String email = fbp.getEmail();
////                if((email!=null)&&(!email.equals(""))){
////                    userTemp.setEmail(email);
////
////                    existingUserByEmail = userService.getUsers(email);
////                    if(existingUserByEmail!=null){
////                        User user = userService.getUser(existingUserByEmail.getUserID());
////                        user.update(userTemp);
////                        userService.updateUser(user);
////                        //TODO EMAIL NOTIFICATION REGARDING USER LINKAGE
////                        emailobtained = ACCOUNTS_LINKED;
////                    }
////                    else{
////                        String currentInviteReply = (String)httpServletRequest.getSession().getAttribute(Constants.CURRENT_INVITE_ID);
////
////                        if(currentInviteReply!=null){
////                            //if no email obtained redirect to create email form, pass eventid
////                            Invite i = inviteeService.findInvite(currentInviteReply);
////                            if(i!=null){
////                                emailobtained = i.getInviteeUserID();
////                                userTemp.setEmail(emailobtained);
////                                httpServletRequest.getSession().setAttribute(Constants.CURRENT_EVENT_ID, i.getEventID());
////                            }
////                            else{
////                                emailobtained = email;
////                            }
////
////                        }
////                        else{
////                            emailobtained = email;
////                        }
////                    }
////                }
////                else{
////                    userTemp.setEmail(fbp.getUsername()+Constants.FACEBOOK_EMAIL_DOMAIN);
////                    //uncomment if we are ok with facebook.com email
////                    //emailobtained = true;
////                }
////                cuf.setPassword(userService.generatePassword());
////
////
//            }
//            if(apiBinding instanceof VKontakte){
//                vKontakte = (VKontakte)apiBinding;
//                VKontakteProfile vkp = vKontakte.usersOperations().getProfile();
////                cuf.setUserName(vkp.getFirstName()+"."+vkp.getLastName()+"."+vkp.getUid()+Constants.VKONTAKTE_USER);
////                cuf.setPassword("vk"+ userService.generatePassword());
////                userTemp.setFirstName(vkp.getFirstName());
////                userTemp.setLastName(vkp.getLastName());
//            }
//
////            cuf.setUser(userTemp);
//
//
////            if ((existingUserByEmail==null) && (userService.getUser(cuf.getUserName()) == null) ){
////                try {
////                    //createUserService.createUser(cuf, Constants.ROLE_SOCIALGUEST);
////
////                    cuf.setEnabled(emailobtained!=null);
////                    createUserService.createUser(cuf, Constants.ROLE_USER);
////                    ((HttpServletRequest)nativeRequest.getNativeRequest()).getSession().setAttribute(Constants.CREATE_USER_FORM, cuf);
////                } catch (DataIntegrityViolationException d) {
////                    logger.error(d);
////                    //modelAndView.addObject("message", true);
////                    ((HttpServletRequest)nativeRequest.getNativeRequest()).getSession().setAttribute(Constants.USER_EXISTS, d.getMessage() );
////                    //modelAndView.setViewName("login");
////
////                }
////            }
//
//
////            String userToBeSignedIn  = ((existingUserByEmail==null)?cuf.getUserName():existingUserByEmail.getUsername());
////            signInAdapter.signIn(userToBeSignedIn, connection, nativeRequest);
////            ProviderSignInUtils.handlePostSignUp(userToBeSignedIn, nativeRequest);
//
//            try{
////                List<SocialUser> socialUsers = socialUserDAO.findByUserId(cuf.getUserName());
////                for(SocialUser socialUser:socialUsers){
////                    recipientService.updateRecipientUserID(socialUser.getProviderUserId(), socialUser.getProviderId());
////
////
//////                    if(socialUser.getProviderId().equals(Constants.vkontakte)){
//////
//////                        VKontakte vkontakte = socialUserService.getVKontakte(true);
//////                        ArrayList<String> vkids = new ArrayList<String>();
//////                        vkids.add(socialUser.getProviderUserId());
//////                        VKontakteProfile vkp = vkontakte.usersOperations().getProfiles(vkids).get(0);
//////                        socialUser.setPhotoL(vkp.getPhotoBig());
//////                        socialUser.setPhotoM(vkp.getPhotoMedium());
//////                        socialUserDAO.update(socialUser);
//////
//////                    }
////                }
//            }catch(Exception e){
////                logger.error(e);
//                e.printStackTrace();
//            }
//
//
//        }
//        return emailobtained;
//    }
//
//
//
//}
