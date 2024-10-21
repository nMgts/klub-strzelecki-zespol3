package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.klubstrzelecki.serwer_klub_strzelecki.dto.WeaponDTO;
import pl.klubstrzelecki.serwer_klub_strzelecki.service.WeaponService;

import java.io.IOException;

@RestController
@RequestMapping("/api/weapon")
public class  WeaponController {

    private final WeaponService weaponService;
    private MultipartFile image;

    @Autowired
    public WeaponController(WeaponService weaponService) {
        this.weaponService = weaponService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllWeapons() {
        return ResponseEntity.ok().body(weaponService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getWeaponById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(weaponService.findWeaponById(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> createNews(@RequestBody WeaponDTO weaponDTO) {
        weaponService.saveWeapon(weaponDTO, image);
        return ResponseEntity.ok().body("{\"message\": \"News saved successfully!\"}");
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> updateWeapon(@PathVariable Long id, @RequestBody WeaponDTO weaponDTO) throws Exception {
        weaponService.updateWeapon(id, weaponDTO);
        return ResponseEntity.ok().body("{\"message\": \"Weapon updated successfully!\"}");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteWeapon(@PathVariable("id") Long id) throws Exception {
        weaponService.deleteWeaponById(id);
        return ResponseEntity.ok().body("{\"message\": \"Weapon deleted successfully!\"}");
    }

    @PostMapping(path = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addWeapon(
            @RequestPart("weapon") WeaponDTO weaponDTO,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        weaponService.saveWeapon(weaponDTO, image);
        return ResponseEntity.ok().body("{\"message\": \"Weapon saved successfully!\"}");
    }

}
