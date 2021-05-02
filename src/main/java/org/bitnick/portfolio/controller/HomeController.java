/*
 * The MIT License
 *
 * Copyright (c) 2020, Nicholas Bruce Strydom
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.bitnick.portfolio.controller;

import org.bitnick.portfolio.model.Award;
import org.bitnick.portfolio.model.Job;
import org.bitnick.portfolio.model.Project;
import org.bitnick.portfolio.model.Resume;
import org.bitnick.portfolio.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private ProjectService projectService;

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    private Resume buildResume() {
        Resume resume = new Resume();
        resume.setTitle("Nicholas B Strydom");
        resume.setBio("Lorem ipsum ");
        resume.setAwards(generateAwards());
        resume.setWorkExperience(generateWorkExperience());
        resume.setSkills(generateSkills());

        return resume;
    }

    private List<String> generateSkills() {
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("Groovy");
        skills.add("Python");
        skills.add("C#");
        skills.add("C\\C++");
        skills.add("JavaScript");

        return skills;
    }

    private List<Award> generateAwards() {
        List<Award> awards = new ArrayList<>();
        Award firstAward = new Award();
        firstAward.setTitle("");
        firstAward.setDescription("");
        firstAward.setYear("2007");
        awards.add(firstAward);

        Award secondAward = new Award();
        secondAward.setTitle("");
        secondAward.setDescription("");
        secondAward.setYear("2008");
        awards.add(secondAward);

        return awards;
    }

    private List<Job> generateWorkExperience() {
        List<Job> jobs = new ArrayList<>();
        Job leaderbJob = new Job();
        leaderbJob.setPosition("App Developer");
        leaderbJob.setStartMonthStr("");
        leaderbJob.setEndMonthStr("");
        leaderbJob.setCompany("MyLeaderboards, Inc.");
        leaderbJob.setDescription("");
        jobs.add(leaderbJob);

        Job northstarJob = new Job();
        northstarJob.setPosition("Software Developer");
        northstarJob.setStartMonthStr("");
        northstarJob.setEndMonthStr("");
        northstarJob.setCompany("NorthstarMLS");
        northstarJob.setDescription("");
        jobs.add(northstarJob);

        Job cryptoJob = new Job();
        cryptoJob.setPosition("Software Developer");
        cryptoJob.setStartMonthStr("");
        cryptoJob.setEndMonthStr("");
        cryptoJob.setCompany("Crypto Quant Solutions");
        cryptoJob.setDescription("");
        jobs.add(cryptoJob);

        Job regiJob = new Job();
        regiJob.setPosition("Software Developer");
        regiJob.setStartMonthStr("");
        regiJob.setEndMonthStr("");
        regiJob.setCompany("Registration Technology, Inc.");
        regiJob.setDescription("");
        jobs.add(regiJob);

        Job loginJob = new Job();
        loginJob.setPosition("Software Developer");
        loginJob.setStartMonthStr("");
        loginJob.setEndMonthStr("");
        loginJob.setCompany("Login, Inc.");
        loginJob.setDescription("");
        jobs.add(loginJob);

        return jobs;
    }

    @RequestMapping(path = "/")
    public String index(Model model) {
        List<Project> projects = projectService.getAllProjects();
        Resume resume = buildResume();

        model.addAttribute("welcome", "Hello World! I'm Nick!");
        model.addAttribute("resume", resume);
        model.addAttribute("projects", projects);
        model.addAttribute("email", "nstrydom@gmail.com");

        return "index";
    }
}
