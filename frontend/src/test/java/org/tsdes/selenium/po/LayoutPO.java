package org.tsdes.selenium.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tsdes.selenium.PageObject;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Copied from file in repo https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/test/java/org/tsdes/intro/exercises/quizgame/selenium/po/LayoutPO.java
 */
public abstract class LayoutPO extends PageObject {

    public LayoutPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public LayoutPO(PageObject other) {
        super(other);
    }

    public SignUpPO toSignUp(){

        clickAndWait("linkToSignupId");

        SignUpPO po = new SignUpPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public IndexPO doLogout(){

        clickAndWait("logoutId");

        IndexPO po = new IndexPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public boolean isLoggedIn(){

        return getDriver().findElements(By.id("logoutId")).size() > 0 &&
                getDriver().findElements((By.id("linkToSignupId"))).isEmpty();
    }
}
