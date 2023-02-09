package com.geteWay.models;

import lombok.*;

import java.util.Collection;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRespone {
    private String userId;
    private String  accessTokenl;
    private String refreshToken;
    private long expireAt;

    private Collection<String> authorititis;

}
