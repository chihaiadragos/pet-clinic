package org.example.validator;

import org.example.repository.PetOwnerRepository;

public class PetOwnerValidator {
    private PetOwnerRepository petOwnerRepository = new PetOwnerRepository();
    //TODO refactorizat validatorula sta ca appointment validator
    public boolean isUserPhoneNumberValid(String userPhoneNumber) {
        return userPhoneNumber.matches("^[+]407[0-9]{1,8}") && !petOwnerRepository.findByPhoneNumber(userPhoneNumber).isPresent();
    }
}
