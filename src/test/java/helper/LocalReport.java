package helper;

import com.github.javafaker.Faker;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LocalReport {
    public static void main(String[] argss) {
        Faker faker = new Faker();
        String version = faker.app().version();

        String ruta = "reports/cucumber/";
        File report = new File(ruta + "FlexBusinessMobile");

        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add(ruta + "report.json");

        Configuration configuration = new Configuration(report, "Flex Business \n" +
                "Mobile Test Automation Project");
        configuration.setBuildNumber(version);
        configuration.addClassifications("Owner", "Test Running S.A");
        configuration.addClassifications("Environment ", "Android");
        configuration.addClassifications("Universidad", "Universidad Católica Boliviana San Pablo - La Paz");
        configuration.addClassifications("Diplomado", "Testing de Software vesión 3");
        configuration.addClassifications("Paises de Estudiantes", "Bolivia y Perú");
        configuration.setTrendsStatsFile(new File( "reports/trends.json"));

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

}
