package com.mymovieapi.mymovieapi.models;

import com.mymovieapi.mymovieapi.dao.PersistentObject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person implements PersistentObject<Long> {
    @Column(name = "id_imdb")
    private String imdbId;

    @Id
    @Column(name = "id_origin")
    private Long idOrigin;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "place_birth")
    private String placeOfBirth;

    public Long getId() {
        return idOrigin;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Long getIdOrigin() {
        return idOrigin;
    }

    public void setIdOrigin(Long idOrigin) {
        this.idOrigin = idOrigin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(imdbId, person.imdbId) &&
                Objects.equals(idOrigin, person.idOrigin) &&
                Objects.equals(name, person.name) &&
                Objects.equals(gender, person.gender) &&
                Objects.equals(placeOfBirth, person.placeOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imdbId, idOrigin, name, gender, placeOfBirth);
    }
}
