package cn.medemede.j2ee.controller;

import cn.medemede.j2ee.enums.ResultEnum;
import cn.medemede.j2ee.model.AcBean;
import cn.medemede.j2ee.model.AcProve;
import cn.medemede.j2ee.model.Active;
import cn.medemede.j2ee.model.Result;
import cn.medemede.j2ee.repository.AcBeanRepository;
import cn.medemede.j2ee.repository.AcProveRepository;
import cn.medemede.j2ee.repository.ActiveRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class AcInfoController {

    @Resource
    private ActiveRepository activeRepository;

    @Resource
    private AcProveRepository acProveRepository;

    @Resource
    private AcBeanRepository acBeanRepository;

    @PostMapping("/acinfo/ac")
    public Result addAcTem(@RequestParam String acName,
                           @RequestParam String acTime,
                           @RequestParam String acUnit,
                           @RequestParam Integer days){
        Result result=new Result();
        Active active=new Active();
        Date date=new Date();
        active.setAcName(acName);
        active.setAcTime(acTime);
        active.setAcUnit(acUnit);
        active.setCreatDate(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,+days);

        active.setClearDate(calendar.getTime());
        active.setStruts(1);

        activeRepository.save(active);

        result.setResultEnum(ResultEnum.ADD_ACTEM_SUCCESS);
        return result;
    }

    @GetMapping("/acinfo/ac/{acName}")
    public Result exportAc(@PathVariable String acName){
        System.out.println(acName);
        Result result=new Result();
        List<AcBean> acBeanList=acBeanRepository.findByAcName(acName);
        System.out.println(acBeanList.size());
        for (AcBean acBean:acBeanList){
            System.out.println("stuId:"+acBean.getStuId()+" ");
        }

        return result;
    }

    @PutMapping("/acinfo/lock")
    public Result lockAc(@RequestParam String acName){
        Result result=new Result();
        Active active=activeRepository.findOne(acName);
        active.setStruts(0);
        activeRepository.save(active);
        result.setResultEnum(ResultEnum.LOCK_ACTEM_SUCCESS);
        return result;
    }
    @PutMapping("/acinfo/unlock")
    public Result unlockAc(@RequestParam String acName){
        Result result=new Result();
        Active active=activeRepository.findOne(acName);
        active.setStruts(1);
        activeRepository.save(active);
        result.setResultEnum(ResultEnum.UNLOCK_ACTEM_SUCCESS);
        return result;
    }
}
