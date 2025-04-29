package com.example.goodoc.controller;

import com.example.goodoc.dto.releaseForm.ReleaseFormRequest;
import com.example.goodoc.dto.releaseForm.ReleaseFormResponse;
import com.example.goodoc.service.ReleaseFormService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/releaseForm")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReleaseFormController {
    private final ReleaseFormService releaseFormService;

    @GetMapping("/all")
    public List<ReleaseFormResponse> all() {
        return releaseFormService.all();
    }

    @GetMapping("/findById/{id}")
    public ReleaseFormResponse findById(@PathVariable Long id) {
        return releaseFormService.findById(id);
    }

    @PutMapping("/updateById/{id}")
    public void updateById(@PathVariable Long id, @RequestBody ReleaseFormRequest releaseFormRequest) {
        releaseFormService.updateById(id, releaseFormRequest);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        releaseFormService.deleteById(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody ReleaseFormRequest request) {
        releaseFormService.create(request);
    }
}
