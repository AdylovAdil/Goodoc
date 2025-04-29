package com.example.goodoc.controller;

import com.example.goodoc.dto.reception.ReceptionRequest;
import com.example.goodoc.dto.reception.ReceptionResponse;
import com.example.goodoc.service.ReceptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reception")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReceptionController {
    private final ReceptionService receptionService;

    @GetMapping("/all")
    public List<ReceptionResponse> all() {
        return receptionService.all();
    }

    @GetMapping("/findById/{id}")
    public ReceptionResponse findById(@PathVariable Long id) {
        return receptionService.findById(id);
    }

    @PutMapping("/updateById/{id}")
    public void updateById(@PathVariable Long id, @RequestBody ReceptionRequest receptionRequest) {
        receptionService.updateById(id, receptionRequest);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        receptionService.deleteById(id);
    }


    @PostMapping("/create")
    public void create(@RequestBody ReceptionRequest request) {
        receptionService.create(request);
    }
}
