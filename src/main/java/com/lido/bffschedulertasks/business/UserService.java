package com.lido.bffschedulertasks.business;

import com.lido.bffschedulertasks.business.dto.in.LoginDTORequest;
import com.lido.bffschedulertasks.business.dto.in.UserDTORequest;
import com.lido.bffschedulertasks.business.dto.out.UserDTOResponse;
import com.lido.bffschedulertasks.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public UserDTOResponse saveUser(UserDTORequest userDTO){
      return userClient.saveUser(userDTO);
    }

    public String login(LoginDTORequest loginDTO) {
        return userClient.login(loginDTO);
    }


    public UserDTOResponse findUserByEmail(String token, String email){
        return userClient.findUserByEmail(email, token);
    }

    public void deleteUserByEmail(String token, String email){
        userClient.deleteUserByEmail(token, email);
    }

    public UserDTOResponse updateUserData(String token, UserDTOResponse userDTO){
       return userClient.updateUserData(token, userDTO);
    }
}
