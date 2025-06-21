package user_common.domain.adapter;

import user_common.domain.dto.UserDTO;
import user_common.domain.model.User;

public class User2UserDTO {

    private User2UserDTO() {}

    public static UserDTO convert(User user){
        return new UserDTO(user.getIdUser(),  user.getFullName(), user.getCpf(), user.getUserEmail(), user.getBalance());
    }
}
