package cn.medemede.j2ee.controller;

import cn.medemede.j2ee.model.AcProve;
import cn.medemede.j2ee.repository.AcProveRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视图获取控制器
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

    @GetMapping("/admininfo")
    public String admininfo(Model model){
        List<AcProve> proveList=acProveRepository.findAll();
        model.addAttribute("proveList",proveList);
        return "admin-info";
    }

}
