package com.example.demo.domain.image;

public record ImageResponseDTO(byte[] image) {
    public ImageResponseDTO(Image image) {
        this(image.getImage());
    }
}
