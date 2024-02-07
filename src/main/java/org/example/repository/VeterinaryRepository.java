package org.example.repository;

import org.example.entities.Veterinary;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class VeterinaryRepository extends Repository<Veterinary>{

    public List<Veterinary> getAllVeterinaries() {
        Session session = sessionFactory.openSession();
        Query<Veterinary> query = session.createQuery("select v from Veterinary v");
        return query.getResultList();
    }
}