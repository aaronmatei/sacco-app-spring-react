package com.aaronmatei.contributionsservice.controller;

import com.aaronmatei.contributionsservice.intercomm.UserClient;
import com.aaronmatei.contributionsservice.model.Transaction;
import com.aaronmatei.contributionsservice.service.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SavingsController {
    @Autowired
    private SavingService savingService;
    @Autowired
    private UserClient userClient;


    @GetMapping("/service/all")
    public ResponseEntity<?> displayAllSavings(){
        return ResponseEntity.ok(savingService.allSavings());
    }
    @GetMapping("/service/user/{userId}")
    public ResponseEntity<?> findUserTransactionsById(@PathVariable Long userId){
        return ResponseEntity.ok(savingService.findSavingsByUserId(userId));
    }
    @PostMapping("/service/transact")
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction){
        transaction.setDateOfSaving(LocalDateTime.now());
        transaction.setSaving(savingService.findSavingById(transaction.getSaving().getId()));
        return new ResponseEntity<>(savingService.saveTransaction(transaction), HttpStatus.CREATED);
    }
    @GetMapping("/service/saving/{savingId}")
    public ResponseEntity<?> findUsersWithLoanId(@PathVariable Long savingId){
        List<Transaction> transactions = savingService.findSavingsBySavingsId(savingId);
        if(CollectionUtils.isEmpty(transactions)){
            return ResponseEntity.notFound().build();
        }
        List<Long> userIdList = transactions.parallelStream().map(t-> t.getUserId()).collect(Collectors.toList());
        List<String> users = userClient.getUserNames(userIdList);
        return ResponseEntity.ok(users);
    }
}
