package com.parttime.cs353.service.impl;

import com.parttime.cs353.dao.InterviewMapper;
import com.parttime.cs353.dao.UserPasswordMapper;
import com.parttime.cs353.pojo.business.InterviewBO;
import com.parttime.cs353.pojo.data.InterviewDO;
import com.parttime.cs353.service.inter.InterviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-12-13 22:15
 **/
@Slf4j
@Service("interviewServiceImpl")
public class InterviewServiceImpl implements InterviewService {
    @Autowired
    InterviewMapper interviewMapper;
    @Autowired
    UserPasswordMapper userPasswordMapper;

    public void setInterviewMapper(InterviewMapper interviewMapper) {
        this.interviewMapper = interviewMapper;
    }

    @Override
    public int insertInterview(InterviewDO interview) {
        return interviewMapper.insertInterview(interview);
    }

    @Override
    public int updateInterview(InterviewDO interview) {
        return interviewMapper.updateInterview(interview);
    }

    @Override
    public int deleteInterview(InterviewDO interview) {
        return interviewMapper.deleteInterview(interview);
    }

    @Override
    public List<InterviewDO> selectInterviewById(int uid) {
        return interviewMapper.selectInterviewById(uid);
    }

    @Override
    public List<InterviewDO> selectInterviewByCid(int cid) {
        return interviewMapper.selectInterviewByCid(cid);
    }

    @Override
    public List<InterviewDO> selectInterviewByJid(int jid) {
        return interviewMapper.selectInterviewByJid(jid);
    }

    @Override
    public List<InterviewBO> selectReceivedResume(int jid) {
        List<InterviewDO> list=selectInterviewByJid( jid);
        List<InterviewBO> res=new ArrayList<>();
        for(InterviewDO d:list){
            String name=userPasswordMapper.selectUserDetail(d.getUid()).getName();
            InterviewBO b=new InterviewBO(name,d.getUid(),d.getState());
            res.add(b);
        }
        return res;
    }
}
