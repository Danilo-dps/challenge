package pay.domain.adapter;

import pay.domain.dto.UserDTO;
import pay.domain.record.UserResponse;

public class UserDTO2UserResponse {

    private UserDTO2UserResponse() {}

    public static UserResponse convert(UserDTO userDTO){
        return new UserResponse(userDTO.getIdUser(), userDTO.getFullName(), userDTO.getUserEmail(), userDTO.getBalance());
    }
}
