package org.popaqConnect.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.popaqConnect.data.models.Book;
import org.popaqConnect.data.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServiceImpTest {
    @Autowired
    BookServices bookServices;
    @Autowired
    BookRepository bookRepository;

    @AfterEach
    public void doThisAfterEachTest(){
        bookRepository.deleteAll();
    }

//    @Test
//    public void bookingCompleteTest(){
//        Book book = new Book();
//        book.setBookId("BO K");
//        book.setServiceProviderEmail("philipodey75@gmail.com");
//        bookServices.completeJob(book.getBookId(), book.getServiceProviderEmail());
//    }

}
