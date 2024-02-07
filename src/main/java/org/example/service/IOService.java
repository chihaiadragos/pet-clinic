package org.example.service;

import org.example.entities.Pet;
import org.example.entities.PetOwner;
import org.example.entities.Veterinary;

import java.util.List;
import java.util.Scanner;

public class IOService {
    public void displayMenu() {
        displayToConsoleNextLine("\nWelcome to the Pet-Clinic!");
        displayToConsoleNextLine("Please chose one of the following options:");
        displayToConsoleNextLine("0 - End session");
        displayToConsoleNextLine("1 - Register a veterinary");
        displayToConsoleNextLine("2 - Display all veterinaries");
        displayToConsoleNextLine("3 - Register a pet owner");
        displayToConsoleNextLine("4 - Display all pet owners");
        displayToConsoleNextLine("5 - Register a pet");
        displayToConsoleNextLine("6 - Display all pet");
        displayToConsoleNextLine("7 - Add an appointment");
    }

    public String getUserInput() {
        displayToConsoleInLine("Your answer: ");
        return new Scanner(System.in).nextLine();
    }

    public void displayToConsoleNextLine(String text) {
        System.out.println(text);
    }

    public void displayToConsoleInLine(String text) {
        System.out.print(text);
    }

    public void displayAllVeterinaries(List<Veterinary> veterinaries) {
        displayToConsoleNextLine("\n[CLINIC VETERINARIES]");
        // de modificat
        veterinaries.forEach(System.out::println);
    }

    public void displayAllPetOwners(List<PetOwner> petOwners) {
        displayToConsoleNextLine("\n[PET OWNERS]");
        //de modificat la fel ca la displayAllPets
        petOwners.forEach(System.out::println);
    }

    public void displayAllPets(List<Pet> allPets) {
        displayToConsoleNextLine("\n[ALL PETS OF OUR PET CLINIC]");
        for (Pet pet : allPets) {
            System.out.println("Pet name: " + pet.getName() + ", Pet type: " + pet.getAnimalType() + ", Pet collarId: "
                    + pet.getCollarId() + ", Pet owner's name: " + pet.getPetOwner().getFirstName());
        }
    }
    public void displayPetOptions(List<Pet> petList) {
        System.out.println("[Please select the number of your pet]");
        for (int index = 0; index < petList.size(); index++) {
            displayToConsoleNextLine((index + 1)
                    + " -> "
                    + petList.get(index).getName());
        }
    }

    public void displayVeterinaryOptions(List<Veterinary> veterinaryList) {
        System.out.println("[Please select the number of your veterinary]");
        for (int index = 0; index < veterinaryList.size(); index++) {
            displayToConsoleNextLine((index + 1)
                    + " -> Dr. "
                    + veterinaryList.get(index).getFirstName()
                    + " "
                    + veterinaryList.get(index).getLastName());
        }
    }
}
