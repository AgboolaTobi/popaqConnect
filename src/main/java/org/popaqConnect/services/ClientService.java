package org.popaqConnect.services;

import org.popaqConnect.data.models.Book;
import org.popaqConnect.dtos.requests.BookRequest;
import org.popaqConnect.dtos.requests.ClientLoginRequest;
import org.popaqConnect.dtos.requests.FindABookRequest;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.dtos.response.BookResponse;

import java.util.List;

public interface ClientService {

    void register(RegisterRequest registerRequest);

    void login(ClientLoginRequest loginRequest);

    BookResponse bookServices(BookRequest bookRequest);

    List<Book> findAllBookingRequest(String mail);

    Book findABookRequest(FindABookRequest findABookRequest);
}
