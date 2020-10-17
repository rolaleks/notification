package ru.geekbrains.parserservice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.geekbrains.model.Task;
import ru.geekbrains.service.parserservice.ParserService;
import ru.geekbrains.service.requesthandler.TaskService;

import static org.mockito.Mockito.*;

@Ignore("тест игнорируется до написания парсеров")
@RunWith(MockitoJUnitRunner.class)
public class ParserServiceTest {

    @Mock
    TaskService taskService;

    ParserService parserService;

    @Before
    public void config(){
//        when(taskService.peek()).thenReturn(new Task());
//        parserService = new ParserService(taskService);
    }

    @Test
    public void parserServiceTest() throws Exception {
        //Assert.assertEquals(captorValue.getValue(), APPROVE.name());
    }
}
