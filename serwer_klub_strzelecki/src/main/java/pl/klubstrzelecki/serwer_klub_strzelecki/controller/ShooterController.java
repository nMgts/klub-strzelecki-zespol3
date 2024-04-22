package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.ShooterRepository;

@RestController
public class ShooterController {

    @Autowired
    private ShooterRepository shooterRepository;

    @GetMapping("/shooter/all")
    public ResponseEntity<Object> getAllShooters() {
        return ResponseEntity.ok(shooterRepository.findAll());
    }

    @PostMapping("/shooter/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Shooter createShooter(@RequestBody Shooter shooter) throws Exception {
        if (shooterRepository.findByEmail(shooter.getEmail()) != null) {
            throw new Exception("Shooter is exist with " + shooter.getEmail());
        }
        return shooterRepository.save(shooter);
    }

    @RequestMapping("/shooter/delete/{shooterId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteShooter(@PathVariable Long shooterId) {
        shooterRepository.deleteById(shooterId);
        return "Shooter deleted successfully";
    }
}
