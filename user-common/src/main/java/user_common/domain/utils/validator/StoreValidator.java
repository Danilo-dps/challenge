package user_common.domain.utils.validator;

import org.springframework.stereotype.Component;
import user_common.domain.dto.StoreDTO;
import user_common.domain.utils.validations.CnpjValidator;
import user_common.domain.utils.validations.EmailValidator;

@Component
public class StoreValidator {

    private final CnpjValidator cnpjValidator;
    private final EmailValidator emailValidator;

    public StoreValidator(CnpjValidator cnpjValidator, EmailValidator emailValidator) {
        this.cnpjValidator = cnpjValidator;
        this.emailValidator = emailValidator;
    }

    public void validate(StoreDTO storeDTO) {
        cnpjValidator.validate(storeDTO.getCnpj());
        emailValidator.validate(storeDTO.getStoreEmail());
    }
}
