package cn.medemede.j2ee.service;

import cn.medemede.j2ee.model.AcProve;
import cn.medemede.j2ee.model.Result;
import cn.medemede.j2ee.util.ExportWord2;
import cn.medemede.j2ee.util.ZipUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Saber
 */
@Service
public class AcWordService {
    private String resourcePath = "/demo/acProve.xml";
    private String templatePath = "acProve.xml";

    public boolean exportWord(AcProve acProve, HttpServletResponse response) throws IOException {

        Map<String, Object> data = new HashMap<>();
        data.put("acProve", acProve);

        ExportWord2.exportWord(resourcePath, templatePath, acProve+".doc", data, response);

        return true;
    }

    public Result exportAcList(List<AcProve> acProves, HttpServletResponse response) {
        Result result=new Result();
        int count=0;
        int error=0;
        List<String> fileName=new ArrayList<>();
        for (AcProve acProve:acProves){
            Map<String, Object> data = new HashMap<>();
            data.put("acProve", acProve);
            fileName.add(acProve.getStuId()+".doc");
            try {
                if(ExportWord2.creatDoc(data,resourcePath,templatePath,acProve.getStuId())){
                    count++;
                }else {
                    error++;
                }
            } catch (IOException e) {
                error++;
            }
        }

        ZipUtil.downloadZip("./doctemp",".zip",fileName,response);
        result.setData("count:"+count+","+"error:"+error);
        return result;
    }
}
