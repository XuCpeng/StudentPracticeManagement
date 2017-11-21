package cn.medemede.j2ee.controller;

import cn.medemede.j2ee.model.AcBean;
import cn.medemede.j2ee.model.AcProve;
import cn.medemede.j2ee.model.Result;
import cn.medemede.j2ee.repository.AcProveRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class StuInfoController {

    @Resource
    private AcProveRepository acProveRepository;

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
    @PostMapping("/stuinfo")
    public Result addStuAc(@RequestParam(required = false) String acName,
                           @RequestParam(required = false) String acTime,
                           @RequestParam(required = false) Float acHour,
                           @RequestParam(required = false) String acRole,
                           @RequestParam(required = false) String acUnit,
                           @RequestParam(required = false) String witne,
                           @RequestParam(required = false) String stuId){
        Result result=new Result();
        AcBean acBean=new AcBean();
        System.out.println(stuId);
        AcProve acProve=acProveRepository.findOne(stuId);
        int index=1;
        if(acProve.getAcList()!=null)
            index=acProve.getAcList().size()+1;
        acBean.setAcId(index);
        acBean.setAcName(acName);
        acBean.setAcTime(acTime);
        acBean.setAcHour(acHour);
        acBean.setAcRole(acRole);
        acBean.setAcUnit(acUnit);
        acBean.setWitne(witne);
        acProve.getAcList().add(acBean);
        acProveRepository.save(acProve);
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
