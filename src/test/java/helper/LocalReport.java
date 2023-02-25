package helpers;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LocalReport {
     public static void main (String[]argsss){
           String ruta="C:\\Users\\Eynar\\Desktop\\JBMovileAbril\\jb_movile_abril\\build\\reports\\cucumber\\";
           File report= new File(ruta+"JBReport");

           List<String> jsonFiles= new ArrayList<>();
           jsonFiles.add(ruta+"report.json");

         Configuration configuration = new Configuration(report,"Movile JBGROUP");
         configuration.setBuildNumber("100000");
         configuration.addClassifications("Owner","JB");
         configuration.addClassifications("Env","Android");
         configuration.addClassifications("Tipo","Local");
         configuration.addClassifications("Branch","master");

         ReportBuilder reportBuilder= new ReportBuilder(jsonFiles,configuration);
         reportBuilder.generateReports();
     }

}
