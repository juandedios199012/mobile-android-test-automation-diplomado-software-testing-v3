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

        Configuration configuration = new Configuration(report, "Flex Business Mobile");
        configuration.setBuildNumber(version);
        configuration.addClassifications("S.O", "Android");
        configuration.addClassifications("Congreso", "Testing Bolivia 2023");
        configuration.addClassifications("Anfitrion", "Universidad Privada de Santa Cruz de la Sierra");
        configuration.setTrendsStatsFile(new File( "reports/trends.json"));

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

}
