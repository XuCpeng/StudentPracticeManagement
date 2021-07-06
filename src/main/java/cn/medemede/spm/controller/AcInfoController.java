package cn.medemede.spm.controller;

import cn.medemede.spm.enums.ResultEnum;
import cn.medemede.spm.model.AcBean;
import cn.medemede.spm.model.AcProve;
import cn.medemede.spm.model.Active;

import cn.medemede.spm.model.Result;
import cn.medemede.spm.repository.AcBeanRepository;
import cn.medemede.spm.repository.AcProveRepository;
import cn.medemede.spm.repository.ActiveRepository;
import cn.medemede.spm.service.AcExcelService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author xcp
 */
@RestController
public class AcInfoController {

    @Resource
    private ActiveRepository activeRepository;

    @Resource
    private AcProveRepository acProveRepository;

    @Resource
    private AcBeanRepository acBeanRepository;
    @Resource
    private AcExcelService acExcelService;

    @PostMapping("/acinfo/ac")
    public Result addAcTem(@RequestParam String acName,
                           @RequestParam String acTime,
                           @RequestParam String acUnit,
                           @RequestParam Integer days) {
        Result result = new Result();
        Active active = new Active();
        Date date = new Date();
        active.setAcName(acName);
        active.setAcTime(acTime);
        active.setAcUnit(acUnit);
        active.setCreatDate(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, +days);

        active.setClearDate(calendar.getTime());
        active.setStruts(1);

        activeRepository.save(active);

        result.setResultEnum(ResultEnum.ADD_ACTEM_SUCCESS);
        return result;
    }

    @GetMapping("/acinfo/ac/{acName}")
    public Result exportAc(@PathVariable String acName) {

        Result result = new Result();
        List<AcBean> acBeanList = acBeanRepository.findByAcName(acName);

        return result;
    }

    @PostMapping("/acinfo/acExcel")
    public Result importFormMon(@RequestParam String acName,
                                @RequestParam("ExcelFile") MultipartFile multipartFile) {
        Result result = new Result();
        String fileName = multipartFile.getOriginalFilename();
        File file = new File("D://" + fileName);

        try {
            //MultipartFile自带的解析文件的方法
            multipartFile.transferTo(file);
            List<AcBean> acBeans = acExcelService.importFromMon(acName, file);
            for (AcBean acBean : acBeans) {
                AcProve acProve = acProveRepository.findOne(acBean.getStuId());
                acProve.getAcList().add(acBean);
                acProveRepository.save(acProve);
            }
            result.setResultEnum(ResultEnum.IMPORT_AC_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @PutMapping("/acinfo/lock")
    public Result lockAc(@RequestParam String acName) {
        Result result = new Result();
        Active active = activeRepository.findOne(acName);
        active.setStruts(0);
        activeRepository.save(active);
        result.setResultEnum(ResultEnum.LOCK_ACTEM_SUCCESS);
        return result;
    }

    @PutMapping("/acinfo/unlock")
    public Result unlockAc(@RequestParam String acName) {
        Result result = new Result();
        Active active = activeRepository.findOne(acName);
        active.setStruts(1);
        activeRepository.save(active);
        result.setResultEnum(ResultEnum.UNLOCK_ACTEM_SUCCESS);
        return result;
    }
}
