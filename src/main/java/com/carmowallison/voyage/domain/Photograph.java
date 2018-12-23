package com.carmowallison.voyage.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Photograph implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String description;
	private boolean active;
	private Double latitude;
	private Double longitude;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Photograph() {}

    public Photograph(Integer id, String title, String description, boolean active,Category category,Double latitude,Double longitude) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.active = active;
        this.category=category;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Photograph that = (Photograph) o;
		return active == that.active &&
				Objects.equals(id, that.id) &&
				Objects.equals(title, that.title) &&
				Objects.equals(description, that.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, description, active);
	}
}
