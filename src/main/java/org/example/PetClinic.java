package org.example;

import org.example.service.*;

public class PetClinic {
    private VeterinaryService veterinaryService = new VeterinaryService();
    private PetOwnerService petOwnerService = new PetOwnerService();
    private AppointmentService appointmentService = new AppointmentService();
    private PetService petService = new PetService();
    private IOService ioService = new IOService();

    public void startApplication() {
        while (true) {
            //1
            ioService.displayMenu();
            //2
            String userInput = ioService.getUserInput();
            //3
            processUserInput(userInput);
        }
    }
    private void processUserInput(String userInput) {

        switch (userInput) {
            case "0" -> {
                ioService.displayToConsoleInLine("Thank you for visiting our pet clinic. \nHave a nice day!");
                System.exit(0);
            }
            case "1" -> veterinaryService.addNewVeterinary();
            case "2" -> veterinaryService.displayAllVeterinaries();
            case "3" -> petOwnerService.addNewPetOwner();
            case "4" -> petOwnerService.displayAllPetOwners();
            case "5" -> petService.addNewPet();
            case "6" -> petService.displayAllPets();
            case "7" -> appointmentService.addNewAppointment();
            default -> ioService.displayToConsoleNextLine("Command not found");
        }
    }
}