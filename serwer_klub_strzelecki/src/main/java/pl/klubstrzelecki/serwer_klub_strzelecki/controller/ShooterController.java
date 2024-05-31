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

    private final ShooterRepository shooterRepository;
    private final ShooterService shooterService;

    @Autowired
    public ShooterController(ShooterRepository shooterRepository, ShooterService shooterService) {
        this.shooterRepository = shooterRepository;
        this.shooterService = shooterService;
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllShooters() {
        return ResponseEntity.ok().body(shooterRepository.findAll());
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
    public ResponseEntity<Object> getNewsById(@PathVariable Long id) {
        return ResponseEntity.ok().body(shooterRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Shooter not exists with id: " + id)));
    }

    @PostMapping("/add")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> createShooter(@RequestBody ShooterDTO shooter) {
        ShooterDTO result = shooterService.saveShooter(shooter);
        if (result.id() > 0) {
            return ResponseEntity.ok().body("{\"message\": \"Shooter was saved\"}");
        }
        return ResponseEntity.status(404).body("{\"error\": \"Error, shooter not saved\"}");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> updateNews(@PathVariable Long id, @RequestBody Shooter shooterDetails) {
        Shooter shooter = shooterRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("News not exists with id: " + id));
        shooter.setFirst_name(shooterDetails.getFirst_name());
        shooter.setLast_name(shooterDetails.getLast_name());
        shooter.setEmail(shooterDetails.getEmail());

        Shooter updatedShooter = shooterRepository.save(shooter);
        return ResponseEntity.ok().body(updatedShooter);
    }

    @RequestMapping("/delete/{shooterId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteShooter(@PathVariable("shooterId") Long id) {
        shooterService.deleteShooterById(id);
        return ResponseEntity.ok().body("{\"message\": \"Shooter deleted successfully\"}");
    }
}
