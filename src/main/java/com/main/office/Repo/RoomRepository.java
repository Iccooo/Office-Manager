package com.main.office.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.office.Мodel.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByName(String name);
    Optional<Room> findById(Long id);
}