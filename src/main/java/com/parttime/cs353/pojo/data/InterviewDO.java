package com.parttime.cs353.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-13 21:21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewDO {
    private int cid;
    private int jid;
    private int uid;
    private String state;

    public InterviewDO(int cid, int jid,int uid) {
        this.cid = cid;
        this.jid = jid;
        this.jid=jid;
    }
}
