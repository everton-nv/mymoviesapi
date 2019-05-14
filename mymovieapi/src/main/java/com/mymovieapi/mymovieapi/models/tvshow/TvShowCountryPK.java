package com.mymovieapi.mymovieapi.models.tvshow;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TvShowCountryPK implements Serializable {
    @Column(name = "tvshow_id", nullable = false)
    private Long tvShowId;

    @Column(name = "country_id", nullable = false)
    private Long countryId;

    public Long getTvShowId() {
        return tvShowId;
    }

    public void setTvShowId(Long tvShowId) {
        this.tvShowId = tvShowId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TvShowCountryPK that = (TvShowCountryPK) o;
        return Objects.equals(tvShowId, that.tvShowId) &&
                Objects.equals(countryId, that.countryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tvShowId, countryId);
    }
}
