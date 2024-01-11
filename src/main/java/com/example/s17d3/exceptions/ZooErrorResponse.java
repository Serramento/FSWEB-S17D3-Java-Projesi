package com.example.s17d3.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZooErrorResponse {
    private Integer status;
    private String message;
    private Long timestamp;
}
