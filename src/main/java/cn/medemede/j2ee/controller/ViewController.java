package cn.medemede.j2ee.controller;

import cn.medemede.j2ee.model.AcProve;
import cn.medemede.j2ee.model.Active;
import cn.medemede.j2ee.model.JUserRole2;
import cn.medemede.j2ee.model.Stu;
import cn.medemede.j2ee.repository.AcProveRepository;
import cn.medemede.j2ee.repository.ActiveRepository;
import cn.medemede.j2ee.repository.JUserRole2Repository;
import cn.medemede.j2ee.repository.StuRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 视图获取控制器
 * @author Saber
 */
@Controller
public class ViewController {

    @Resource
    private AcProveRepository acProveRepository;
    @Resource
    private JUserRole2Repository jUserRole2Repository;
    @Resource
    private ActiveRepository activeRepository;
    @Resource
    private StuRepository stuRepository;

    @GetMapping(value = {"/","/login"})
    public String index(){
        return "sign-in";
    }

    @GetMapping("/signin")
    public String signin(){
        return "sign-in";
    }

    @GetMapping("/signup")
    public String signUp(){
        return "sign-up2";
    }

    @GetMapping("/stu/{stuId}")
    public String stuInfo(@PathVariable("stuId") String stuId, Model model){
        AcProve acProve=acProveRepository.findOne(stuId);
        model.addAttribute("acProve",acProve);
        return "stu-info";
    }

    @GetMapping("/admin/{adminId}")
    public String admininfo(@PathVariable("adminId") String adminId,Model model){
        AcProve acProve=acProveRepository.findOne(adminId);
        List<JUserRole2> stuList=jUserRole2Repository.findByRoleName("stu");
        List<AcProve> proveList=new ArrayList<>();
        for(JUserRole2 jUserRole2:stuList){
            AcProve acProve1=acProveRepository.findOne(jUserRole2.getStuId());
            if (acProve1!=null&&acProve1.getStuName()!=null) {
                proveList.add(acProve1);
            }
        }
        List<Active> activeList=activeRepository.findAll();
        model.addAttribute("acProve",acProve);
        model.addAttribute("proveList",proveList);
        model.addAttribute("activeList",activeList);
        return "admin-info";
    }

    @GetMapping("/monitor/{stuId}")
    public String monitorinfo(@PathVariable("stuId") String stuId,Model model){

        Stu monitor=stuRepository.findOne(stuId);
        List<Stu> stuList=stuRepository.findByLevelAndKlass(monitor.getLevel(),monitor.getKlass());
        List<Active> activeList=activeRepository.findAll();

        model.addAttribute("monitor",monitor);
        model.addAttribute("activeList",activeList);
        return "monitor";
    }
}
