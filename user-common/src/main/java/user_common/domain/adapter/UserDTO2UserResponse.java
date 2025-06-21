package user_common.domain.adapter;

import user_common.domain.dto.UserDTO;
import user_common.domain.record.UserResponse;

public class UserDTO2UserResponse {

    private UserDTO2UserResponse() {}

    public static UserResponse convert(UserDTO userDTO){
        return new UserResponse(userDTO.getIdUser(), userDTO.getFullName(), userDTO.getUserEmail(), userDTO.getBalance());
    }
}
