package com.udacity.rw.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class HomePage {

  @FindBy(id = "logout-button")
  private WebElement logoutButton;

  @FindBy(id = "nav-notes-tab")
  private WebElement noteTab;
  // note of these are avialable until the tab is clicked.
  private WebElement addNoteButton;
  private WebElement noteTitle;
  private WebElement noteDescription;

  @FindBy(id = "note-submit")
  private WebElement submitNoteButton;

  @FindBy(id = "nav-credentials-tab")
  private WebElement credentialTab;

  private WebDriverWait wait;

  private WebDriver webDriver;


  public HomePage(WebDriver webDriver,WebDriverWait wait){
    this.webDriver = webDriver;
    this.wait = wait;
    PageFactory.initElements(webDriver,this);
  }

  public void logout(){
    logoutButton.click();
  }

  public void createNote(String noteTitle, String noteDescription) throws InterruptedException {
    Thread.sleep(1000);
    //this.wait.until(ExpectedConditions.visibilityOf(this.noteTab));
    this.noteTab.click();

    this.wait.until(ExpectedConditions.elementToBeClickable(By.id("add-note")));
    addNoteButton = webDriver.findElement(By.id("add-note"));
    this.addNoteButton.click();

    this.noteTitle = webDriver.findElement(By.id("note-title"));
    this.wait.until(ExpectedConditions.visibilityOf(this.noteTitle));
    this.noteTitle.sendKeys(noteTitle);

    this.noteDescription = webDriver.findElement(By.id("note-description"));
    this.noteDescription.sendKeys(noteDescription);
    this.submitNoteButton.click();
  }

  public void isNoteDeleted() throws InterruptedException {
    Thread.sleep(1000);
    //this.wait.until(ExpectedConditions.visibilityOf(this.noteTab));
    this.noteTab.click();

    List rows = webDriver.findElements(By.xpath("//*[@id='userTable']/tbody/tr"));
    Assertions.assertEquals(0,rows.size());
  }

  public void isNoteEdited() throws InterruptedException {
    Thread.sleep(1000);
    this.noteTab.click();
    //Thread.sleep(1000);
    this.wait.until(ExpectedConditions.visibilityOf
            (this.webDriver.findElement(By.xpath("//*[@id='userTable']/tbody/tr/th"))));
    WebElement noteTitle = this.webDriver.findElement(By.xpath("//*[@id='userTable']/tbody/tr/th"));
    WebElement noteDesc = this.webDriver.findElement(By.xpath("//*[@id='userTable']/tbody/tr/td[2]"));

    Assertions.assertEquals("Tests",noteTitle.getText());
    Assertions.assertEquals("Testing is fun! edited",noteDesc.getText());
  }

  public void editNote(String noteTitle, String noteDescription) throws InterruptedException {
    Thread.sleep(1000);
    //this.wait.until(ExpectedConditions.visibilityOf(this.noteTab));
    this.noteTab.click();

    WebElement editButton = this.webDriver.findElement(By.xpath("//*[@id='userTable']/tbody/tr/td[1]/button"));
    this.wait.until(ExpectedConditions.elementToBeClickable(editButton));
    editButton.click();

    this.noteTitle = webDriver.findElement(By.id("note-title"));
    this.wait.until(ExpectedConditions.visibilityOf(this.noteTitle));
    this.noteTitle.sendKeys(noteTitle);

    this.noteDescription = webDriver.findElement(By.id("note-description"));
    this.noteDescription.sendKeys(noteDescription);
    this.submitNoteButton.click();
  }

  public void deleteNote() throws InterruptedException {
    Thread.sleep(1000);
    this.noteTab.click();
    WebElement deleteButton = this.webDriver.findElement(By.xpath("//*[@id='userTable']/tbody/tr/td[1]/a"));
    this.wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
    deleteButton.click();
  }
}
