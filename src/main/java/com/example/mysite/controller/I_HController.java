package com.example.mysite.controller;

import com.example.mysite.classes.*;
import com.example.mysite.service.HandheldService;
import com.example.mysite.service.I_HService;
import com.example.mysite.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ihs")
public class I_HController {
    @Autowired
    private I_HService i_hService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private HandheldService handheldService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @PostMapping("/create")
    public I_H createIHByIds(@RequestParam Integer identity_id, @RequestParam Integer handheld_id) {
        I_H.I_HKey i_hKey = new I_H.I_HKey();
        i_hKey.setIdentity_id(identity_id);
        i_hKey.setHandheld_id(handheld_id);

        I_H i_h = new I_H();
        i_h.setI_hKey(i_hKey);

        Identity identity = identityService.getIdentityById(identity_id);
        Handheld handheld = handheldService.getHandheldById(handheld_id);

        i_h.setIdentity(identity);
        i_h.setHandheld(handheld);

        return i_hService.saveIH(i_h);
    }

    @DeleteMapping("/{identityId}/{handheldId}")
    public void deleteID(@PathVariable Integer identityId, @PathVariable Integer handheldId) {
        I_H.I_HKey i_hKey = new I_H.I_HKey();
        i_hKey.setIdentity_id(identityId);
        i_hKey.setHandheld_id(handheldId);
        i_hService.deleteIH(i_hKey);
    }

    @DeleteMapping("/delete")
    public I_H deleteIH(@RequestParam Integer handheld_id) {
        String selectSql = "SELECT * FROM I_H WHERE handheld_id = ?";
        I_H i_h = jdbcTemplate.queryForObject(selectSql, new BeanPropertyRowMapper<>(I_H.class), handheld_id);

        if (i_h != null) {
            String deleteSql = "DELETE FROM I_H WHERE handheld_id = ?";
            jdbcTemplate.update(deleteSql, handheld_id);
        }

        return i_h;
    }

}
