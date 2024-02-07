package org.example.entities;

import jakarta.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue
    private Integer petId;
    private String animalType;
    private String name;
    @Column(unique = true)
    private String collarId;
    @ManyToOne
    @JoinColumn
    private PetOwner petOwner;

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollarId() {
        return collarId;
    }

    public void setCollarId(String collarId) {
        this.collarId = collarId;
    }

    public PetOwner getPetOwner() {
        return petOwner;
    }

    public void setPetOwner(PetOwner petOwner) {
        this.petOwner = petOwner;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pet{");
        sb.append("petId=").append(petId);
        sb.append(", animalType='").append(animalType).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", collarId='").append(collarId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
