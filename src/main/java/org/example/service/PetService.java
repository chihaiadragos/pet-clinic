package org.example.service;

import org.example.entities.Pet;
import org.example.entities.PetOwner;
import org.example.repository.PetOwnerRepository;
import org.example.repository.PetRepository;
import org.example.utils.Utils;

import java.util.Optional;

public class PetService {
    private PetRepository petRepository = new PetRepository();
    private IOService ioService = new IOService();
    private PetOwnerRepository petOwnerRepository = new PetOwnerRepository();
    public void addNewPet() {
        Optional<Pet> optionalPet = createPet();
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            petRepository.save(pet);
            ioService.displayToConsoleNextLine(pet.toString());
        } else {
            ioService.displayToConsoleNextLine("Pet owner does not exist");
        }
    }
    public void displayAllPets() {
        ioService.displayAllPets(petRepository.getAllPets());
    }
    private Optional<Pet> createPet() {
        Pet pet = new Pet();
        ioService.displayToConsoleNextLine("What is the pet type?");
        pet.setAnimalType(ioService.getUserInput());
        ioService.displayToConsoleNextLine("What is the pet name?");
        pet.setName(ioService.getUserInput());
        // De verificat faptul ca collar id este unic, daca nu trebuie sa apelam pana cand gasim unul unic
        pet.setCollarId(Utils.generateCollarId());
        ioService.displayToConsoleNextLine("What is the pet owner's phone number?");
        String phoneNumber = ioService.getUserInput();
        Optional<PetOwner> optionalPetOwner = petOwnerRepository.findByPhoneNumber(phoneNumber);
        if (optionalPetOwner.isEmpty()) {
            return Optional.empty();
        }
        pet.setPetOwner(optionalPetOwner.get());
        return Optional.of(pet);
    }
}
