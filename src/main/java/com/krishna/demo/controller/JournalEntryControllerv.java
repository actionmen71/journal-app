package com.krishna.demo.controller;


import com.krishna.demo.entity.JournalEntry;
import com.krishna.demo.entity.User;
import com.krishna.demo.service.JournalEntryService;
import com.krishna.demo.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;


    /*@GetMapping("{username}")
    public ResponseEntity<?> getAllEntriesByUser(@PathVariable String username)*/
    @GetMapping()
    public ResponseEntity<?> getAllEntriesByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User byUserName = userService.findByUserName(username);
        List<JournalEntry> all = byUserName.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntryService.saveEntry(entry, username);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{requestid}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId requestid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        List<JournalEntry> collect = user.getJournalEntries().stream()
                .filter(x -> x.getId().equals(requestid))
                .collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(requestid);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("id/{requestid}")
    public ResponseEntity<JournalEntry> deleteJournalEntryById(@PathVariable ObjectId requestid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean removed = journalEntryService.deleteById(requestid, username);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("id/{requestid}")
    public ResponseEntity<JournalEntry> updateJournalEntryById(@PathVariable ObjectId requestid, @RequestBody JournalEntry modifiedEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        List<JournalEntry> collect = user.getJournalEntries().stream()
                .filter(x -> x.getId().equals(requestid))
                .collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(requestid);
            if (journalEntry.isPresent()) {
                JournalEntry existingEntry = journalEntry.get();
                existingEntry.setTitle(modifiedEntry.getTitle() != null && !modifiedEntry.getTitle().isEmpty() ? modifiedEntry.getTitle() : existingEntry.getTitle());
                existingEntry.setContent(modifiedEntry.getContent() != null && !modifiedEntry.getContent().isEmpty() ? modifiedEntry.getContent() : existingEntry.getContent());
                journalEntryService.saveEntry(existingEntry);
                return new ResponseEntity<>(existingEntry, HttpStatus.OK);

            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
