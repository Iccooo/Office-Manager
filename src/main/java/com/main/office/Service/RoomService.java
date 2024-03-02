package com.main.office.Service;

import com.main.office.Repo.AvailabilityRepository;
import com.main.office.Repo.RoomRepository;
import com.main.office.Мodel.Availability;
import com.main.office.Мodel.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    private AvailabilityRepository availabilityRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
    public List<Room> getRoomsInUseNow() {
        List<Availability> availabilitiesInUse = availabilityRepository.findAvailabilitiesInUseNow();
        Set<Room> roomsInUse = availabilitiesInUse.stream()
                .map(Availability::getRoom)
                .collect(Collectors.toSet());
        return new ArrayList<>(roomsInUse);
    }
}