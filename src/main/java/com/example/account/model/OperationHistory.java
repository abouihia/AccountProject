package com.example.account.model;


import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author brahim
 * @since 2021-03-25
 */
@Getter
@Setter
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationHistory {

      private OperationType operationType;
      private String operationDate;
      private  double amout;

}
