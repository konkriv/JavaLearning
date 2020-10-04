package main.model;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Entries {
    final private List<Entry> entries;

    public Entries() {
        this.entries = new ArrayList<>();
    }

    void addEntry(Entry entry) {
        entries.add(entry); //Почему не ругается на final поле ?
    }

    Collection<Entry> from(LocalDate date) {
        return entries.stream().filter(entry -> entry.getTime().toLocalDate().isAfter(date)).collect(Collectors.toList());
    }

    Collection<Entry> to (LocalDate date) {
        return entries.stream().filter(entry -> entry.getTime().toLocalDate().isBefore(date)).collect(Collectors.toList());
    }

    Collection<Entry> betweenDates(LocalDate from, LocalDate to) {
        return entries.stream().filter(entry -> entry.getTime().toLocalDate().isAfter(from) && entry.getTime().toLocalDate().isBefore(to)).collect(Collectors.toList());
    }


    Entry last() {
        return entries.get(entries.size() - 1);
    }

    List<Entry> getEntries() {
        return entries;
    }
}

