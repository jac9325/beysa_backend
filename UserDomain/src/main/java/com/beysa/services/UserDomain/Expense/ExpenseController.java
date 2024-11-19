package com.beysa.services.UserDomain.Expense;

import com.beysa.services.UserDomain.Expense.DTO.ExpenseDto;
import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getExpenseById/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable(value = "id") Long id){
        try{
            ExpenseDto expense = expenseService.getExpenseById(id);
            return response.ok(codes.ok(), messages.ok(), expense, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    // @GetMapping("/getExpenseByIdCashSession/{id}")
    // public ResponseEntity<?> getExpenseByIdCashSession(@PathVariable(value = "id") Long id){
    //     try{
    //         List<ExpenseDto> expense = expenseService.getExpenseByIdCashSession(id);
    //         return response.ok(codes.ok(), messages.ok(), expense, null);
    //     }catch (Exception e){
    //         return response.error(codes.error(), messages.error() + e.getMessage(), null);
    //     }
    // }
}
