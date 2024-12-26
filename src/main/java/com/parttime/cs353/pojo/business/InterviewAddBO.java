package com.parttime.cs353.pojo.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-24 20:50
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewAddBO {
    private int uid;
    private int cid;
    private int jid;
}
