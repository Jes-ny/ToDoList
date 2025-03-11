package com.example.ToDoList.Controllers;

import com.example.ToDoList.Repositories.ToDoRepo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.ToDoList.Models.ToDoModel;

import static com.example.ToDoList.Controllers.LoginController.tokenStore;
@CrossOrigin
@RestController
public class AddToDoController {
    @Autowired
    ToDoRepo toDoRepo;

    @PostMapping("/addToDo")
    public ResponseEntity<?> addToDoList(@RequestHeader("Authorization") String token, @RequestBody ToDoModel todo) {
        if (token == null || !tokenStore.containsKey(token)) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        ToDoModel newToDo = toDoRepo.save(todo);
        Map<String, String> response = new HashMap<>();
        response.put("status", "Success");
        return ResponseEntity.ok(response);
    }
}
