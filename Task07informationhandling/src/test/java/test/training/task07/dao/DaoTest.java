package test.training.task07.dao;

import by.training.task07.dao.FileOperations;
import by.training.task07.dao.exception.DaoException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;


public class DaoTest {
    FileOperations fileOperations = new FileOperations();
    final String PATH ="F:\\Tasks\\Task07informationhandling\\src\\test\\resources\\";

    @DataProvider(name = "data for reading")
    public Object[][] dataForTest(){
        return new Object[][]{
                {PATH + "test1.txt", "    This is the text! Do you know what's here? I am not, that's kinda funny. Why?! Hehe, here some fact's for you: 1 this is test text, second this is TEST text third i don't know what should be the third reason." + System.lineSeparator() +
                        "    So i hope my parser parse this text right. It's so exciting! Ok, this is lexeme parse test; this text should be divided into 3 parts." + System.lineSeparator() +
                        "    And have 2 or 3, cause i have 3 tabulation marks, so, lets text it! Im so excited! Let's GOOO!" + System.lineSeparator() +
                        "    Ok?! Final test HERE WE COME! Haha?! Please: work."},
                {PATH + "test2.txt", "    It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 30>>>3 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum." + System.lineSeparator() +
                        "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English." + System.lineSeparator() +
                        "\tIt is a (8^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout." + System.lineSeparator() +
                        "\tBye."}
        };
    }

    @Test(description = "testing how method read text", dataProvider = "data for reading")
    public void testReading(String filename, String result) throws DaoException {
        assertEquals(fileOperations.readFile(filename),result);
    }
}
