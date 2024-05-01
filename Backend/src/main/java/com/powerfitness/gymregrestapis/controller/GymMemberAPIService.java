package com.powerfitness.gymregrestapis.controller;

import com.powerfitness.gymregrestapis.GymregRestApisApplication;
import com.powerfitness.gymregrestapis.model.GymMember;
import com.powerfitness.gymregrestapis.repository.GymMemRepository;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gymmember")
public class GymMemberAPIService {



    @Autowired
    private GymMemRepository gymMemRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/members")
    public List<GymMember> getAllMembers()
    {
        System.out.println("Inside the get members route");
        return this.gymMemRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("members/{id}")
    public ResponseEntity<GymMember> getMemberById(@PathVariable("id") Long memId) throws ResourceNotFoundException {
        GymMember gymMem = gymMemRepository.findById(memId).orElseThrow(()-> new ResourceNotFoundException("Member not found for this id"+memId));

        return ResponseEntity.ok().body(gymMem);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("members")
    public GymMember createMember(@RequestBody GymMember gymMember){
        System.out.println("Inside POST request");
        return this.gymMemRepository.save(gymMember);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("members/{id}")
    public ResponseEntity<GymMember> updateMember(@PathVariable("id") Long memId, @RequestBody GymMember gymMemberDetails) throws ResourceNotFoundException {
        System.out.println("Inside Put Mapping and the id = "+memId);
        GymMember gymMember = gymMemRepository.findById(memId).orElseThrow(()-> new ResourceNotFoundException("Member not found for id: "+memId));
        gymMember.setFirstName(gymMemberDetails.getFirstName());
        gymMember.setLastName(gymMemberDetails.getLastName());
        gymMember.setEmail(gymMemberDetails.getEmail());
        gymMember.setMobile(gymMemberDetails.getMobile());
        gymMember.setBmi(gymMemberDetails.getBmi());
        gymMember.setBmiResult(gymMemberDetails.getBmiResult());
        gymMember.setHaveGymBefore(gymMemberDetails.getHaveGymBefore());
        gymMember.setGender(gymMemberDetails.getGender());
        gymMember.setHeight(gymMemberDetails.getHeight());
        gymMember.setWeight(gymMemberDetails.getWeight());
        gymMember.setEnquiryDate(gymMemberDetails.getEnquiryDate());
        gymMember.setImportant(gymMemberDetails.getImportant());
        gymMember.setRequireTrainer(gymMemberDetails.getRequireTrainer());
        gymMember.setPackage(gymMemberDetails.getPackage());

        return ResponseEntity.ok(this.gymMemRepository.save(gymMember));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("members/{id}")
    public Map<String,Boolean> deleteMember(@PathVariable("id") Long memId) throws ResourceNotFoundException {
        GymMember gymMember = gymMemRepository.findById(memId).orElseThrow(()->new ResourceNotFoundException("Member not found for this id"+memId));
        this.gymMemRepository.delete(gymMember);

        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);

        return response;
    }

//    @GetMapping("/{id}")
//    public GymMember getGymMemberInfo(String id) {
//        System.out.println("Inside Get Route");
//        System.out.println(id);
//        String[] important = {"diet", "cardio"};
//        return gymMember;
////        return new GymMember("John",              // firstName
////                "Doe",               // lastName
////                "john.doe@example.com", // email
////                1234567890,          // mobile
////                70.5,                // weight
////                175.0,               // height
////                24.5,                // bmi
////                "Male",              // gender
////                "Yes",               // requireTrainer
////                "Gold",              // aPackage
////                important,           // important
////                "No",                // haveGymBefore
////                "2024-02-05",        // enquiryDate
////                id);
//    }
//
//    @PostMapping
//    public String crearteGymMember(@RequestBody GymMember gymMember)
//    {
//        System.out.println("Inside POST");
//        this.gymMember = gymMember;
//        return "Gym Member Registered Successfully";
//    }
//
//    @PutMapping
//    public String updateGymMember(@RequestBody GymMember gymMember)
//    {
//        System.out.println("Inside POST");
//        this.gymMember = gymMember;
//        return "Gym Member Updated Successfully";
//    }
//
//    @DeleteMapping("{id}")
//    public String deleteGymMember(@PathVariable("id") String id)
//    {
//        System.out.println("Id is = "+id);
//        this.gymMember = null;
//        return "Gym Member Deleted Successfully";
//    }
};

