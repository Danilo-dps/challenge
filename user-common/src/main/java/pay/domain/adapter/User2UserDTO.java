package pay.domain.adapter;

import pay.domain.dto.UserDTO;
import pay.domain.model.User;

public class User2UserDTO {

    private User2UserDTO() {}

    public static UserDTO convert(User user){
        return new UserDTO(user.getIdUser(),  user.getFullName(), user.getCpf(), user.getUserEmail(), user.getBalance());
    }
}
