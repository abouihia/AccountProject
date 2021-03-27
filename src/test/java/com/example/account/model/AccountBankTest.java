package com.example.account.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author brahim
 * @since 2021-03-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountBankTest {

    @Autowired
    private BankAccount bankAccount;

    @Before
    public void beforEach(){
        bankAccount.setBalance(0);
    }

   @Test
    public void shouldDepositMoneyWhenAmountIsPositif(){
        bankAccount.deposit(100);
        assertThat(bankAccount.getBalance()).isEqualTo(100);
       assertThat(bankAccount.getOperationsHistory())
               .extracting(OperationHistory ::getOperationType)
               .contains(OperationType.Deposit);
    }

    @Test
    public void shouldNotDepositMoneyWhenAmountIsLessOrEqualZero(){
        bankAccount.deposit(-100);
        assertThat(bankAccount.getBalance()).isEqualTo(0);
    }

    @Test
    public void shouldNotWithdrawWhenBalanceIsInsufficient(){
        bankAccount.deposit(100);
        assertThat(bankAccount.getBalance()).isEqualTo(100);
       final String message = bankAccount.withdraw(200);
        assertThat(message).contains("insufficient balance :");
        assertThat(bankAccount.getBalance()).isEqualTo(100);

    }

    @Test
    public void shouldWithdrawWhenBalanceSufficient(){
        bankAccount.deposit(200);
       final  double balance = bankAccount.getBalance();
        assertThat(balance).isEqualTo(200);
        String message = bankAccount.withdraw(100);
        assertThat(message).contains("withdraw :"+balance);
        assertThat(bankAccount.getBalance()).isEqualTo(100);
        assertThat(bankAccount.getOperationsHistory())
                            .extracting(OperationHistory ::getOperationType)
                            .contains(OperationType.WithDraw);



    }
}
