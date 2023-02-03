package provider.pact.springboot.controler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import provider.pact.springboot.model.UserDTO;
import provider.pact.springboot.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "User APIs")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "Register", notes = "This method is used to register an user")
    @PostMapping(path = "/register", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        userDTO = userService.registerUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Login", notes = "This method is used to log in an user")
    @PostMapping(path = "/login", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<UserDTO> loginUser(@Valid @RequestBody UserDTO userDTO) {
        userDTO = userService.login(userDTO.getOwnerEmail(), userDTO.getPassword());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all users", notes = "This method is used to get all users")
    @GetMapping(path = "", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all users", notes = "This method is used to get all users")
    @GetMapping(path = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

}
