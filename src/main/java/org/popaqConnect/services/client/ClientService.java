package org.popaqConnect.services.client;


import org.popaqConnect.data.models.Job;
import org.popaqConnect.dtos.requests.*;
import org.popaqConnect.data.models.Book;
import org.popaqConnect.dtos.response.BookResponse;
import java.util.List;

public interface ClientService {

    void register(RegisterRequest registerRequest);

    void login(ClientLoginRequest loginRequest);
    List<Job> searchBYDropTitle(SearchByDRopTitleRequest search);

    BookResponse bookServices(BookRequest bookRequest);

    List<Book> viewAllBookingHistory(String mail);

    Book viewABookingHistory(FindABookRequest findABookRequest);

    void update(ClientUpdateRequest clientUpdateRequest);

    void logout(ClientLogoutRequest clientLogoutRequest);

    void deleteAccount(ClientDeleteRequest clientDeleteRequest);
}
