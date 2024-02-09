package org.example.service;

import org.example.entities.Appointment;
import org.example.entities.Pet;
import org.example.entities.Veterinary;
import org.example.exception.*;
import org.example.repository.AppointmentRepository;
import org.example.repository.PetRepository;
import org.example.repository.VeterinaryRepository;
import org.example.utils.Utils;
import org.example.validator.AppointmentValidator;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentService {
    private IOService ioService = new IOService();
    private PetRepository petRepository = new PetRepository();
    private VeterinaryRepository veterinaryRepository = new VeterinaryRepository();
    private AppointmentRepository appointmentRepository = new AppointmentRepository();
    private AppointmentValidator appointmentValidator = new AppointmentValidator();
    public void addNewAppointment() {
        try {
                Appointment appointment = createAppointment();
                save(appointment);
                displayAppointmentInfo(appointment);
                ioService.displayToConsoleNextLine("[Appointment was done successfully]");
        } catch (SomethingWentWrongException e) {
            ioService.displayToConsoleNextLine("Sorry, something went wrong!");
        } catch (NoPetsFoundForPhoneNumberException e) {
            ioService.displayToConsoleNextLine("Sorry, the phone number doesn't have any pets!");
        } catch (InvalidInputException e) {
            ioService.displayToConsoleNextLine("Sorry, the input is invalid");
        } catch (VeterinariesNotFoundException e) {
            ioService.displayToConsoleNextLine("Sorry, no veterinaries available");
        } catch (InvalidDateTimeException e) {
            ioService.displayToConsoleNextLine("Invalid date and time input");
        } catch (InvalidAppointmentDateException e) {
            ioService.displayToConsoleNextLine("Sorry the veterinary is not available at this date and time.");
        }
    }

    private void save(Appointment appointment) throws SomethingWentWrongException {
        try {
            appointmentRepository.save(appointment);
        } catch (Exception e) {
            throw new SomethingWentWrongException();
        }
    }

    private Appointment createAppointment() throws NoPetsFoundForPhoneNumberException,
            InvalidInputException,
            VeterinariesNotFoundException,
            InvalidAppointmentDateException,
            InvalidDateTimeException {

            Appointment appointment = new Appointment();
            setPetForAppointment(appointment);
            Veterinary selectedVeterinary = getSelectedVeterinary();
            appointment.setVeterinary(selectedVeterinary);
            setAppointmentDate(selectedVeterinary, appointment);
            return appointment;
    }

    private void setAppointmentDate(Veterinary selectedVeterinary, Appointment appointment) throws InvalidDateTimeException, InvalidAppointmentDateException {
        LocalDateTime appointmentDateTine = obtainDateTime();
        appointmentValidator.validateNewAppointmentDate(appointmentDateTine, selectedVeterinary);
        appointment.setDateTime(appointmentDateTine);
    }

    private Veterinary getSelectedVeterinary() throws VeterinariesNotFoundException, InvalidInputException {
        List<Veterinary> veterinaryList = veterinaryRepository.getAllVeterinaries();
        ioService.displayVeterinaryOptions(veterinaryList);
        return obtainSelectedVeterinary(veterinaryList);
    }

    private void setPetForAppointment(Appointment appointment) throws NoPetsFoundForPhoneNumberException, InvalidInputException {
        ioService.displayToConsoleNextLine("What is your phone number?");
        List<Pet> ownerPetList = petRepository.findByOwnerPhoneNumber(ioService.getUserInput());
        ioService.displayPetOptions(ownerPetList);
        appointment.setPet(obtainSelectedPet(ownerPetList));
    }

    private LocalDateTime obtainDateTime() throws InvalidDateTimeException {
        ioService.displayToConsoleNextLine("What is the date of your appointment?");
        String appointmentLocalDate = ioService.getUserInput();
        ioService.displayToConsoleNextLine("What is the time of your appointment");
        String appointmentLocalTime = ioService.getUserInput();
        return Utils.convertToDateTime(appointmentLocalDate, appointmentLocalTime);
    }

    private void displayAppointmentInfo(Appointment appointment) {
        ioService.displayToConsoleNextLine("\n[Appointment]\n" +
                "Pet: " +
                appointment.getPet().getName() +
                "\nOwner full name: " +
                appointment.getPet().getPetOwner().getFirstName() +
                " " +
                appointment.getPet().getPetOwner().getLastName() +
                "\nVeterinary: Dr. " +
                appointment.getVeterinary().getFirstName());
    }

    private Veterinary obtainSelectedVeterinary(List<Veterinary> veterinaryList) throws VeterinariesNotFoundException,
            InvalidInputException {

        if (veterinaryList.isEmpty()) {
            throw new VeterinariesNotFoundException();
        }
        try {
            return veterinaryList.get(Integer.parseInt(ioService.getUserInput()) - 1);
        } catch (Exception e) {
            throw new InvalidInputException();
        }
    }

    private Pet obtainSelectedPet(List<Pet> ownerPetList) throws NoPetsFoundForPhoneNumberException, InvalidInputException {
        if (ownerPetList.isEmpty()) {
            throw new NoPetsFoundForPhoneNumberException();
        }
        try {
            return ownerPetList.get(Integer.parseInt(ioService.getUserInput()) - 1);
        } catch (Exception e) {
            throw new InvalidInputException();
        }
    }

}
