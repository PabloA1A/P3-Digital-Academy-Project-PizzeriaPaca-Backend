package org.factoriaf5.pizzeriapaca.register.dtos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerService {

     @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> findCustomer(@PathVariable("customerId") Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Address address = addressRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setUsername(customer.getUsername());
        dto.setEmail(customer.getEmail());
        dto.setFullName(customer.getFirstName() + " " + customer.getLastName());
        dto.setAddress(address.getAddress());
        dto.setPostalCode(address.getPostalCode());
        dto.setCity(address.getCity());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
