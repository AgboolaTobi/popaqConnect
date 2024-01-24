package org.popaqConnect.dtos.requests;

import lombok.Data;

@Data
public class FindABookRequest {
    private String email;
    private  String bookId;
}
