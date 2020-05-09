package com.aaronmatei.loanmanagement.controller;

import com.aaronmatei.loanmanagement.intercomm.UserClient;
import com.aaronmatei.loanmanagement.model.Transaction;
import com.aaronmatei.loanmanagement.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LoanController {
    @Autowired
    private UserClient userClient;
    @Autowired
    private LoanService loanService;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private Environment env;
    @Value("${spring.application.name}")
    private String serviceId;

    @GetMapping("/service/port")
    public String getPort(){
        return "Service is working at port : "+env.getProperty("local.server.port");
    }
    @GetMapping("/service/instances")
    public ResponseEntity<?> getInstances(){
        return ResponseEntity.ok(discoveryClient.getInstances(serviceId));

    }

    @GetMapping("/service/user/{userId}")
    public ResponseEntity<?> findTransactionsOfUser(@PathVariable Long userId){
        
        return ResponseEntity.ok(loanService.findTransactionsOfUser(userId));

    }
    @GetMapping("/service/all")
    public ResponseEntity<?> findAllLoans(){
        return ResponseEntity.ok(loanService.allLoans());
    }
    @PostMapping("/service/transact")
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction){
        transaction.setDateOfIssue(LocalDateTime.now());
        transaction.setLoan(loanService.findLoanById(transaction.getLoan().getId()));
        return new ResponseEntity<>(loanService.saveTransaction(transaction), HttpStatus.CREATED);
    }
    @GetMapping("/service/loan/{loanId}")
    public ResponseEntity<?> findUsersWithLoan(@PathVariable Long loanId){
        List<Transaction> transactions = loanService.findTransactionsByLoanId(loanId);
        if(CollectionUtils.isEmpty(transactions)){
            return ResponseEntity.notFound().build();
        }
        List<Long> userIdList = transactions.parallelStream().map(t->t.getUserId()).collect(Collectors.toList());
        List<String> users = userClient.getUserNames(userIdList);
        return ResponseEntity.ok(users);

    }

}
