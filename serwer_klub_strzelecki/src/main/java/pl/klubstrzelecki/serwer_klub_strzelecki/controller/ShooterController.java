package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ShooterDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.ShooterService;

@RestController
@RequestMapping("/api/shooter")
public class ShooterController {
    private final ShooterService shooterService;

    @Autowired
    public ShooterController(ShooterService shooterService) {
        this.shooterService = shooterService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllShooters() {
        return ResponseEntity.ok().body(shooterService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getShooterById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(shooterService.findShooterById(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> createShooter(@RequestBody ShooterDTO shooterDTO) {
        shooterService.saveShooter(shooterDTO);
        return ResponseEntity.ok().body("{\"message\": \"Shooter saved successfully!\"}");
    }

    //return ResponseEntity.status(404).body("{\"error\": \"Error, shooter not saved\"}");

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> updateNews(@PathVariable Long id, @RequestBody ShooterDTO shooterDTO) throws Exception {
        shooterService.updateShooter(id, shooterDTO);
        return ResponseEntity.ok().body("{\"message\": \"Shooter updated successfully!\"}");
    }

    @RequestMapping("/delete/{shooterId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteShooter(@PathVariable("shooterId") Long id) throws Exception {
        shooterService.deleteShooterById(id);
        return ResponseEntity.ok().body("{\"message\": \"Shooter deleted successfully!\"}");
    }
}
