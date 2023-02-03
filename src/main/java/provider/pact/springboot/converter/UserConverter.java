package provider.pact.springboot.converter;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import provider.pact.springboot.entity.UserEntity;
import provider.pact.springboot.model.UserDTO;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertEntityToDTO (UserEntity userEntity) {
        UserDTO userDTO = this.modelMapper.map(userEntity, UserDTO.class);
        userDTO.setPassword(null);
        return userDTO;

    }
}
