package org.tsdes.selenium;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.tsdes.selenium.po.IndexPO;
import org.tsdes.selenium.po.SignUpPO;
import org.tsdes.selenium.po.ui.DetailsPO;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;


public abstract class SeleniumTestBase {


    protected abstract WebDriver getDriver();

    protected abstract String getServerHost();

    protected abstract int getServerPort();


    private static final AtomicInteger counter = new AtomicInteger(0);

    private String getUniqueId() {
        return "testuser" + counter.getAndIncrement();
    }


    private IndexPO home;


    private IndexPO createNewUser(String username, String password) {

        home.toStartingPage();

        SignUpPO signUpPO = home.toSignUp();

        IndexPO indexPO = signUpPO.createUser(username, password);
        assertNotNull(indexPO);

        return indexPO;
    }

    @BeforeEach
    public void initTest() {

        getDriver().manage().deleteAllCookies();

        home = new IndexPO(getDriver(), getServerHost(), getServerPort());

        home.toStartingPage();

        assertTrue(home.isOnPage(), "Failed to start from Home Page");
    }

    @Test
    public void testCreateAndLogoutUser() {

        assertFalse(home.isLoggedIn());

        String username = getUniqueId();
        String password = "123456789";
        home = createNewUser(username, password);

        assertTrue(home.isLoggedIn());
        assertTrue(home.getDriver().getPageSource().contains(username));

        home.doLogout();

        assertFalse(home.isLoggedIn());
        assertFalse(home.getDriver().getPageSource().contains(username));
    }

    @Test
    public void testDefaultMovies() {

        createNewUser(getUniqueId(), "123");
        assertTrue(home.checkMovieList() > 2);
    }

    @Test
    public void testWriteReview(){
        String movieId = home.getMovieIds().get(2);

        assertTrue(home.catSelectMovies());

        DetailsPO po = home.chooseAMovie(movieId);
        assertFalse(home.catSelectMovies());

        assertTrue(po.checkForUnAuthorizedToWriteReview());

        createNewUser(getUniqueId(), "123");

        DetailsPO po1 = home.chooseAMovie(movieId);
        assertFalse(home.catSelectMovies());

        assertFalse(po1.checkForUnAuthorizedToWriteReview());

        po1.writeAReview("Good! from test", "5");
        assertTrue(po1.readReview("Good! from test"));

        po1.doLogout();

        home.chooseAMovie(movieId);
        assertTrue(po1.readReview("Good! from test"));

    }

    @Test
    public void testStars(){

        String movieId = home.getMovieIds().get(6);

        assertTrue(home.catSelectMovies());

        assertEquals("1/5" ,home.getStarsForAMovie(movieId));

        createNewUser(getUniqueId(), "123");
        DetailsPO po = home.chooseAMovie(movieId);
        assertFalse(home.catSelectMovies());
        po.writeAReview("Excellent", "5");

        assertTrue(po.readReview("Excellent"));

        po.doLogout();

        assertTrue(home.catSelectMovies());
        assertEquals("2/5" ,home.getStarsForAMovie(movieId));


    }

}
