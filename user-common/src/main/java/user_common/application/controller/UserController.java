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
        return ResponseEntity.ok(userSearch);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO userCreated = userService.create(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID userId, @RequestBody UserResponse userResponse){
        UserDTO userUpdate = userService.update(userId, userResponse);
        return ResponseEntity.ok(userUpdate);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity <Void> deleteUser(@PathVariable UUID userId){
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
