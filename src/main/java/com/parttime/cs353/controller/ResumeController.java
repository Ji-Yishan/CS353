package com.parttime.cs353.controller;

import com.parttime.cs353.pojo.data.ResumeDO;
import com.parttime.cs353.service.inter.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @author: Isabella
 * @create: 2024-11-13 22:59
 **/
@RestController
public class ResumeController {
    @Autowired
    ResumeService resumeService;
    @PostMapping
    public void insertResume(@RequestBody ResumeDO resumeDO) {
        resumeService.insertResume(resumeDO);
    }

    @DeleteMapping("/{uid}")
    public void deleteResume(@PathVariable int uid) {
        resumeService.deleteResume(uid);
    }

    @PutMapping
    public void updateResume(@RequestBody ResumeDO resumeDO) {
        resumeService.updateResume(resumeDO);
    }

    @GetMapping("/{uid}")
    public List<ResumeDO> selectResumeById(@PathVariable int uid) {
        return resumeService.selectResumeById(uid);
    }
}
