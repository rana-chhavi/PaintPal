package com.chhrana.paintPal.review;

import jakarta.validation.constraints.*;

public record ReviewRequest(
        @Positive(message="2000")
        @Min(value = 0, message = "20001")
        @Max(value = 5, message="2002")
        Double note,

        @NotNull(message = "2003")
        @NotEmpty(message = "2003")
        @NotBlank(message = "2003")
        String review,

        @NotNull(message = "2004")
        Integer paintingId
) {
}
