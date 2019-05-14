package com.mymovieapi.mymovieapi.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @Column(name = "id_origin")
    private Long idOrigin;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdOrigin() {
        return idOrigin;
    }

    public void setIdOrigin(Long idOrigin) {
        this.idOrigin = idOrigin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(idOrigin, genre.idOrigin) &&
                Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrigin, name);
    }
}
