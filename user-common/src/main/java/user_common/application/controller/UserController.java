package user_common.application.controller;

import org.springframework.web.bind.annotation.*;
import user_common.domain.dto.UserDTO;
import user_common.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID userId) {
        UserDTO userSearch = userService.getByUserId(userId);
        return userSearch != null
                ? ResponseEntity.ok(userSearch)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO userCreated = userService.create(userDTO);
        return userCreated != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(userCreated)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
