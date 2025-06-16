package user_common.application.controller;

import org.springframework.web.bind.annotation.*;
import user_common.domain.dto.UserDTO;
import user_common.domain.record.UserResponse;
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
        UserDTO userSearch = userService.getById(userId);
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

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID userId, @RequestBody UserResponse userResponse){
        UserDTO userUpdate = userService.update(userId, userResponse);
        return userUpdate != null
                ? ResponseEntity.ok(userUpdate)
                : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity <Void> deleteUser(@PathVariable UUID userId){
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
