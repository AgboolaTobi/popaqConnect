package org.popaqConnect.services;


import org.popaqConnect.data.models.Job;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.dtos.requests.SearchByDRopTitleRequest;
import org.popaqConnect.data.models.Book;
import org.popaqConnect.dtos.requests.BookRequest;
import org.popaqConnect.dtos.requests.FindABookRequest;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.dtos.response.BookResponse;
import java.util.List;

public interface ClientService {

    void register(RegisterRequest registerRequest);

    List<Job> searchBYDropTitle(SearchByDRopTitleRequest search);
    void login(org.popaqConnect.dtos.requests.LoginRequest loginRequest);

    BookResponse bookServices(BookRequest bookRequest);

    List<Book> findAllBookingRequest(String mail);

    Book findABookRequest(FindABookRequest findABookRequest);
}
