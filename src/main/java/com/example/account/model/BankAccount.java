package com.example.account.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

/**
 * @author brahim
 * @since 2021-03-25
 */
@Data
@Component
public class BankAccount {

    private static final DateTimeFormatter formatter =
              DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss SSS");

    private double balance;

    private List<OperationHistory> operationsHistory  ;

    public  BankAccount(){
        this.operationsHistory  = new ArrayList<>();
    }

    public void deposit(final double amount){
          if(amount > 0){
              this.balance += amount;
             addOperation(OperationType.Deposit, amount);
          }
    }

     public final String withdraw(final double amount){
        String withdrawMessage =null;

        if(balance- amount < 0){
            withdrawMessage = "insufficient balance :" +this.balance;
        }else{
            withdrawMessage = "withdraw :" +this.balance;
            this.balance -= amount;
            addOperation(OperationType.WithDraw, amount);
        }
        return  withdrawMessage;
     }

     public final String infoAccount(){

        return "Your Balance Account is :"
                + this.balance
                + " at Date :"
                + LocalDateTime.now().format(formatter);
     }

    public final  String checkHistoryOperations(){

        final StringBuffer  infoOperation = new StringBuffer();
        infoOperation.append("This the History of all your Operation  in your Account :\n");
        infoOperation.append("----------------------------------------------------------\n");
        infoOperation.append("Type").append("\t\t");
        infoOperation.append("Date").append("\t\t\t\t\t\t");
        infoOperation.append("Amount").append("\n");
        infoOperation.append("----------------------------------------------------------\n");
        for(OperationHistory operationHistory  : operationsHistory){
            infoOperation.append(operationHistory.getOperationType().name()).append(" \t");
            infoOperation.append(operationHistory.getOperationDate()).append("\t\t");
            infoOperation.append(operationHistory.getAmout()).append("\t");
            infoOperation.append("\n");
        }

         return  infoOperation.toString();

    }



   private void  addOperation(final OperationType  operationType, final  double amount){

        OperationHistory  operationHistory  =OperationHistory.builder()
                .operationType(operationType)
                .amout(amount)
                .operationDate(LocalDateTime.now().format(formatter))
                .build();

        this.operationsHistory.add( operationHistory );
    }

}
