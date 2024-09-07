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
        String sql = "SELECT * FROM identity WHERE identity_id = ?";
        List<Identity> identities = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Identity.class));
        return identities.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(identities.get(0));
    }

    @PostMapping
    public Identity createIdentity(@RequestBody Identity identity) {
        String sql = "INSERT INTO Identity (career, name, camp, gender, birthday, picture) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, identity.getCareer(), identity.getName(), identity.getCamp(), identity.getGender(), identity.getBirthday(), identity.getPicture());
        return identity;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Identity> updateIdentity(@PathVariable Integer id, @RequestBody Identity identity) {
        String sqlCheck = "SELECT COUNT(*) FROM identity WHERE identity_id = ?";
        int count = jdbcTemplate.queryForObject(sqlCheck, new Object[]{id}, Integer.class);
        if (count == 0) {
            return ResponseEntity.notFound().build();
        }
        String sqlUpdate = "UPDATE identity SET career = ?, name = ?, camp = ?, gender = ?, birthday = ?, picture = ? WHERE identity_id = ?";
        jdbcTemplate.update(sqlUpdate, identity.getCareer(), identity.getName(), identity.getGender(), identity.getBirthday(),identity.getPicture(), id);
        return ResponseEntity.ok(identity);
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
