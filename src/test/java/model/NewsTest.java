package model;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NewsTest{

    public void setUp() throws Exception {
    }

    public void tearDown() throws Exception {
    }

    @Test
    public void returnInstancesOfNews_true(){
        News firstNews=new News(0,"Holiday",0);
        assertEquals(true, firstNews instanceof News);
    }

    @Test
    public void returnIdOfNews_int(){
        News firstNews=new News(0,"Holiday",0);
        assertEquals(0,firstNews.getId());
    }

    @Test
    public void returnContentOfNews_String(){
        News firstNews=new News(0,"Holiday",0);
        assertEquals("Holiday",firstNews.getContent());
    }

    @Test
    public void returnUserIdOfNews_int(){
        News firstNews=new News(0,"Holiday",0);
        assertEquals(0,firstNews.getUserId());
    }
}