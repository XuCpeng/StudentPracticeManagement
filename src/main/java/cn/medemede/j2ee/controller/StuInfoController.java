package cn.medemede.j2ee.controller;

import cn.medemede.j2ee.enums.ResultEnum;
import cn.medemede.j2ee.model.AcBean;
import cn.medemede.j2ee.model.AcProve;
import cn.medemede.j2ee.model.JUserRole2;
import cn.medemede.j2ee.model.Result;
import cn.medemede.j2ee.repository.AcProveRepository;
import cn.medemede.j2ee.repository.JUserRole2Repository;
import cn.medemede.j2ee.service.AcExcelService;
import cn.medemede.j2ee.service.AcWordService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Saber
 */
@RestController
public class StuInfoController {

    @Resource
    private AcProveRepository acProveRepository;

    @Resource
    private AcWordService acWordService;

    @Resource
    private JUserRole2Repository jUserRole2Repository;

    @Resource
    private AcExcelService acExcelService;

    /**
     * 更新个人信息，不包括活动
     * @return
     */
    @PostMapping("/stuinfo")
    public Result updateStuInfo(@RequestParam(required = false) String stuName,
                                @RequestParam(required = false) String sex,
                                @RequestParam(required = false) String birth,
                                @RequestParam(required = false) String level,
                                @RequestParam(required = false) String klass,
                                @RequestParam(required = false) String startY,
                                @RequestParam(required = false) String startM,
                                @RequestParam(required = false) String school,
                                @RequestParam(required = false) String stuId){
        Result result=new Result();
        AcProve acProve=acProveRepository.findOne(stuId);
        if(stuName!=null) {
            acProve.setStuName(stuName);
        }
        if(sex!=null) {
            acProve.setSex(sex);
        }
        if(birth!=null){
            String format=birth.split("-")[0]+"年"+birth.split("-")[1]+"月"+birth.split("-")[2]+"日";
            acProve.setBirth(format);
        }
        if(level!=null) {
            acProve.setLevel(level);
        }
        if(klass!=null) {
            acProve.setKlass(klass);
        }
        if(startY!=null) {
            acProve.setStartY(startY);
        }
        if(startM!=null) {
            acProve.setStartM(startM);
        }
        if(school!=null) {
            acProve.setSchool(school);
        }
        if(acProveRepository.save(acProve)!=null){
            result.setResultEnum(ResultEnum.UPDATE_STU_SUCCESS);
        }else {
            result.setResultEnum(ResultEnum.UPDATE_STU_FAILD);
        }
        return result;
    }

    /**
     * 添加活动
     * @return
     */
    @PostMapping("/stuinfo/ac")
    public Result addStuAc(@RequestParam(required = false) String acName,
                           @RequestParam(required = false) String acTime,
                           @RequestParam(required = false) Float acHour,
                           @RequestParam(required = false) String acRole,
                           @RequestParam(required = false) String acUnit,
                           @RequestParam(required = false) String witne,
                           @RequestParam(required = false) String stuId,
                           @RequestParam(required = false) Integer acId){
        Result result=new Result();
        AcProve acProve=acProveRepository.findOne(stuId);
        if(acId!=null){
            if(acProve.getAcList()!=null){
                for(AcBean acBean:acProve.getAcList()){
                    if(acBean.getAcId().equals(acId)){
                        acBean.setAcName(acName);
                        acBean.setAcTime(acTime);
                        acBean.setAcHour(acHour);
                        acBean.setAcRole(acRole);
                        acBean.setAcUnit(acUnit);
                        acBean.setWitne(witne);
                        if (acProveRepository.save(acProve) != null) {
                            result.setResultEnum(ResultEnum.UPDATE_AC_SUCCESS);
                        }else {
                            result.setResultEnum(ResultEnum.UPDATE_AC_FAILD);
                        }
                        break;
                    }
                }
            }else {
                result.setResultEnum(ResultEnum.UPDATE_AC_FAILD);
            }
        }else {
            AcBean acBean = new AcBean();
            int index = 1;
            if (acProve.getAcList() != null) {
                index = acProve.getAcList().size() + 1;
            }
            acBean.setAcId(index);
            acBean.setAcName(acName);
            acBean.setAcTime(acTime);
            acBean.setAcHour(acHour);
            acBean.setAcRole(acRole);
            acBean.setAcUnit(acUnit);
            acBean.setWitne(witne);
            acProve.getAcList().add(acBean);
            if (acProveRepository.save(acProve) != null) {
                result.setResultEnum(ResultEnum.ADD_AC_SUCCESS);
            } else {
                result.setResultEnum(ResultEnum.ADD_AC_FAILD);
            }
        }

        return result;
    }


