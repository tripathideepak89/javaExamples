package com.javaexamples;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Transaction Processing App to show the examples of Java Streams
 *
 */
public class App 
{
    private static final Logger LOG = LogManager.getLogger(App.class);
    public static void main( String[] args )
    {
        App app = new App();
        Map<Integer, List<Object>> data = app.inputData();
        app.transactionprocessing(data);
    }

    private Map<Integer, List<Object>> inputData() {
        String fileLocation = "src/main/resources/Barcelona_trip_expenses.xlsx";
        FileInputStream file = null;
        Workbook workbook = null;
        try  {
            file = new FileInputStream(new File(fileLocation)) ;
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            LOG.info(e);
        }

        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<Object>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<Object>());
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING: data.get(Integer.valueOf(i)).add(cell.getStringCellValue()); break;
                    case NUMERIC: data.get(Integer.valueOf(i)).add(cell.getNumericCellValue()); break;
                    case BOOLEAN: data.get(Integer.valueOf(i)).add(cell.getBooleanCellValue());  break;
                    case FORMULA: data.get(Integer.valueOf(i)).add(cell.getRichStringCellValue().getString()); break;
                    default: data.get(Integer.valueOf(i)).add(" ");
                }
            }
            i++;
        }

        return data;     
    }


    /**
     * @param data
     * 
     */
    private void transactionprocessing(Map<Integer, List<Object>> data) {
        TransactionProcess transactionProcess = new TransactionProcess();
        transactionProcess = createTransactionList(transactionProcess, data);        
        printTransactions(transactionProcess);
        getTransactionTotalByCategory(transactionProcess);
        searchTransaction(transactionProcess, 10);        
    }


    private void searchTransaction(TransactionProcess transactionProcess, Integer id) {
        transactionProcess.getTransaction(id).ifPresent(System.out::println);
    }


    private void getTransactionTotalByCategory(TransactionProcess transactionProcess) {
        transactionProcess.getTotalValue().forEach((key, value) -> System.out.println(key + " : " + value));
    }


    private void printTransactions(TransactionProcess transactionProcess) {
        transactionProcess.getTransactionList().forEach(System.out::println);
    }


    private TransactionProcess createTransactionList(TransactionProcess transactionProcess, Map<Integer, List<Object>> data) {
        for( List<Object> value : data.values()) {
            try { 
                Integer.parseInt(value.get(0).toString()) ;
                transactionProcess.addTransaction(new Transaction(Integer.valueOf(value.get(0).toString()),value.get(1).toString(), value.get(2).toString(), Double.valueOf(value.get(3).toString()), value.get(4).toString()));
             }catch(NumberFormatException e){
                LOG.info(e);
                }
        }
        return transactionProcess;
    }
}
