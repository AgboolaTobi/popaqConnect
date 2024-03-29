package org.popaqConnect.data.models;

import lombok.Data;
import org.popaqConnect.data.BookType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Book{
    @Id
    private String bookId;
    private String description;
    private BookType projectStatus = BookType.NOTACCEPTED;
    private String time;
    private String serviceProviderEmail;
    private String clientEmail;
}
