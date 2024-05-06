package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.NewsDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Shooter;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.NewsRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.repository.ShooterRepository;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.NewsService;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.ShooterService;

@RestController
public class ShooterController {

    @Autowired
    private ShooterRepository shooterRepository;
    private final ShooterService shooterService;

    @Autowired
    public ShooterController(ShooterService shooterService, ShooterRepository shooterRepository) {
        this.shooterService = shooterService;
        this.shooterRepository = shooterRepository;
    }

    @GetMapping("/shooter/all")
    public ResponseEntity<Object> getAllShooters() {
        return ResponseEntity.ok(shooterRepository.findAll());
    }

//    @PostMapping("/shooter/save")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public Shooter createShooter(@RequestBody Shooter shooter) throws Exception {
//        if (shooterRepository.findByEmail(shooter.getEmail()) != null) {
//            throw new Exception("Shooter is exist with " + shooter.getEmail());
//        }
//        return shooterRepository.save(shooter);
//    }

    @PostMapping("/shooter/add")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Shooter createShooter(@RequestBody Shooter shooter) throws Exception {
        if (shooterRepository.findByEmail(shooter.getEmail()) != null) {
            throw new Exception("Shooter is exist with " + shooter.getEmail());
        }
        return shooterService.saveShooter(shooter);
    }

    @RequestMapping("/shooter/delete/{shooterId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteShooter(@PathVariable("shooterId") Long id) throws Exception {
        shooterService.deleteShooterById(id);
        return ResponseEntity.ok("Shooter deleted successfully!.");
    }

//    public String deleteShooter(@PathVariable Long shooterId) {
//        shooterRepository.deleteById(shooterId);
//        return "Shooter deleted successfully";
//    }

}
