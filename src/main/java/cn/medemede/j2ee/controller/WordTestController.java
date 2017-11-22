package cn.medemede.j2ee.controller;

import cn.medemede.j2ee.model.AcBean;
import cn.medemede.j2ee.model.AcProve;
import cn.medemede.j2ee.util.ExportWord2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
public class WordTestController {

    /**
     * 导出Word
     * @throws IOException
     */
    @RequestMapping(value = "/export")
    public void exportW() throws IOException {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        String resourcePath = "/demo/acProve.xml";
        String templatePath = "acProve.xml";

        Set<AcBean> acList = new HashSet<>();
        AcBean ac1 = new AcBean();
        AcBean ac2 = new AcBean();
        AcProve acProve = new AcProve();

        ac1.setAcId(1);
        ac1.setAcName("活动1");
        ac1.setAcTime("2017-11-21");
        ac1.setAcHour((float) 9);
        ac1.setAcRole("小组长");
        ac1.setAcUnit("学生会");
        ac1.setWitne("辅导员");
        acList.add(ac1);

        ac2.setAcId(2);
        ac2.setAcName("活动2");
        ac2.setAcTime("2017-11-21");
        ac2.setAcHour((float) 9);
        ac2.setAcRole("小组长");
        ac2.setAcUnit("学生会");
        ac2.setWitne("辅导员");
        acList.add(ac2);

        acProve.setStuName("徐传鹏");
        acProve.setSex("男");
        acProve.setBirth("1997-04-16");
        acProve.setLevel("2015");
        acProve.setKlass("二");
        acProve.setStuId("20153555");
        acProve.setStartY("2017");
        acProve.setStartM("6");
        acProve.setEndY("2017");
        acProve.setEndM("9");
        acProve.setSchool("山东农业大学");
        acProve.setProveDate(new Date());
        acProve.setAcList(acList);

        Map<String, Object> data2 = new HashMap<>();
        data2.put("acProve", acProve);

        ExportWord2.exportWord(resourcePath, templatePath, "证明.doc", data2, response);

    }
}
