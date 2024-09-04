package com.example.mysite.controller;

import com.example.mysite.classes.I_D;
import com.example.mysite.service.I_DService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ids")
public class I_DController {
    @Autowired
    private I_DService i_dService;

    @GetMapping
    public List<I_D> getAllIDs() {
        return i_dService.getAllIDs();
    }

    @GetMapping("/{identityId}/{distinctionId}")
    public I_D getIDById(@PathVariable Integer identityId, @PathVariable Integer distinctionId) {
        I_D.I_DKey i_dKey = new I_D.I_DKey();
        i_dKey.setIdentity_id(identityId);
        i_dKey.setDistinction_id(distinctionId);
        return i_dService.getIDById(i_dKey);
    }

    @PostMapping
    public I_D createID(@RequestBody I_D i_d) {
        return i_dService.saveID(i_d);
    }

    @DeleteMapping("/{identityId}/{distinctionId}")
    public void deleteID(@PathVariable Integer identityId, @PathVariable Integer distinctionId) {
        I_D.I_DKey i_dKey = new I_D.I_DKey();
        i_dKey.setIdentity_id(identityId);
        i_dKey.setDistinction_id(distinctionId);
        i_dService.deleteID(i_dKey);
    }
}
