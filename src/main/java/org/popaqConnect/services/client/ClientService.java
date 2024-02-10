package org.popaqConnect.services.client;


import org.popaqConnect.data.models.Job;
import org.popaqConnect.data.models.ServiceProvider;
import org.popaqConnect.dtos.requests.*;
import org.popaqConnect.dtos.requests.*;
import org.popaqConnect.data.models.Book;
import org.popaqConnect.dtos.response.BookResponse;
import java.util.List;

public interface ClientService {

    void register(RegisterRequest registerRequest);
    void login(ClientLoginRequest loginRequest);
    List<ServiceProvider> searchBYDropTitle(SearchByDRopTitleRequest search);
    BookResponse bookServices(BookRequest bookRequest);
    List<Book> viewAllBookingHistory(String mail);
    Book viewABookingHistory(FindABookRequest findABookRequest);
    List<ServiceProvider> searchByCategory(SearchByCategory searchByCategory);
    void cancelBookingRequest(ClientCancelBookingRequest cancelRequest);
    void update(ClientUpdateRequest clientUpdateRequest);
    void logout(ClientLogoutRequest clientLogoutRequest);
    void deleteAccount(ClientDeleteRequest clientDeleteRequest);
}
