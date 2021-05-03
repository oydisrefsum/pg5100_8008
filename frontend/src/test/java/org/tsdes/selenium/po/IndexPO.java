package org.tsdes.selenium.po;

import org.openqa.selenium.WebDriver;
import org.tsdes.selenium.PageObject;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class IndexPO extends LayoutPO {

    public IndexPO(PageObject other) {
        super(other);
    }

    public IndexPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public void toStartingPage(){
        toOrigin();
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Movie Guide");
    }

    /*public MatchPO startNewMatch(){

        clickAndWait("newMatchBtnId");
        MatchPO po = new MatchPO(this);
        assertTrue(po.isOnPage());

        return po;
    }*/
}
