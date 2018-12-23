package com.carmowallison.voyage.dto;

import com.carmowallison.voyage.domain.Category;
import com.carmowallison.voyage.domain.Photograph;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PhotographDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Length(min = 5, max = 255, message = "O título precisa ser de 5 a 255 caracter!")
    private String title;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Length(min = 5, max = 1000, message = "O campo descrição precisa ser de 5 a 1000 caracter!")
    private String description;

    @NotNull(message = "A latitude não pode ser vazia!")
    private Double latitude;

    @NotNull(message = "A latitude não pode ser vazia!")
    private Double longitude;

    private Category category;

    private boolean active;

    public PhotographDTO() { }

    public PhotographDTO(Photograph obj) {
        id = obj.getId();
        title = obj.getTitle();
        description = obj.getDescription();
        category = obj.getCategory();
        active = obj.isActive();
        latitude = obj.getLatitude();
        longitude = obj.getLongitude();
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
