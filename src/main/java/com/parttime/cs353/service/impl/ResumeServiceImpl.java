package com.parttime.cs353.service.impl;

import com.parttime.cs353.dao.ResumeMapper;
import com.parttime.cs353.pojo.data.ResumeDO;
import com.parttime.cs353.service.inter.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-11-13 22:49
 **/
@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    ResumeMapper resumeMapper;

    public void setResumeMapper(ResumeMapper resumeMapper) {
        this.resumeMapper = resumeMapper;
    }
    @Override
    public int deleteResume(int uid) {
        return resumeMapper.deleteResume(uid);
    }

    @Override
    public int updateResume(ResumeDO resumeDO) {
        return resumeMapper.updateResume(resumeDO);
    }

    @Override
    public List<ResumeDO> selectResumeById(int uid) {
        return resumeMapper.selectResumeById(uid);
    }
    @Override
    public int insertResume(ResumeDO resumeDO) {
        return  resumeMapper.insertResume(resumeDO);
    }
}
