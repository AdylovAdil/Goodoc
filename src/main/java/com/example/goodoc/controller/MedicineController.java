package com.example.goodoc.controller;

import com.example.goodoc.dto.medicine.MedicineRequest;
import com.example.goodoc.dto.medicine.MedicineResponse;
import com.example.goodoc.service.MedicineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/medicine")
public class MedicineController {
    private final MedicineService medicineService;

    @GetMapping("/all")
    public List<MedicineResponse> all() {
        return medicineService.all();
    }

    @GetMapping("/findById/{id}")
    public MedicineResponse findById(@PathVariable Long id) {
        return medicineService.findById(id);
    }

    @PutMapping("/updateById/{id}")
    public void updateById(@PathVariable Long id, @RequestBody MedicineRequest medicineRequest) {
        medicineService.updateById(id, medicineRequest);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        medicineService.deleteById(id);
    }

    @PostMapping("/addMedicine")
    public void addMedicine(@RequestBody MedicineRequest medicineRequest) {
        medicineService.addMedicine(medicineRequest);
    }
}
