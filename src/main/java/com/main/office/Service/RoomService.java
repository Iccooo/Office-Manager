package com.main.office.Service;

import com.main.office.Repo.AvailabilityRepository;
import com.main.office.Repo.RoomRepository;
import com.main.office.Мodel.Availability;
import com.main.office.Мodel.Room;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public List<Room> getRoomsInUse() {
        List<Room> allRooms = roomRepository.findAll();
        return allRooms.stream()
                .filter(room -> room.getAvailabilities().stream()
                        .anyMatch(Availability::isInUseNow))
                .collect(Collectors.toList());
    }

    public Optional<Room> getRoomById(Long roomId) {
        return roomRepository.findById(roomId);
    }

    public void reserveRoom(Long roomId, LocalDateTime startTime, LocalDateTime endTime) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + roomId));

        List<Availability> availabilities = room.getAvailabilities();
        
        if (isRoomAvailable(availabilities, startTime, endTime)) {
            Availability newAvailability = new Availability();
            newAvailability.setStartTime(startTime);
            newAvailability.setEndTime(endTime);
            newAvailability.setRoom(room);
            availabilities.add(newAvailability);
            roomRepository.save(room);
        } else {
            throw new IllegalArgumentException("Room is not available for the specified time slot.");
        }
    }

    private boolean isRoomAvailable(List<Availability> availabilities, LocalDateTime startTime, LocalDateTime endTime) {
        for (Availability availability : availabilities) {
            LocalDateTime existingStart = availability.getStartTime();
            LocalDateTime existingEnd = availability.getEndTime();
            if ((startTime.isAfter(existingStart) && startTime.isBefore(existingEnd)) ||
                    (endTime.isAfter(existingStart) && endTime.isBefore(existingEnd))) {
                return false; // not available
            }
        }
        return true;
    }
}