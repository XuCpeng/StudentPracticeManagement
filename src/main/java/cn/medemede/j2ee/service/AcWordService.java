package cn.medemede.j2ee.service;

import cn.medemede.j2ee.model.AcProve;
import cn.medemede.j2ee.util.ExportWord2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AcWordService {

    public boolean exportWord(AcProve acProve, HttpServletResponse response) throws IOException {
        String resourcePath = "/demo/acProve.xml";
        String templatePath = "acProve.xml";

        Map<String, Object> data = new HashMap<>();
        data.put("acProve", acProve);

        ExportWord2.exportWord(resourcePath, templatePath, "证明.doc", data, response);

        return true;
    }
}
