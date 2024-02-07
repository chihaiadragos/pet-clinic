package org.example.service;

import org.example.entities.PetOwner;
import org.example.repository.PetOwnerRepository;
import org.example.validator.PetOwnerValidator;

public class PetOwnerService {
    private PetOwnerRepository petOwnerRepository = new PetOwnerRepository();
    private IOService ioService = new IOService();
    private PetOwnerValidator petOwnerValidator = new PetOwnerValidator();

    public void addNewPetOwner() {
        PetOwner petOwner = createPetOwner();
        petOwnerRepository.save(petOwner);
    }

    public void displayAllPetOwners() {
        ioService.displayAllPetOwners(petOwnerRepository.getAllPetOwners());
    }

    private PetOwner createPetOwner() {
        PetOwner petOwner = new PetOwner();
        ioService.displayToConsoleNextLine("PetOwner's first name: ");
        petOwner.setFirstName(ioService.getUserInput());
        ioService.displayToConsoleNextLine("PetOwner's last name: ");
        petOwner.setLastName(ioService.getUserInput());
        String userPhoneNumber = null;
        while (true) {
            ioService.displayToConsoleNextLine("PetOwner's phone number: ");
            userPhoneNumber = ioService.getUserInput();
            if (petOwnerValidator.isUserPhoneNumberValid(userPhoneNumber)) {
                petOwner.setPhoneNumber(userPhoneNumber);
                break;
            }
            ioService.displayToConsoleNextLine("Sorry, phone number " + userPhoneNumber + " is invalid");
            ioService.displayToConsoleNextLine("Try again");
        }
        ioService.displayToConsoleNextLine("New pet owner has been added successfully -> " + petOwner);
        return petOwner;
    }
}
