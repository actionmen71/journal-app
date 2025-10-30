//package com.krishna.demo.controller;
//
//
//import com.krishna.demo.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.List;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//
//    private Map<Long,JournalEntry> journalEntries = new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry entry){
//        journalEntries.put(entry.getId(),entry);
//        return true;
//    }
//
//    @GetMapping("id/{requestid}")
//    public JournalEntry getJournalEntryById(@PathVariable Long requestid){
//        return journalEntries.get(requestid);
//    }
//
//    @DeleteMapping("id/{requestid}")
//    public JournalEntry deleteJournalEntryById(@PathVariable Long requestid){
//        return journalEntries.remove(requestid);
//    }
//
//    @PutMapping("id/{requestid}")
//    public JournalEntry updateJournalEntryById(@PathVariable Long requestid,@RequestBody JournalEntry entry){
//        return journalEntries.put(requestid,entry);
//    }
//}
