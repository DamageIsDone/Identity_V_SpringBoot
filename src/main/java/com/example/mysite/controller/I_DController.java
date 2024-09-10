package com.example.mysite.controller;

import com.example.mysite.classes.Distinction;
import com.example.mysite.classes.I_D;
import com.example.mysite.classes.Identity;
import com.example.mysite.service.DistinctionService;
import com.example.mysite.service.I_DService;
import com.example.mysite.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ids")
public class I_DController {
    @Autowired
    private I_DService i_dService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private DistinctionService distinctionService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @PostMapping("/create")
    public I_D createIDByIds(@RequestParam Integer identity_id, @RequestParam Integer distinction_id) {
        I_D.I_DKey i_dKey = new I_D.I_DKey();
        i_dKey.setIdentity_id(identity_id);
        i_dKey.setDistinction_id(distinction_id);

        I_D i_d = new I_D();
        i_d.setI_dKey(i_dKey);

        Identity identity = identityService.getIdentityById(identity_id);
        Distinction distinction = distinctionService.getDistinctionById(distinction_id);

        i_d.setIdentity(identity);
        i_d.setDistinction(distinction);

        return i_dService.saveID(i_d);
    }

    @DeleteMapping("/{identityId}/{distinctionId}")
    public void deleteID(@PathVariable Integer identityId, @PathVariable Integer distinctionId) {
        I_D.I_DKey i_dKey = new I_D.I_DKey();
        i_dKey.setIdentity_id(identityId);
        i_dKey.setDistinction_id(distinctionId);
        i_dService.deleteID(i_dKey);
    }

    @DeleteMapping("/delete")
    public I_D deleteID(@RequestParam Integer distinction_id) {
        String selectSql = "SELECT * FROM I_D WHERE distinction_id = ?";
        I_D i_d = jdbcTemplate.queryForObject(selectSql, new BeanPropertyRowMapper<>(I_D.class), distinction_id);

        if (i_d != null) {
            String deleteSql = "DELETE FROM I_D WHERE distinction_id = ?";
            jdbcTemplate.update(deleteSql, distinction_id);
        }

        return i_d;
    }
}
