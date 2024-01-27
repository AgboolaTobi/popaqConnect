package org.popaqConnect.services.Booking;

import org.popaqConnect.data.models.Book;
import org.popaqConnect.data.BookType;
import org.popaqConnect.data.repositories.BookRepository;
import org.popaqConnect.dtos.requests.AcceptBookingRequest;
import org.popaqConnect.dtos.requests.BookRequest;
import org.popaqConnect.utils.GenerateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookServices {
    @Autowired
    BookRepository bookRepository;
    @Override
    public String save(BookRequest bookRequest) {
        Book book = new Book();
        String generatedId = "BK-" + GenerateId.generateId();
        book.setDescription(bookRequest.getDescription());
        book.setClientEmail(bookRequest.getClientEmail());
        book.setServiceProviderEmail(bookRequest.getServiceProviderEmail());
        book.setTime(bookRequest.getTime());
        book.setBookId(generatedId);
        bookRepository.save(book);
        return book.getBookId();

    }

    @Override
    public List<Book> findAllBookingRequest(String userEmail) {
        List<Book> booking = new ArrayList<>();
        for(Book bookingRequest:bookRepository.findAll()){
            if(bookingRequest.getClientEmail().equals(userEmail)) {
                booking.add(bookingRequest);
            }
          if(bookingRequest.getServiceProviderEmail().equals(userEmail)){
            booking.add(bookingRequest);}
        }
        return booking;
    }

    @Override
    public Book findABookingRequest(String bookId, String userEmail) {
        List<Book> booking = findAllBookingRequest(userEmail);
        for(Book bookRequest: booking){
            if(bookRequest.getBookId().equals(bookId)) return bookRequest;

        }
        return null;
    }

    @Override
    public void setBookType(String serviceProvider, AcceptBookingRequest bookingRequest) {
        Book bookRequest = findABookingRequest(bookingRequest.getId(),serviceProvider);
        for(BookType bookType:BookType.values()){
            if(bookType.name().toLowerCase().equalsIgnoreCase(bookingRequest.getResponse().toLowerCase())) {
                bookRequest.setAcceptedProject(bookType);
            }
        }
        bookRepository.save(bookRequest);
    }

    private List<Book> findAllBooking(){
        List<Book> allBooking = new ArrayList<>();
        for(Book booking : bookRepository.findAll()){
            allBooking.add(booking);
        }
        return allBooking;
    }

}
