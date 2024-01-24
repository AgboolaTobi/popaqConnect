package org.popaqConnect.data.repositories;

import org.popaqConnect.data.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book,String> {
}
