package provider.pact.springboot.service;

import provider.pact.springboot.exception.BusinessException;
import provider.pact.springboot.model.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO registerUser(UserDTO userDTO);
    UserDTO login(String email, String password) throws BusinessException;
    List<UserDTO> getAllUsers();
    UserDTO getUser(Long id);

}
