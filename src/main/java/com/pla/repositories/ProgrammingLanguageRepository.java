package com.pla.repositories;

import com.pla.models.ProgrammingLanguage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProgrammingLanguageRepository extends MongoRepository<ProgrammingLanguage, String> {

}
