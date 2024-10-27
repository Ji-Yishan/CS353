package com.parttime.cs353.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2024-10-18 21:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDO {
    /**
     * uid
     * @titleName 用户ID
     */

    private String uid;
    /**
     * jid
     * @titleName 岗位ID
     */
    private String jid;

}
