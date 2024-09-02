package com.example.mysite.controller;

import com.example.mysite.classes.Identity;
import com.example.mysite.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/identities")
public class IdentityController {

    @Autowired
    private IdentityService identityService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Identity> getAllIdentities() {
        String sql="SELECT * FROM identity";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Identity.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Identity> getIdentityById(@PathVariable Integer id) {
        Optional<Identity> identity = identityService.findById(id);
        return identity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Identity createIdentity(@RequestBody Identity identity) {
        return identityService.save(identity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Identity> updateIdentity(@PathVariable Integer id, @RequestBody Identity identity) {
        if (!identityService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        identity.setIdentity_id(id);
        return ResponseEntity.ok(identityService.save(identity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIdentity(@PathVariable Integer id) {
        if (!identityService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        identityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getIdentitiesCount() {
        long count = identityService.countAllIdentities();
        return ResponseEntity.ok(count);
    }
}
