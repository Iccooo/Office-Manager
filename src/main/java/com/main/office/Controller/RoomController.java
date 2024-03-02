package com.main.office.Controller;

import com.main.office.Service.RoomService;
import com.main.office.Мodel.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/inUse")
    public String showRoomsInUse(Model model) {
        List<Room> roomsInUse = roomService.getRoomsInUseNow();
        model.addAttribute("roomsInUse", roomsInUse);
        return "roomsInUse";
    }
    @GetMapping("/status")
    public String showRooms(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "rooms";
    }
}
