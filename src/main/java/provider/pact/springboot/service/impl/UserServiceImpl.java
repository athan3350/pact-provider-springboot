package provider.pact.springboot.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import provider.pact.springboot.converter.UserConverter;
import provider.pact.springboot.entity.UserEntity;
import provider.pact.springboot.exception.BusinessException;
import provider.pact.springboot.exception.ErrorModel;
import provider.pact.springboot.model.UserDTO;
import provider.pact.springboot.repository.UserRepository;
import provider.pact.springboot.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());

        if (optionalUserEntity.isPresent()) {
            List<ErrorModel> errorModuleList = new ArrayList<>();
            ErrorModel errorModule = new ErrorModel();
            errorModule.setErrorMessage("The password already exist");
            errorModule.setCode("EMAIL_ALREADY_EXIST");
            errorModuleList.add(errorModule);

            throw new BusinessException(errorModuleList);
        }

        UserEntity userEntity = userRepository.save(this.modelMapper.map(userDTO, UserEntity.class));

        return userConverter.convertEntityToDTO(userEntity);
    }

    @Override
    public UserDTO login(String email, String password) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email, password);

        if (optionalUserEntity.isPresent()) {
            return userConverter.convertEntityToDTO(optionalUserEntity.get());
        }
        List<ErrorModel> errorModuleList = new ArrayList<>();
        ErrorModel errorModule = new ErrorModel();
        errorModule.setErrorMessage("Incorrect Email or Password");
        errorModule.setCode("INVALID_LOGIN");
        errorModuleList.add(errorModule);

        throw new BusinessException(errorModuleList);

    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        userRepository.findAll().forEach((UserEntity userEntity) -> userDTOS.add(userConverter.convertEntityToDTO(userEntity)));

        return userDTOS;
    }

    @Override
    public UserDTO getUser(Long id) {
        return userConverter.convertEntityToDTO(userRepository.findById(id)
                .orElseThrow(() -> {
                    List<ErrorModel> errorModuleList = new ArrayList<>();
                    ErrorModel errorModule = new ErrorModel();
                    errorModule.setErrorMessage("User " + id + " not found");
                    errorModule.setCode("USER_NOT_FOUND");
                    errorModuleList.add(errorModule);

                    throw new BusinessException(errorModuleList);
                }));
    }


}