package org.example.service;

import org.example.entities.Veterinary;
import org.example.repository.VeterinaryRepository;
import org.example.utils.Utils;

public class VeterinaryService {
    private VeterinaryRepository veterinaryRepository = new VeterinaryRepository();
    private IOService ioService = new IOService();
    public void addNewVeterinary() {
        veterinaryRepository.save(createVeterinary());
    }
    public void displayAllVeterinaries() {
        ioService.displayAllVeterinaries(veterinaryRepository.getAllVeterinaries());
    }
    private Veterinary createVeterinary() {
        Veterinary veterinary = new Veterinary();
        ioService.displayToConsoleNextLine("Veterinary's first name: ");
        veterinary.setFirstName(ioService.getUserInput());
        ioService.displayToConsoleNextLine("Veterinary's last name: ");
        veterinary.setLastName(ioService.getUserInput());
        veterinary.setBadgeID(Utils.generateBadgeID());
        ioService.displayToConsoleNextLine("New veterinary has been added successfully -> " + veterinary);
        return veterinary;
    }
}
