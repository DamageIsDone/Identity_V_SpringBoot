package com.example.mysite.controller;

import com.example.mysite.classes.I_S;
import com.example.mysite.service.I_SService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iss")
public class I_SController {
    @Autowired
    private I_SService i_sService;

    @GetMapping
    public List<I_S> getAllISs() {
        return  i_sService.getALLISs();
    }

    @GetMapping("/{identityId}/{skillId}")
    public I_S getIDById(@PathVariable Integer identityId, @PathVariable Integer skillId) {
        I_S.I_SKey i_sKey = new I_S.I_SKey();
        i_sKey.setIdentity_id(identityId);
        i_sKey.setSkill_id(skillId);
        return i_sService.getISById(i_sKey);
    }

    @PostMapping
    public I_S createIS(@RequestBody I_S i_s) {
        return i_sService.saveIS(i_s);
    }

    @DeleteMapping("/{identityId}/{skillId}")
    public void deleteID(@PathVariable Integer identityId, @PathVariable Integer skillId) {
        I_S.I_SKey i_sKey = new I_S.I_SKey();
        i_sKey.setIdentity_id(identityId);
        i_sKey.setSkill_id(skillId);
        i_sService.deleteIS(i_sKey);
    }
}
