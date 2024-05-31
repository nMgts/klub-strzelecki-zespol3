package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.ShooterDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.ShooterRepository;
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
    public ResponseEntity<Object> getAllShooters() {
        return ResponseEntity.ok().body(shooterService.findAll());
    }

//    @PostMapping("/shooter/save")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public Shooter createShooter(@RequestBody Shooter shooter) throws Exception {
//        if (shooterRepository.findByEmail(shooter.getEmail()) != null) {
//            throw new Exception("Shooter is exist with " + shooter.getEmail());
//        }
//        return shooterRepository.save(shooter);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getNewsById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(shooterService.findShooterById(id));
    }

    @PostMapping("/add")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> createShooter(@RequestBody ShooterDTO shooterDTO) {
        shooterService.saveShooter(shooterDTO);
        return ResponseEntity.ok().body("{\"message\": \"Shooter was saved!\"}");
    }

    //return ResponseEntity.status(404).body("{\"error\": \"Error, shooter not saved\"}");

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> updateNews(@PathVariable Long id, @RequestBody ShooterDTO shooterDTO) throws Exception {
        shooterService.updateShooter(id, shooterDTO);
        return ResponseEntity.ok().body("{\"message\": \"Shooter updated successfully!\"}");
    }

    @RequestMapping("/delete/{shooterId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteShooter(@PathVariable("shooterId") Long id) throws Exception {
        shooterService.deleteShooterById(id);
        return ResponseEntity.ok().body("{\"message\": \"Shooter deleted successfully!\"}");
    }
}
