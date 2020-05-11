package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(){}

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntries = this.timeEntryRepository.list();
        return new ResponseEntity<>(timeEntries, HttpStatus.OK);
    }

    @GetMapping("/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable("timeEntryId") long timeEntryId) {
        TimeEntry timeEntry = this.timeEntryRepository.find(timeEntryId);

        if(timeEntry != null)
            return new ResponseEntity<>(timeEntry,HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        TimeEntry newTimeEntry = this.timeEntryRepository.create(timeEntry);
        return new ResponseEntity<>(newTimeEntry, HttpStatus.CREATED);
    }

    @PutMapping("/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable("timeEntryId") long timeEntryId,
                                            @RequestBody TimeEntry timeEntry) {

        TimeEntry updatedTimeEntry = this.timeEntryRepository.update(timeEntryId,timeEntry);

        if(updatedTimeEntry != null)
            return new ResponseEntity<>(updatedTimeEntry,HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable("timeEntryId") long timeEntryId) {
        this.timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
