package com.example.mysite.controller;

import com.example.mysite.classes.I_H;
import com.example.mysite.service.I_HService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ihs")
public class I_HController {
    @Autowired
    private I_HService i_hService;

    @GetMapping
    public List<I_H> getAllIHs() {
        return  i_hService.getALLIHs();
    }

    @GetMapping("/{identityId}/{handheldId}")
    public I_H getIDById(@PathVariable Integer identityId, @PathVariable Integer handheldId) {
        I_H.I_HKey i_hKey = new I_H.I_HKey();
        i_hKey.setIdentity_id(identityId);
        i_hKey.setHandheld_id(handheldId);
        return i_hService.getIHById(i_hKey);
    }

    @PostMapping
    public I_H createIH(@RequestBody I_H i_h) {
        return i_hService.saveIH(i_h);
    }

    @DeleteMapping("/{identityId}/{handheldId}")
    public void deleteID(@PathVariable Integer identityId, @PathVariable Integer handheldId) {
        I_H.I_HKey i_hKey = new I_H.I_HKey();
        i_hKey.setIdentity_id(identityId);
        i_hKey.setHandheld_id(handheldId);
        i_hService.deleteIH(i_hKey);
    }
}
