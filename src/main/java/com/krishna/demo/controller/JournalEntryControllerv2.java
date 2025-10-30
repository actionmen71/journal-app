//package com.krishna.demo.controller;
//
//
//import com.krishna.demo.entity.JournalEntry;
//import com.krishna.demo.service.JournalEntryService;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.time.LocalDateTime;
//import java.util.*;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryControllerv2 {
//
//    @Autowired
//    private JournalEntryService journalEntryService;
//
//    @GetMapping
//    public ResponseEntity<?> getAll() {
//        List<JournalEntry> all = journalEntryService.getAll();
//        if (all != null && !all.isEmpty()) {
//            return new ResponseEntity<>(all,HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
//        try {
//            entry.setDate(LocalDateTime.now());
//            journalEntryService.saveEntry(entry);
//            return new ResponseEntity<>(entry,HttpStatus.CREATED);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("id/{requestid}")
//    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId requestid) {
//        Optional<JournalEntry> journalEntry = journalEntryService.findById(requestid);
//        if(journalEntry.isPresent()){
//            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//    }
//
//
//    @DeleteMapping("id/{requestid}")
//    public ResponseEntity<JournalEntry> deleteJournalEntryById(@PathVariable ObjectId requestid) {
//        journalEntryService.deleteById(requestid);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//
//    @PutMapping("id/{requestid}")
//    public ResponseEntity<JournalEntry> updateJournalEntryById(@PathVariable ObjectId requestid, @RequestBody JournalEntry modifiedEntry) {
//        JournalEntry existingEntry = journalEntryService.findById(requestid).orElse(null);
//        if (existingEntry != null){
//            existingEntry.setTitle(modifiedEntry.getTitle()!=null && !modifiedEntry.getTitle().isEmpty() ? modifiedEntry.getTitle() : existingEntry.getTitle());
//            existingEntry.setContent(modifiedEntry.getContent()!=null && !modifiedEntry.getContent().isEmpty()? modifiedEntry.getContent() : existingEntry.getContent());
//            journalEntryService.saveEntry(existingEntry);
//            return new ResponseEntity<>(existingEntry,HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//}
