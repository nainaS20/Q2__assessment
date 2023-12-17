package org.example;
import com.itextpdf.layout.element.Cell;
import com.mysql.cj.result.Row;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;


public class ExcelFileDataReader {
    public static boolean isRowEmpty(Row row){
        for(Cell cell:row){
            if(cell!=null && cell.getCellType()!=CellType.BLANK){
                return false;
            }

        }
        return true;
    }

    public static List<ExcelData>readExcel(String filePath) throws IOException{
        List<ExcelData>dataList=new ArrayList<>();
        try(FileInputStream fileInputStream=new FileInputStream(new File(filePath));
            Workbook workbook=new XSSFWorkbook(fileInputStream)){
            Sheet sheet=workbook.getSheetAt(0);
            int r=0;
            Iterator<org.apache.poi.ss.usermodel.Row> rowIterator=sheet.iterator();
            while(rowIterator.hasNext()){
                Row row= (Row) rowIterator.next();
                if(isRowEmpty(row)){
                    break;
                }
                ExcelData data=new ExcelData();
                if(r==0){
                    r++;
                    continue;
                }
                Cell dataCell=row.getCell(0);
                try{
                    Date dateValue=dateCell.getDateCellValue();
                    SimpleDateFormat dataFormat=new SimpleDateFormat("dd-mm-yy");
                    data.setDate(dateFormat.format(dataValue));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                Cell monthCell =row.getCell(1);
                if(monthCell !=null){
                    String monthValue;
                    if(monthCell.getCellType() == CellType.Numeric){
                        monthValue=String.valueOf(monthCell.getStringCellValue());
                    }else{
                        monthValue=monthCell.getStringCellValue();
                    }
                    data.setMonth(monthValue);
                }
                data.setTeam(row.getCell(2).getStringCellValue());
                data.setPanelName(row.getCell(3).getStringCellValue());
                data.setRound(row.getCell(4).getStringCellValue());
                data.setSkill(row.getCell(5).getStringCellValue());

                Cell timeCell=row.getCell(6);

                if(timeCell!=null) {
                    String timeValue;
                    if (timeCell.getCellType() == CellType.NUMERIC) {
                        Date dateValue = timeCell.getDateCellValue();
                        SimpleDataFormat timeFormat = new SimpleDateFormat("h:mm a");
                        timeValue = timeFormat.format(dataValue);
                    } else {
                        timeValue = timeCell.getStringCellValue();
                    }
                    data.setTime(timeValue);
                }
                data.setCandidate_Current_Loc(row.getCell(7).getStringValue());
                data.setCandidate_Preferred_location(row.getCell(8).getStringCellValue());
                data.setCandidate_name(row.getCell(9).getStringCellValue());
                dataList.add(data);
            }

        }
        return dataList;

    }
}
