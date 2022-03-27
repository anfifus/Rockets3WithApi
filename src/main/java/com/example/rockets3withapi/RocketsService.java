package com.example.rockets3withapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RocketsService {

    RocketsRepository rocketsRepository;
    PropellantRepository propellantRepository;
    @Autowired
    public RocketsService(RocketsRepository rocketsRepository,PropellantRepository propellantRepository) {
        this.rocketsRepository = rocketsRepository;
        this.propellantRepository = propellantRepository;
    }

    public Rocket addRocket(Rocket rocketToAdd) {
       return rocketsRepository.save(rocketToAdd);
    }

    public List<Rocket> getAll() {
        return (List<Rocket>) rocketsRepository.findAll();
    }

    public void deleteAll() {
        rocketsRepository.deleteAll();
    }

    public Rocket getRocketById(Long id) {
       return rocketsRepository.findById(id).get();
    }

    public void deleteRocketById(Long id) {
        rocketsRepository.deleteById(id);
    }

    public List<Propellant> addPropellant(Long id, Propellant propellantToAdd) throws Exception {
        Rocket rocket = getRocketById(id);
        return (List<Propellant>)propellantRepository.saveAll(rocket.addPropellant(propellantToAdd));
    }

    public void deleteAllPropellantsById(Long id) {
        Rocket rockets = getRocketById(id);
        propellantRepository.deleteAll(rockets.getPropellantList());
    }
    public List<Propellant> getAllPropellantByIdRocket(Long id) {
        Rocket rocket = getRocketById(id);
        return  rocket.getPropellantList();
    }

    public Propellant getPropellantById(Long id, Long idPropellant) throws Exception {
        Rocket rocket = getRocketById(id);
        for (Propellant currentPropellant:rocket.getPropellantList()) {
            if(currentPropellant.getId() == idPropellant){
                return currentPropellant;
            }
        }
        throw new Exception("We didn't find the propellant in the rocket");
    }


    public Rocket increasePropellant(Long id) throws Exception {
        Rocket rocket = getRocketById(id);
        rocket.increasePower();
        return rocketsRepository.save(rocket);
    }
    public Rocket decreasePropellant(Long id) throws Exception {
        Rocket rocket = getRocketById(id);
        rocket.decreasePower();
        return rocketsRepository.save(rocket);
    }
}
