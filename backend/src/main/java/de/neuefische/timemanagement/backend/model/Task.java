package de.neuefische.timemanagement.backend.model;

import java.time.LocalDateTime;

public record Task(String id,String title,LocalDateTime dateTime) {
}
