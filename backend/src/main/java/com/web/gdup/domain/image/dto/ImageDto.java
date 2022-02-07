package com.web.gdup.domain.image.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "image")
public class ImageDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    private String imageName;

    private String newImageName;

    private String imagePath;

    @Builder
    public ImageDto(int imageId, String imageName, String newImageName, String imagePath) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.newImageName = newImageName;
        this.imagePath = imagePath;
    }
}