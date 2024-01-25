package com.maryj.authenticationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmailDetail {
    private  String recipient;
    private  String messageBody;
    private  String subject;
}
