package cn.medemede.j2ee.service;

import cn.medemede.j2ee.model.AcBean;
import cn.medemede.j2ee.model.Active;
import cn.medemede.j2ee.repository.ActiveRepository;
import cn.medemede.j2ee.repository.StuRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Saber
 */
@Service
public class AcExcelService {

    @Resource
    private ActiveRepository activeRepository;
    @Resource
    private StuRepository stuRepository;

    /**
     * Excel的列数
     */
    private int cellNum=6;
    private int cellNum2=2;

    public ArrayList<AcBean> importAc(File file) throws IOException {
        ArrayList<AcBean> acBeans;
        //声明工作簿
        Workbook workbook;
        //声明工作表
        Sheet sheet;
        try {
            //初始化工作簿，Excel 2007及以上，xlsx
            workbook = new XSSFWorkbook(file);
            //初始化工作表,默认第一个工作表
            sheet = workbook.getSheetAt(0);
            acBeans=getAc(sheet);
        } catch (InvalidFormatException e) {
            //初始化工作簿，Excel 2007以下
            workbook = new HSSFWorkbook(new FileInputStream(file));
            //初始化工作表
            sheet = workbook.getSheetAt(0);
            acBeans=getAc(sheet);
        }
        workbook.close();
        return acBeans;
    }


    public ArrayList<AcBean> importFromMon(String acName,File file) throws IOException {
        Active active=activeRepository.findOne(acName);
        ArrayList<AcBean> acBeans;
        //声明工作簿
        Workbook workbook;
        //声明工作表
        Sheet sheet;
        try {
            //初始化工作簿，Excel 2007及以上，xlsx
            workbook = new XSSFWorkbook(file);
            //初始化工作表,默认第一个工作表
            sheet = workbook.getSheetAt(0);
            acBeans=getAc(sheet);
        } catch (InvalidFormatException e) {
            //初始化工作簿，Excel 2007以下
            workbook = new HSSFWorkbook(new FileInputStream(file));
            //初始化工作表
            sheet = workbook.getSheetAt(0);
            acBeans=getAc(sheet,active);
        }
        workbook.close();
        return acBeans;
    }

    private ArrayList<AcBean> getAc(Sheet sheet){
        ArrayList<AcBean> acBeans=new ArrayList<>();
        for (int i = 1; i <sheet.getLastRowNum()+1; i++) {
            AcBean acBean=new AcBean();
            //定位一行
            Row row=sheet.getRow(i);
            //初始单元格
            Cell[] cell = new Cell[cellNum];
            for (int j = 0; j < cellNum; j++) {
                //定位一列
                cell[j] = row.getCell(j);
                //设置单元格的读取类型
                cell[j].setCellType(CellType.STRING);
            }
            //封装一个活动对象
            acBean.setAcName(cell[0].getStringCellValue());
            acBean.setAcTime(cell[1].getStringCellValue());
            acBean.setAcHour(Float.valueOf(cell[2].getStringCellValue()));
            acBean.setAcRole(cell[3].getStringCellValue());
            acBean.setAcUnit(cell[4].getStringCellValue());
            acBean.setWitne(cell[5].getStringCellValue());
            //将该活动添加到list
            acBeans.add(acBean);
        }
        return acBeans;
    }

    private ArrayList<AcBean> getAc(Sheet sheet,Active active){
        ArrayList<AcBean> acBeans=new ArrayList<>();
        for (int i = 1; i <sheet.getLastRowNum()+1; i++) {
            AcBean acBean=new AcBean();
            //定位一行
            Row row=sheet.getRow(i);
            //初始单元格
            Cell[] cell = new Cell[cellNum2];
            for (int j = 0; j < cellNum2; j++) {
                //定位一列
                cell[j] = row.getCell(j);
                //设置单元格的读取类型
                cell[j].setCellType(CellType.STRING);
            }
            //封装一个活动对象
            acBean.setStuId(cell[0].getStringCellValue());
            acBean.setAcHour(Float.valueOf(cell[1].getStringCellValue()));
            acBean.setAcRole(cell[2].getStringCellValue());
            acBean.setWitne(cell[3].getStringCellValue());

            acBean.setAcName(active.getAcName());
            acBean.setAcTime(active.getAcTime());
            acBean.setAcUnit(active.getAcUnit());

            //将该活动添加到list
            acBeans.add(acBean);
        }
        return acBeans;
    }
}
