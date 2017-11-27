package cn.medemede.j2ee.controller;

import cn.medemede.j2ee.model.AcProve;
import cn.medemede.j2ee.model.JUserRole2;
import cn.medemede.j2ee.repository.AcProveRepository;
import cn.medemede.j2ee.repository.JUserRole2Repository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 视图获取控制器
 * @author Saber
 */
@Controller
public class ViewController {

    @GetMapping("/")
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

    @Resource
    private AcProveRepository acProveRepository;
    @GetMapping("/stuinfo/{stuId}")
    public String stuInfo(@PathVariable("stuId") String stuId, Model model){
        AcProve acProve=acProveRepository.findOne(stuId);
        model.addAttribute("acProve",acProve);
        return "stu-info";
    }

    @Resource
    private JUserRole2Repository jUserRole2Repository;
    @GetMapping("/admininfo/{adminId}")
    public String admininfo(@PathVariable("adminId") String adminId,Model model){
        AcProve acProve=acProveRepository.findOne(adminId);
        List<JUserRole2> stuList=jUserRole2Repository.findByRoleName("stu");
        List<AcProve> proveList=new ArrayList<>();
        for(JUserRole2 jUserRole2:stuList){
            proveList.add(acProveRepository.findOne(jUserRole2.getStuId()));
        }
        model.addAttribute("acProve",acProve);
        model.addAttribute("proveList",proveList);
        return "admin-info";
    }

}
