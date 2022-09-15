package com.example.Restaurant.Controllers;

import com.example.Restaurant.Entitys.State;
import com.example.Restaurant.repositorys.StateRepository;
import com.example.Restaurant.domain.Services.StateRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {
    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateRegistrationService stateRegistrationService;

    @GetMapping
    public List<State> list() {
        return stateRepository.all();
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> find(@PathVariable("stateId") Long id) {
        State state = stateRepository.perId(id);
        if (state != null) {
            return ResponseEntity.ok(state);
        } else {
//        return ResponseEntity.status(HttpStatus.FOUND).build();
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public State add(@RequestBody State state) {
        return stateRegistrationService.add(state);
    }
}
