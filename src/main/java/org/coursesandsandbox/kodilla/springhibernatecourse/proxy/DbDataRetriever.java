package org.coursesandsandbox.kodilla.springhibernatecourse.proxy;

public interface DbDataRetriever {
    int getFirstValue() throws InterruptedException;
    int getSecondValue() throws InterruptedException;
    int getThirdValue() throws InterruptedException;
}
