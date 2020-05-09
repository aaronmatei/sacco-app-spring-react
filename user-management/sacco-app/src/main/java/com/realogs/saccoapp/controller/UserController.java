package com.realogs.saccoapp.controller;

import com.realogs.saccoapp.controller.exceptions.UserNotFoundException;
import com.realogs.saccoapp.model.Role;
import com.realogs.saccoapp.model.User;
import com.realogs.saccoapp.model.UserResponseModel;
import com.realogs.saccoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private Environment env;

    @GetMapping("/service/port")
    public String getPort(){
        return "User service running on port: "+env.getProperty("local.server.port");
    }

    @GetMapping("/service/services")
    public ResponseEntity<?> getServices(){
        return new ResponseEntity<>(discoveryClient.getServices(),HttpStatus.OK);

    }
    @GetMapping("/service/user/{userId}")
    public ResponseEntity<?> findUserById(@PathVariable Long userId){
        User userFound = userService.findByUserId(userId);
        if(userFound == null){
            throw new UserNotFoundException("User with id "+userId+" not found");
        }
        return ResponseEntity.ok(userService.findByUserId(userId));
    }
    @PostMapping(value = "/service/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE }
    )
    public @ResponseBody ResponseEntity<UserResponseModel> saveUser(@Valid @RequestBody User user){


        if(userService.findByUsername(user.getUsername()) != null){
            //409
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        //default role is = user
        user.setRole(Role.USER);
        User createdUser = userService.save(user);
        UserResponseModel responseModel = new UserResponseModel(createdUser.getId(),createdUser.getFirstName(),createdUser.getLastName(),createdUser.getUsername(),createdUser.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }
//    @GetMapping("/service/login")
//    public ResponseEntity<UserResponseModel> getUser(HttpServletRequest request){
//        Principal principal = request.getUserPrincipal();
//        if(principal == null || principal.getName() == null){
//            //login?logout
//            return new ResponseEntity<>(HttpStatus.OK);
//
//        }
//        //username = principal.getName()
//        User loggedInUser = userService.findByUsername(principal.getName());
//        UserResponseModel responseModel = new UserResponseModel(loggedInUser.getId(),loggedInUser.getFirstName(),loggedInUser.getLastName(),loggedInUser.getUsername(),loggedInUser.getEmail());
//        return ResponseEntity.ok().body(responseModel);
//    }
    @PostMapping("/service/usernames")
    public ResponseEntity<?> getUsernamesForUsers(@RequestBody List<Long> idList){
        return ResponseEntity.ok(userService.findUsers(idList));
    }
    @GetMapping("/service/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("it is working....");
    }
}
