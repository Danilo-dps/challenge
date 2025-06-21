package user_common.domain.adapter;

import user_common.domain.dto.UserDTO;
import user_common.domain.model.User;
import user_common.domain.record.UserResponse;

public class User2UserResponse {

    private User2UserResponse() {}

    public static UserResponse convert(User user){
        return new UserResponse(user.getIdUser(), user.getFullName(), user.getUserEmail(), user.getBalance());
    }
}
