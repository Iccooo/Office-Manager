package com.main.office.Controller;

import com.main.office.Service.RoomService;
import com.main.office.dto.ReservationRequest;
import com.main.office.Мodel.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/inUse")
    public String showRoomsInUse(Model model) {
        System.out.println("in-use endpoint");
        List<Room> roomsInUse = roomService.getRoomsInUse();
        model.addAttribute("roomsInUse", roomsInUse);
        return "roomsInUse";
    }

    @GetMapping("/")
    public String getAllRooms(Model model) {
        System.out.println("all rooms endpoint");
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "rooms";
    }

    @GetMapping("/status/{roomId}")
    public String showRoomStatus(@PathVariable Long roomId, Model model) {
        System.out.println("status/{roomId} endpoint");
        Optional<Room> room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);
        return "roomStatus";
    }

    @GetMapping("/reserve")
    public String showReservationForm(Model model) {
        model.addAttribute("reservationRequest", new ReservationRequest());
        return "reservation-form";
    }

    @PostMapping("/reserve")
    public String reserveRoom(@ModelAttribute("reservationRequest") ReservationRequest request, Model model) {
        Long roomId = request.getRoomId();
        LocalDateTime startTime = request.getStartTime();
        LocalDateTime endTime = request.getEndTime();

        try {
            roomService.reserveRoom(roomId, startTime, endTime);
            model.addAttribute("message", "Room reserved successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Failed to reserve room: " + e.getMessage());
        }
        return "reservation-result";
    }
}
