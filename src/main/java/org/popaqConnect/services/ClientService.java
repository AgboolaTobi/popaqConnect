package org.popaqConnect.services;

import org.popaqConnect.data.models.Job;
import org.popaqConnect.dtos.requests.RegisterRequest;
import org.popaqConnect.dtos.requests.SearchByCategory;
import org.popaqConnect.dtos.requests.SearchByDRopTitleRequest;

import java.util.List;

public interface ClientService {

    void register(RegisterRequest registerRequest);
    List<Job> searchBYDropTitle(SearchByDRopTitleRequest search);
    List<Job> searchByCategory(SearchByCategory search);
}
