package com.main.office.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.main.office.model.Availability;

@Controller
public class AvailabilityController {
    @GetMapping("/editAvailability/{roomId}")
    public String editAvailability(@PathVariable Long roomId, Model model) {
        // Retrieve availability for the specified room
        // Pass availability data to the view
        return "editAvailability";
    }

    @PostMapping("/saveAvailability")
    public String saveAvailability(Availability availability) {
        // Save or update availability data in the database
        return "redirect:/rooms";
    }
}