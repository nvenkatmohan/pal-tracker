package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    protected Map<Long,TimeEntry> timeEntryMap = new LinkedHashMap<Long,TimeEntry>();
    protected long timeEntryId = 1L;
    protected boolean isFirstTimeEntry = true;

    @Override
    public List<TimeEntry> list() {
        return this.timeEntryMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return this.timeEntryMap.get(timeEntryId);
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(this.timeEntryId++);
        System.out.println("Time Entry Id is "+timeEntry.getId());
        this.timeEntryMap.put(timeEntry.getId(),timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {

        if(timeEntryMap.containsKey(timeEntryId)) {
            timeEntry.setId(timeEntryId);
            timeEntryMap.put(timeEntryId, timeEntry);
            return timeEntry;
        }

        return null;
    }

    @Override
    public void delete(long timeEntryId) {
        timeEntryMap.remove(timeEntryId);
    }
}
