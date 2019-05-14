package com.mymovieapi.mymovieapi.models;


import com.mymovieapi.mymovieapi.dao.PersistentObject;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AudioVisualMedia implements PersistentObject<Long> {


    @Id
    @Column(name = "id_origin")
    private Long idOrigin;

    @Column(name = "title")
    private String title;

    @Column(name = "overview",length = 2000)
    private String overview;

    @Column(name = "language")
    private String originalLanguage;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "runtime")
    private int runtime;

    public Long getId() {
        return idOrigin;
    }

    public Long getIdOrigin() {
        return idOrigin;
    }

    public void setIdOrigin(Long idOrigin) {
        this.idOrigin = idOrigin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AudioVisualMedia that = (AudioVisualMedia) o;
        return Objects.equals(idOrigin, that.idOrigin) &&
                Objects.equals(title, that.title) &&
                Objects.equals(overview, that.overview) &&
                Objects.equals(originalLanguage, that.originalLanguage) &&
                Objects.equals(releaseDate, that.releaseDate) &&
                Objects.equals(runtime, that.runtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrigin, title, overview, originalLanguage, releaseDate, runtime);
    }
}
