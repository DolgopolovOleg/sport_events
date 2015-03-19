package com.myapp.service.impl;


import com.myapp.common.ActivationCodeReason;
import com.myapp.common.RoleList;
import com.myapp.dao.ActivationDao;
import com.myapp.dao.UserDao;
import com.myapp.entity.*;
import com.myapp.mail_utils.MessageProducer;
import com.myapp.registration.DuplicateEmailException;
import com.myapp.registration.RegistrationForm;
import com.myapp.service.UserService;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.joda.time.DateTime;

import java.util.List;

@Service("userService")
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService, UserDetailsService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private ActivationDao activationDao;

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private ParticipantDao participantDao;

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(Integer id) {
        User user = userDao.findById(id);
        if (user != null) {
            userDao.delete(user);
        }
    }

    @Override
    public List<User> findAll(){
        Example example = Example.create(new User()).excludeProperty("password");
        return userDao.findAll(example);
    }

    @Override
    public User createNewUser(User user) {
        user.setEnabled(false);
        DateTime now = DateTime.now();
        user.setCreationTime(now);
        user.setModificationTime(now);
        this.save(user);
        return user;
//        Activation activation = new Activation(user, ActivationCodeReason.REGISTRATION);
//        activationDao.saveOrUpdate(activation);
        //send message to user
//        messageProducer.sendMessages(activation.getUser());
    }

    @Override
    public User registrateNewUser(User user) {
        User createdUser = this.createNewUser(user);
        Activation activation = new Activation(user, ActivationCodeReason.REGISTRATION);
        activationDao.saveOrUpdate(activation);

        //send message to user
        messageProducer.sendMessages(activation.getUser());

        return user;
    }

    @Override
    public User getLoggedUser(){
        //TODO: return string if not authorize
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser")
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }

    @Override
    public void activateUser(User user) {
        user.setEnabled(true);
        this.setRole(user, RoleList.USER);
        userDao.save(user);
    }

    @Override
    public void setRole(User user, RoleList role) {
        com.myapp.entity.UserRole userRole = new com.myapp.entity.UserRole(user, role);
        user.getUserRole().add(userRole);
        userDao.save(user);
    }

    @Override
    public Activation findActivationForUser(User user) {
        return activationDao.findByUser(user);
    }

//    @Override
//    public List<ParticipantView> findAllParticipantForEvent(Event event) {
//        return participantDao.findAllParticipantForEvent(event);
//    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByEmail(s);
        if(user == null) throw new UsernameNotFoundException("username: " + s + "not found!");
        return user;
    }


    @Transactional
    @Override
    public User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException {
        if (emailExist(userAccountData.getEmail())) {
            throw new DuplicateEmailException("The email address: " + userAccountData.getEmail() + " is already in use.");
        }

        String encodedPassword = encodePassword(userAccountData);

        User.Builder user = User.getBuilder()
                .email(userAccountData.getEmail())
                .firstName(userAccountData.getFirstName())
                .lastName(userAccountData.getLastName())
                .password(encodedPassword);

        if (userAccountData.isSocialSignIn()) {
            user.signInProvider(userAccountData.getSignInProvider());
        }

        User registered = user.build();
        registered = this.createNewUser(registered);

        if(registered.getSignInProvider() != null){
            Activation activation = new Activation(registered, ActivationCodeReason.REGISTRATION);
            activationDao.saveOrUpdate(activation);

            //send message to user
            messageProducer.sendMessages(activation.getUser());
        }


        return registered;
    }

    @Override
    public User createUserFromUserProfile(UserProfile userProfile) {
        User user = new User();
//        user.set
        user.setFirstName(userProfile.getFirstName() == null ? null : userProfile.getFirstName());
        user.setLastName(userProfile.getLastName() == null ? null : userProfile.getLastName());
        user.setEmail(userProfile.getEmail() == null ? null : userProfile.getEmail());
        user.setEnabled(true);
        user = userDao.save(user);
        return user;
    }


    private boolean emailExist(String email) {
        User user = userDao.findByEmail(email);

        if (user != null) {
            return true;
        }

        return false;
    }

    private String encodePassword(RegistrationForm dto) {
        String encodedPassword = null;

        if (dto.isNormalRegistration()) {
            encodedPassword = passwordEncoder.encode(dto.getPassword());
        }

        return encodedPassword;
    }
}
