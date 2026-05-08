package org.auctionsystem.AuctionSystem.utils;

import org.auctionsystem.AuctionSystem.data.models.User;
import org.auctionsystem.AuctionSystem.dtos.requests.UserSignUpRequest;
import org.auctionsystem.AuctionSystem.dtos.responses.UserLoginResponse;
import org.auctionsystem.AuctionSystem.dtos.responses.UserSignUpResponse;
import org.mindrot.jbcrypt.BCrypt;

public class AuthenticationMapper {


    public static User mapUserSignUpRequestToUser(UserSignUpRequest userSignUpRequest){
        User user = new User();

        user.setFirstName(userSignUpRequest.getFirstName());
        user.setLastName(userSignUpRequest.getLastName());
        user.setPhoneNumber(userSignUpRequest.getPhoneNumber());
        user.setUserName(userSignUpRequest.getUserName());
        user.setEmailAddress(userSignUpRequest.getEmailAddress());
        String hashedPassword = BCrypt.hashpw(userSignUpRequest.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        return user;
    }

    public static UserSignUpResponse mapUserToSignUpResponse(User user){
        UserSignUpResponse userSignUpResponse = new UserSignUpResponse();

        userSignUpResponse.setUsername(user.getUserName());
        userSignUpResponse.setPhoneNumber(user.getPhoneNumber());
        userSignUpResponse.setMessage("Welcome to FlashCorp App " + user.getUserName());

        return userSignUpResponse;
    }


    public static UserLoginResponse mapUserLoginResponseToUser(User user){
        UserLoginResponse userLoginResponse = new UserLoginResponse();

        userLoginResponse.setUsername(user.getUserName());
        userLoginResponse.setFirstName(user.getFirstName());
        userLoginResponse.setPhoneNumber(user.getPhoneNumber());
        userLoginResponse.setMessage("Welcome back " + user.getUserName());

        return  userLoginResponse;
    }




}

