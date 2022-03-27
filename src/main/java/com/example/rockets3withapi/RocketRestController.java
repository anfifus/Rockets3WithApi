package com.example.rockets3withapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RocketRestController {

    RocketsService rocketsService;
    @Autowired
    public RocketRestController(RocketsService rocketsService) {
        this.rocketsService = rocketsService;
    }

    @PostMapping("/rockets")
    public Rocket createRocket(@RequestBody Rocket rocketToAdd){
       return rocketsService.addRocket(rocketToAdd);
    }
    @GetMapping("/rockets")
    public List<Rocket> getAll(){
        return rocketsService.getAll();
    }
    @DeleteMapping("/rockets")
    public void removeAll(){
        rocketsService.deleteAll();
    }
    @GetMapping("/rockets/{id}")
    public Rocket getRocketById(@PathVariable Long id){
       return rocketsService.getRocketById(id);
    }
    @DeleteMapping("/rockets/{id}")
    public void removeRocketById(@PathVariable Long id){
        rocketsService.deleteRocketById(id);
    }
    @PostMapping("/rockets/{id}/propellants")
    public List<Propellant> addPropellantToRocket(@PathVariable Long id, @RequestBody Propellant propellantToAdd) throws Exception {
        return rocketsService.addPropellant(id,propellantToAdd);
    }
    @DeleteMapping("/rockets/{id}/propellants")
    public void deleteAllPropellantsById(@PathVariable Long id){
        rocketsService.deleteAllPropellantsById(id);
    }
    @GetMapping("/rockets/{id}/propellants")
    public List<Propellant> getAllPropellantOfRocket(@PathVariable Long id){
        return  rocketsService.getAllPropellantByIdRocket(id);
    }
    @GetMapping("/rockets/{id}/propellants/{idPropellant}")
    public Propellant getPropellantById(@PathVariable Long id,@PathVariable Long idPropellant) throws Exception {
        return rocketsService.getPropellantById(id,idPropellant);
    }
    @PostMapping("/rockets/{id}/increase_power")
    public Rocket increasePropellant(@PathVariable Long id) throws Exception {
       return rocketsService.increasePropellant(id);
    }
    @PostMapping("/rockets/{id}/decrease_power")
    public Rocket decreasePropellant(@PathVariable Long id) throws Exception {
        return rocketsService.decreasePropellant(id);
    }
}
