package pay.domain.adapter;

import pay.domain.dto.UserDTO;
import pay.domain.model.User;

public class UserDTO2User {

    private UserDTO2User() {}

    public static User convert(UserDTO userDTO){
        return new User(userDTO.getIdUser(),  userDTO.getFullName(), userDTO.getCpf(), userDTO.getUserEmail(), userDTO.getBalance());
    }
}
