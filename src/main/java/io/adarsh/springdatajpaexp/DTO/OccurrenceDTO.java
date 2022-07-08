package io.adarsh.springdatajpaexp.DTO;

public class OccurrenceDTO {
    int firstOccurrence = Integer.MAX_VALUE;
    int lastOccurrence = Integer.MIN_VALUE;

    public int getFirstOccurrence() {
        return firstOccurrence;
    }

    public void setFirstOccurrence(int firstOccurrence) {
        this.firstOccurrence = firstOccurrence;
    }

    public int getLastOccurrence() {
        return lastOccurrence;
    }

    public void setLastOccurrence(int lastOccurrence) {
        this.lastOccurrence = lastOccurrence;
    }
}
