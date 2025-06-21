package user_common.domain.adapter;

import user_common.domain.dto.UserDTO;
import user_common.domain.model.User;

public class UserDTO2User {

    private UserDTO2User() {}

    public static User convert(UserDTO userDTO){
        return new User(userDTO.getIdUser(),  userDTO.getFullName(), userDTO.getCpf(), userDTO.getUserEmail(), userDTO.getBalance());
    }
}
