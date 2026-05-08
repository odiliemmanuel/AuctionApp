package org.auctionsystem.AuctionSystem.services;



import org.auctionsystem.AuctionSystem.data.models.User;
import org.auctionsystem.AuctionSystem.data.repositories.UserRepository;
import org.auctionsystem.AuctionSystem.dtos.requests.UserLoginRequest;
import org.auctionsystem.AuctionSystem.dtos.requests.UserSignUpRequest;
import org.auctionsystem.AuctionSystem.dtos.responses.UserLoginResponse;
import org.auctionsystem.AuctionSystem.dtos.responses.UserSignUpResponse;
import org.auctionsystem.AuctionSystem.event.NewUserEvent;
import org.auctionsystem.AuctionSystem.eventProducer.EventProducer;
import org.auctionsystem.AuctionSystem.exceptions.InvalidInputException;
import org.auctionsystem.AuctionSystem.exceptions.Messages;
import org.auctionsystem.AuctionSystem.exceptions.UserAlreadyExistsException;
import org.auctionsystem.AuctionSystem.exceptions.UserDoesNotExistException;
import org.auctionsystem.AuctionSystem.utils.AuthenticationMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventProducer eventProducer;

    public UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest){
        User user = AuthenticationMapper.mapUserSignUpRequestToUser(userSignUpRequest);
        if(userRepository.findByEmailAddress(user.getEmailAddress()) != null){
            throw new UserAlreadyExistsException(Messages.USER_ALREADY_EXISTS_EXCEPTION);
        }
        else{
            userRepository.save(user);

            NewUserEvent newUserEvent = new NewUserEvent(user.getId(), user.getEmailAddress(), user.getFirstName());
            eventProducer.publishEvent(newUserEvent);

            return AuthenticationMapper.mapUserToSignUpResponse(user);
        }
    }


    public UserLoginResponse logIn(UserLoginRequest userLoginRequest){

        if(!userRepository.existsByEmailAddress(userLoginRequest.getEmailAddress())){
            throw new UserDoesNotExistException(Messages.USER_DOES_NOT_EXIST_EXCEPTION);
        }
        User foundUser = userRepository.findByEmailAddress(userLoginRequest.getEmailAddress());

        if(!BCrypt.checkpw(userLoginRequest.getPassword(), foundUser.getPassword()))throw new InvalidInputException(Messages.INVALID_INPUT_EXCEPTION);
        return AuthenticationMapper.mapUserLoginResponseToUser(foundUser);

    }




}
