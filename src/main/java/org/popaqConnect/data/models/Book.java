package org.popaqConnect.data.models;

import lombok.Data;
import org.popaqConnect.data.BookType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Book{
    @Id
    private String id;
    private String description;
    private BookType acceptedProject = BookType.NOTACCEPTED;
    private String time;
    private String serviceProviderEmail;
    private String bookId;
    private String clientEmail;
}
