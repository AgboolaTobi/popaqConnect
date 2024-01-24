package org.popaqConnect.dtos.requests;

import lombok.Data;
import org.popaqConnect.data.BookType;

@Data
public class BookRequest {
    private String clientEmail;
   private String serviceProviderEmail;
    private String description;
    private BookType acceptedProject;
    private String time;
}
