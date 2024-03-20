package com.example.demo.domain.image;

public record ImageResponseDTO(String name, byte[] image) {
    public ImageResponseDTO(Image image) {
        this(image.getName(), image.getImage());
    }

}
