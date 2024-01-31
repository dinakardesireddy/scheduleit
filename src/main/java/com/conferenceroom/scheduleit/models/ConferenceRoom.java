package com.conferenceroom.scheduleit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "conference_rooms")
public class ConferenceRoom {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "conferenceRoom", fetch = FetchType.LAZY)
    private List<MaintenanceInterval> maintenanceTimeIntervals;
}
