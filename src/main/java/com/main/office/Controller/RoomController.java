package com.main.office.Controller;

import com.main.office.Service.RoomService;
import com.main.office.Мodel.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/status/{roomId}")
    public String showRoomStatus(@PathVariable Long roomId, Model model) {
    Optional<Room> room = roomService.getRoomById(roomId);
    model.addAttribute("room", room);
    return "roomStatus";
}

}
