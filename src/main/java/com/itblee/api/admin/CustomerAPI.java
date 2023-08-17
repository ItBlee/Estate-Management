package com.itblee.api.admin;

import com.itblee.dto.CustomerDTO;
import com.itblee.dto.TransactionDTO;
import com.itblee.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @PutMapping("/assign/{customerId}")
    public ResponseEntity<Void> assignStaffs(
            @PathVariable("customerId") long id,
            @RequestBody Long[] staffIds) {
        customerService.assignCustomer(id, staffIds);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/status/{customerId}")
    public ResponseEntity<Void> changeStatus(@PathVariable("customerId") long id) {
        customerService.changeStatus(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/transaction")
    public ResponseEntity<Void> addTransaction(@RequestBody TransactionDTO transactionDTO) {
        customerService.saveTransaction(transactionDTO);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.save(customerDTO));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable("customerId") long id,
            @RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(id);
        return ResponseEntity.ok(customerService.save(customerDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCustomers(@RequestBody long[] ids) {
        if (ids.length > 0)
            customerService.delete(ids);
        return ResponseEntity.noContent().build();
    }

}
