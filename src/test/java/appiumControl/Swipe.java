package appiumControl;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import singletonSession.Session;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Swipe {

    final int ANIMATION_TIME = 200; // ms
    final int PRESS_TIME = 200; // ms

    int edgeBorder = 10; // better avoid edges
    PointOption pointOptionStart, pointOptionEnd;

    // init screen variables
    // tamaño de la pantalla actual , en este caso de Información Cliente
    Dimension dims = Session.getInstance().getDriver().manage().window().getSize();

    public void swipeScreen(Direction dir) {
        System.out.println("swipeScreen(): dir: '" + dir + "'");

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN:
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                irSiguientePantalla();
                break;
            case UP:
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                irSiguientePantalla();
                break;
            case LEFT:
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                irSiguientePantalla();
                break;
            case RIGHT:
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                irSiguientePantalla();
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

    }

    public void irSiguientePantalla() {

        TouchAction touchAction;
        try {
            new TouchAction(Session.getInstance().getDriver())
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
        } finally {
            Session.getInstance().getDriver().manage().timeouts().implicitlyWait(ANIMATION_TIME, TimeUnit.MICROSECONDS);
        }
    }
}
