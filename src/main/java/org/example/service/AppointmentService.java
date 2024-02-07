package org.example.service;

import org.example.entities.Appointment;
import org.example.entities.Pet;
import org.example.entities.Veterinary;
import org.example.repository.AppointmentRepository;
import org.example.repository.PetRepository;
import org.example.repository.VeterinaryRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class AppointmentService {
    private IOService ioService = new IOService();
    private PetRepository petRepository = new PetRepository();
    private VeterinaryRepository veterinaryRepository = new VeterinaryRepository();
    private AppointmentRepository appointmentRepository = new AppointmentRepository();
//TODO de refacut clasa asta conform PetOwnerService -> metodele addNewAppointment si createAppointment
    public void addNewAppointment() {
        ioService.displayToConsoleNextLine("What is your phone number?");
        String ownerPhoneNumber = ioService.getUserInput();
        List<Pet> ownerPetList = petRepository.findByOwnerPhoneNumber(ownerPhoneNumber);
        ioService.displayPetOptions(ownerPetList);
        int petNumber = Integer.parseInt(ioService.getUserInput()) - 1;
        Pet selectedPet = ownerPetList.get(petNumber);
        List<Veterinary> veterinaryList = veterinaryRepository.getAllVeterinaries();
        ioService.displayVeterinaryOptions(veterinaryList);
        int veterinaryNumber = Integer.parseInt(ioService.getUserInput()) - 1;
        Veterinary selectedVeterinary = veterinaryList.get(veterinaryNumber);
        ioService.displayToConsoleNextLine("What is the date of your appointment?");
        String appointmentDate = ioService.getUserInput();
        ioService.displayToConsoleNextLine("What is the time of your appointment");
        String appointmentTime = ioService.getUserInput();
        LocalDate appointmentLocalDate = LocalDate.parse(appointmentDate);
        LocalTime appointmentLocalTime = LocalTime.parse(appointmentTime);
        LocalDateTime appointmentLocalDateTime = LocalDateTime.of(appointmentLocalDate, appointmentLocalTime);
        Appointment appointment = new Appointment();
        appointment.setVeterinary(selectedVeterinary);
        appointment.setPet(selectedPet);
        appointment.setDateTime(appointmentLocalDateTime);
        appointmentRepository.save(appointment);
        ioService.displayToConsoleNextLine("Your appointment was successfully added");
    }
}
