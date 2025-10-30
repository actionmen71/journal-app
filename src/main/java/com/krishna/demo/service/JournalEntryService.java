package com.krishna.demo.service;

import com.krishna.demo.entity.JournalEntry;
import com.krishna.demo.entity.User;
import com.krishna.demo.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String  username){
        try{
        User byUserName = userService.findByUserName(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry savedEntry = journalEntryRepository.save(journalEntry);
        byUserName.getJournalEntries().add(savedEntry);
        userService.saveUser(byUserName);}
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }


    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id, String username){
        User byUserName = userService.findByUserName(username);
        byUserName.getJournalEntries().removeIf(x->x.getId().equals(id));
        userService.saveUser( byUserName);
        journalEntryRepository.deleteById(id);
    }

}




//controller --->service -----> repository