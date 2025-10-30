package com.krishna.demo.repository;

import com.krishna.demo.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry,Object> {
}
