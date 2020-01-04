package cn.medemede.j2ee.controller;


import cn.medemede.j2ee.model.Region;
import cn.medemede.j2ee.repository.RegionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 地区数据处理
 * @author Saber
 */
@RestController
public class RegionController {

    @Resource
    private RegionRepository regionRepository;

    @RequestMapping("/getregion")
    public String getRegion(@RequestParam("parentId") int parentId,
                            @RequestParam(value = "citySelected",required = false) String citySelected ) {

        StringBuilder regionStr= new StringBuilder();
        if(citySelected!=null){
            List<Region> regions=regionRepository.findByRegionNameAndRegionId(citySelected,parentId);
            for (Region region : regions) {
                regionStr.append(region.getRegionName()).append(",");
            }
            return regionStr.toString();
        }else {
            List<Region> regions=regionRepository.findByParentId(parentId);
            for (Region region : regions) {
                regionStr.append(region.getRegionName()).append(",");
            }
        }

        return regionStr.toString();
    }
}