    /**
     * 删除活动
     * @param acId
     * @return
     */
    @DeleteMapping("/stuinfo/ac")
    public Result deleteStuAc(@RequestParam String stuId,
                              @RequestParam Integer acId){
        Result result=new Result();
        AcProve acProve=acProveRepository.findOne(stuId);
        if (acProve.getAcList()!=null){
            for(AcBean acBean:acProve.getAcList()){
                if (Objects.equals(acBean.getAcId(), acId)){
                    acProve.getAcList().remove(acBean);
                }
            }
            acProveRepository.save(acProve);
            result.setResultEnum(ResultEnum.DELETE_AC_SUCCESS);
        }else {
            result.setResultEnum(ResultEnum.DELETE_AC_FAILD);
        }

        return result;
    }

    /**
     * 导出学号为stuId的学生的活动证明
     * @param stuId
     * @return
     */
    @GetMapping("/stuinfo/ac/{stuId}")
    public Result exportAc(@PathVariable("stuId") String stuId){

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Result result=new Result();
        AcProve acProve=acProveRepository.findOne(stuId);

        //设置当前时间
        acProve.setProveDate(new Date());
        Calendar cal = Calendar.getInstance();
        acProve.setEndY(String.valueOf(cal.get(Calendar.YEAR)));
        acProve.setEndM(String.valueOf(cal.get(Calendar.MONTH)+1));

        try {
            if(acWordService.exportWord(acProve,response)){
                result.setResultEnum(ResultEnum.EXPORT_AC_SUCCESS);
            }else {
                result.setResultEnum(ResultEnum.EXPORT_AC_FAILD);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.setResultEnum(ResultEnum.EXPORT_AC_FAILD);
        }

        return result;
    }

    /**
     * 导出所有学生的活动证明
     * @return
     */
    @GetMapping("/stuinfo/stuList")
    public Result exportAcList(){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        List<JUserRole2> stuList=jUserRole2Repository.findByRoleName("stu");
        List<AcProve> proveList=new ArrayList<>();
        for(JUserRole2 jUserRole2:stuList){
            AcProve acProve=acProveRepository.findOne(jUserRole2.getStuId());
            if(acProve!=null&&acProve.getStuName()!=null) {
                //设置当前时间
                acProve.setProveDate(new Date());
                Calendar cal = Calendar.getInstance();
                acProve.setEndY(String.valueOf(cal.get(Calendar.YEAR)));
                acProve.setEndM(String.valueOf(cal.get(Calendar.MONTH) + 1));
                proveList.add(acProve);
            }
        }
        return acWordService.exportAcList(proveList,response);
    }

    /**
     * 删除某学生
     * @param stuId
     * @return
     */
    @DeleteMapping("/stuinfo/stuList")
    public Result deleteStu(@RequestParam String stuId){
        Result result=new Result();
        acProveRepository.delete(stuId);
        result.setResultEnum(ResultEnum.DELETE_STU_SUCCESS);
        return result;
    }

    @PostMapping("/stuinfo/acExcel")
    public Result importAC(@RequestParam String stuId,
                           @RequestParam("ExcelFile") MultipartFile multipartFile) {
        Result result=new Result();
        String fileName = multipartFile.getOriginalFilename();
        File file = new File("D://"+fileName);
        try {
            //MultipartFile自带的解析文件的方法
            multipartFile.transferTo(file);
            ArrayList<AcBean> acBeans = acExcelService.importAc(file);
            AcProve acProve=acProveRepository.findOne(stuId);
            int index = 1;
            if (acProve.getAcList() != null) {
                index = acProve.getAcList().size() + 1;
            }
            for (AcBean acBean1 : acBeans) {
                acBean1.setAcId(index);
                acProve.getAcList().add(acBean1);
                index++;
            }
            if(acProveRepository.save(acProve)!=null){
                result.setResultEnum(ResultEnum.IMPORT_AC_SUCCESS);
            }else {
                result.setResultEnum(ResultEnum.UPDATE_AC_FAILD);
            }
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
            file.delete();
        }
        return result;
    }

}
