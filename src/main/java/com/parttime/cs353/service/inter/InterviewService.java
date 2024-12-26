package com.parttime.cs353.service.inter;

import com.parttime.cs353.pojo.business.InterviewAddBO;
import com.parttime.cs353.pojo.business.InterviewBO;
import com.parttime.cs353.pojo.business.InterviewStatusBO;
import com.parttime.cs353.pojo.data.InterviewDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author: Isabella
 * @create: 2024-12-13 22:13
 **/
public interface InterviewService {
    int insertInterview(InterviewAddBO interview);
    int updateInterview(InterviewDO interview);
    int deleteInterview(InterviewDO interview);
    List<InterviewDO> selectInterviewById(int uid);
    List<InterviewDO> selectInterviewByCid(int cid);
    List<InterviewDO> selectInterviewByJid(int jid);
    List<InterviewBO> selectReceivedResume(int jid);
    Set<InterviewStatusBO> selectInterviewStatus(int uid);
}
