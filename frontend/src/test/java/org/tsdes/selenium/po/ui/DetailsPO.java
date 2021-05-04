package org.tsdes.selenium.po.ui;


import org.openqa.selenium.By;
import org.tsdes.selenium.PageObject;
import org.tsdes.selenium.po.LayoutPO;


public class DetailsPO extends LayoutPO {


    public DetailsPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().trim().equalsIgnoreCase("Details");
    }


    public boolean checkForUnAuthorizedToWriteReview(){
        return getDriver().findElements(By.id("anonymousDetail")).size() > 0;
    }

    public void writeAReview(String reviewTxt, String stars){
        setText("addReviewText", reviewTxt);
        setText("addStars", stars);
        clickAndWait("addReviewBtn");

    }

    public boolean readReview(String reviewText){
        System.out.println(getText("reviewId"));
        return getText("reviewId").equals("Review: " +reviewText);
    }


}
