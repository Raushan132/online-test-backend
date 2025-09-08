package com.test.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AccessTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accessTokenId;

    private String token;
    private LocalDateTime createAt;
    private LocalDateTime expireDate;
    private boolean isActive;

    // Optional back-reference if you want to navigate from token to test list
    @OneToOne(mappedBy = "accessToken")
    private TestListEntity testList;
}

