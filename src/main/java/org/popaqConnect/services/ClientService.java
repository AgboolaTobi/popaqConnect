package org.popaqConnect.services;

<<<<<<< HEAD
import org.popaqConnect.dtos.requests.LoginRequest;
=======

import org.popaqConnect.data.models.Job;
>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc
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

<<<<<<< HEAD
    void login(LoginRequest loginRequest);
=======
    List<Job> searchBYDropTitle(SearchByDRopTitleRequest search);
    void login(org.popaqConnect.dtos.requests.LoginRequest loginRequest);

    BookResponse bookServices(BookRequest bookRequest);

    List<Book> findAllBookingRequest(String mail);

    Book findABookRequest(FindABookRequest findABookRequest);
>>>>>>> c27a60656f073947c54bf5ed215985e256b3c8dc
}
