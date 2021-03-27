package com.example.account.controller;

import com.example.account.model.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.http.HttpStatus.OK;



/**
 * @author brahim
 * @since 2021-03-26
 */
@RestController
public class BalanceAccountRestController {

    @Autowired
    private BankAccount bankAccount;

    @GetMapping("/infoBalance")
    ResponseEntity<String> checkInfoAccount() {
        return  new ResponseEntity(bankAccount.infoAccount(), OK);
    }

    @GetMapping("/infoOperationsAccount")
    ResponseEntity<String> listOperationsAccount() {
        return  new ResponseEntity(bankAccount.checkHistoryOperations(), OK);
    }

    @PutMapping("/depositInAccount")
    ResponseEntity<String> deposit(@RequestBody double  amount) {
             bankAccount.deposit(amount);
        return  new ResponseEntity(checkInfoAccount(), OK);
    }

    @PutMapping("/withdrawInAccount")
    ResponseEntity<String> withdrow(@RequestBody double  amount) {
        return  new ResponseEntity(bankAccount.withdraw(amount), OK);
    }
}
