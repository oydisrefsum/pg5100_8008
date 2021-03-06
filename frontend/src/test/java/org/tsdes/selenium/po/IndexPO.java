package org.tsdes.selenium.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tsdes.selenium.PageObject;
import org.tsdes.selenium.po.ui.DetailsPO;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Adapted from file in repo https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/test/java/org/tsdes/intro/exercises/quizgame/selenium/po/IndexPO.java
 */

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


    public int checkMovieList() {
        return getDriver().findElements(By.className("movies")).size();
    }

    public boolean canSelectMovies(){
        return getMovieIds().size() > 0;
    }
    public List<String> getMovieIds(){

        return getDriver().findElements(By.xpath("//input[@data-movieid]"))
                .stream()
                .map(e -> e.getAttribute("data-movieid"))
                .collect(Collectors.toList());
    }

    public DetailsPO chooseAMovie(String id){

        clickAndWait("movieBtnId_" + id);
        DetailsPO po = new DetailsPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public String getStarsForAMovie(String id){

        return getText("movieStarsId_"+id);
    }

}
