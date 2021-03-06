package model;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NewsTest{

    public void setUp() throws Exception {
    }

    public void tearDown() throws Exception {
    }

    @Test
    public void returnInstancesOfNews_true(){
        News firstNews=new News(0,"Holiday",0,0);
        assertEquals(true, firstNews instanceof News);
    }

    @Test
    public void returnIdOfNews_int(){
        News firstNews=new News(0,"Holiday",0,0);
        assertEquals(1,firstNews.getId());
    }

    @Test
    public void returnContentOfNews_String(){
        News firstNews=new News(0,"Holiday",0,0);
        assertEquals("Holiday",firstNews.getContent());
    }

    @Test
    public void returnUserIdOfNews_int(){
        News firstNews=new News(0,"Holiday",0,0);
        assertEquals(0,firstNews.getUserId());
    }

    @Test
    public void firstNews_getAllInstances_true(){
        News firstNews=News.setUpNewNews();
        assertTrue(News.getAllInstances().contains(firstNews));
    }

    @Test
    public void clearAllNewsCorrectly_0(){
        News.clearAllNews();
        assertEquals(News.getAllInstances().size(),0);
    }

}