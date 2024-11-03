package com.parttime.cs353.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-10-26 15:49
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    private Integer code;
    private String msg;
    private Object data;
}
