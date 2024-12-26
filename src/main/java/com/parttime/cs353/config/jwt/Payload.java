package com.parttime.cs353.config.jwt;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-26 15:53
 **/

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Payload<T> implements Serializable {
    private String id;
    private T userInfo;
    private Date expiration;
}
