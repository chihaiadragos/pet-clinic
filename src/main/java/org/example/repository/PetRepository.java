package org.example.repository;

import org.example.entities.Pet;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PetRepository extends Repository<Pet> {

    public List<Pet> getAllPets() {
        Session session = sessionFactory.openSession();
        Query<Pet> query = session.createQuery("select p from Pet p");
        return query.getResultList();
    }
}
