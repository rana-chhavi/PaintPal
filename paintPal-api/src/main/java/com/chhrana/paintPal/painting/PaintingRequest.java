package com.chhrana.paintPal.painting;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PaintingRequest(
        Integer id,

        @NotNull(message = "1000")
        @NotEmpty(message = "1000")
        String title,

        @NotNull(message = "1001")
        @NotEmpty(message = "1001")
        String artist,

        @NotNull(message = "1002")
        @NotEmpty(message = "1002")
        String doi,

        @NotNull(message = "1003")
        @NotEmpty(message = "1003")
        String info,
        boolean shareable
) {
}
