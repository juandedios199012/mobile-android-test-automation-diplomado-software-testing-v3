package helper;

import com.github.javafaker.Faker;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LocalReport {
   // public static void main(String[] argss) {
   public static void generateReport () {
        var faker = new Faker();
        String version=faker.app().version();

        String ruta = "build/reports/cucumber/";
        File report = new File(ruta + "TiendApp");

        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add(ruta + "report.json");

        Configuration configuration = new Configuration(report, "TiendApp Test Automation Project");
        configuration.setBuildNumber(version);
        configuration.addClassifications("Owner", "Test Running S.A");
        configuration.addClassifications("Environment ", "Android");
        configuration.addClassifications("Tipo", "Local");
        configuration.addClassifications("Branch", "master");
        configuration.addClassifications("Universidad", "Universidad Católica Boliviana San Pablo - La Paz");
        configuration.addClassifications("Diplomado", "Testing de Software vesión 3");
        configuration.addClassifications("Paises de Estudiantes", "Bolivia y Perú");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

}
