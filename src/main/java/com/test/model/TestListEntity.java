package com.test.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TestListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testListId;

    private String title;
    private String description;
    private String visibility;

    // ✅ One-to-One with AccessToken
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "access_token_id", referencedColumnName = "accessTokenId")
    private AccessTokenEntity accessToken;

    // ✅ Many TestLists can belong to one User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
