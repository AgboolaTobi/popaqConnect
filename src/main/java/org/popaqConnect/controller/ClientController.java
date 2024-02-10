package org.popaqConnect.controller;

import org.popaqConnect.dtos.requests.SearchByCategory;
import org.popaqConnect.dtos.requests.SearchByDRopTitleRequest;
import org.popaqConnect.dtos.response.ApiResponse;
import org.popaqConnect.dtos.response.SearchServiceProviderByTitleResponse;
import org.popaqConnect.dtos.response.SearchByCategoryResponse;
import org.popaqConnect.exceptions.PopaqConnectException;
import org.popaqConnect.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {


    @Autowired
    private ClientService clientService;


    @GetMapping ("/searchServiceProviderByTitle")
    public ResponseEntity<?> searchServiceProviderByTitle(@RequestBody SearchByDRopTitleRequest search) {
        SearchServiceProviderByTitleResponse response = new SearchServiceProviderByTitleResponse();
        try {
            response.setServiceProviders(clientService.searchBYDropTitle(search));
            return new ResponseEntity<>(new ApiResponse(true,response),HttpStatus.FOUND);
        }catch(PopaqConnectException exception){
            response.setServiceProviders(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,response),HttpStatus.NOT_IMPLEMENTED);
        }
    }
    @GetMapping("/searchServiceProviderByCategory")
    public ResponseEntity<?> searchServiceProviderByCategory(@RequestBody SearchByCategory searchByCategory){
        SearchByCategoryResponse searchByCategoryResponse = new SearchByCategoryResponse();
        try{
            searchByCategoryResponse.setServiceProviderList(clientService.searchByCategory(searchByCategory));
            return new ResponseEntity<>(new ApiResponse(true,searchByCategory),HttpStatus.FOUND);
        }catch (PopaqConnectException exception){
            searchByCategoryResponse.setServiceProviderList(exception.getMessage());
            return new ResponseEntity<>(new ApiResponse(false,searchByCategoryResponse),HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
