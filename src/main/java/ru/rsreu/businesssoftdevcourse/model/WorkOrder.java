package ru.rsreu.businesssoftdevcourse.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.EnumSet;

@Data
@Table
@Entity
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Car manufacturer is required")
    private String carManufacturer;

    @NotBlank(message = "Car model is required")
    private String carModel;

    @Digits(integer = 4, fraction = 0, message = "Invalid year")
    @Min(value = 1990, message = "Must be greater than 1990 or equals")
    @Max(value = 2022, message = "Must be less than 2022 or equals")
    private int manufacturingYear;

    @Size(min = 1, message = "Pick any breakdown")
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "order_breakdowns", joinColumns = @JoinColumn(name = "order_id"))
    private Collection<BreakdownType> breakdowns = EnumSet.noneOf(BreakdownType.class);

    @NotBlank(message = "Name is required")
    private String name;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([1-9][0-9])$",
            message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
}