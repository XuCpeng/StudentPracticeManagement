package cn.medemede.j2ee.controller;

import cn.medemede.j2ee.model.AcProve;
import cn.medemede.j2ee.model.Result;
import cn.medemede.j2ee.repository.AcProveRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class StuInfoController {

    @Resource
    private AcProveRepository acProveRepository;

    /**
     * 获取该学生的所用个人信息
     * @param stuId
     * @return
     */
    @GetMapping("/stuinfo2/{stuId}")
    public String getStuInfo(@PathVariable("stuId") String stuId, Model model){
        Result result=new Result();
        AcProve acProve=acProveRepository.findOne(stuId);
        System.out.println(acProve.getStuName());
        model.addAttribute("acProve",acProve);

        return "stu-info";
    }

    /**
     * 更新个人信息，不包括活动
     * @return
     */
    @PutMapping("/stuinfo")
    public Result updateStuInfo(){
        Result result=new Result();

        return result;
    }

    /**
     * 添加活动
     * @return
     */
    @PutMapping("/stuinfo/ac")
    public Result addStuAc(){
        Result result=new Result();

        return result;
    }


    /**
     * 删除活动
     * @param acId
     * @return
     */
    @DeleteMapping("/stuinfo/ac/{acId}")
    public Result deleteStuAc(@PathVariable("acId") String acId){
        Result result=new Result();

        return result;
    }

}
