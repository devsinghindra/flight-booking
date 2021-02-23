package com.devsinghindra.flight.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
public class User {
    @Id
    private final String userAadhaarNo;
    private final String userName;
    private final String userPhone;
    private final String userEmail;
    private final String userPassword;
}
