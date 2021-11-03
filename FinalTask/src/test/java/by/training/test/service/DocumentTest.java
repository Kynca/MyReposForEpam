package by.training.test.service;

import by.training.finaltask.bean.entities.Document;
import by.training.finaltask.dao.connectionpool.ConnectionPool;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.DocumentService;
import by.training.finaltask.service.ServiceFactory;
import by.training.finaltask.service.excpetion.ServiceException;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class DocumentTest {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    DocumentService documentService;

    @BeforeSuite
    public void initConnection() throws DaoException {
        connectionPool.init("database.properties");
    }

    @BeforeTest
    public void before() throws ServiceException {
        documentService = ServiceFactory.getInstance().getDocumentService();
    }

    @AfterMethod
    public void afterMethod() throws ServiceException {
        documentService = ServiceFactory.getInstance().getDocumentService();
    }

    @Test
    public void viewTypesTest() throws ServiceException {
        assertEquals(documentService.viewTypes().size(), 6);
    }

    @DataProvider(name = "find documents data")
    public Object[][] findDocumentsData() {
        return new Object[][]{
                {new Integer[]{2, 2}, false},
                {new Integer[]{3, 1}, false},
                {new Integer[]{4, 0}, false},
                {new Integer[]{1, 1}, true},
                {new Integer[]{5, 0}, true},
                {new Integer[]{2, 0}, true},
                {new Integer[]{null, 0}, true},
                {new Integer[]{null, 0}, false},
        };
    }

    @Test(description = "find documents by dean_id or student_id", dataProvider = "find documents data")
    public void findDocumentsTest(Integer[] data, boolean isDean) throws ServiceException {
        int result = data[1];
        assertEquals(documentService.viewDocuments(data[0], isDean).size(), result);
    }

    @DataProvider(name = "createData")
    public Object[][] createData() {
    return new Object[][]{
            {new Document(1, false, null, null, null, 2), true},
            {new Document(1, true, null, null, null, 2), true},
    };
}

    @Test(description = "creating document", dataProvider = "createData")
    public void findDocTest(Document document, boolean result) throws ServiceException {
        assertEquals(documentService.createDocument(document), result);
    }

    @DataProvider(name = "incorrectCreateData")
    public Object[][] incorrectCreateData() {
        return new Object[][]{
                {new Document(1, true, "sad", "sda", null, 2), false},
                {new Document(65, true, "sad", "sda", null, 2), false},
        };
    }

    @Test(description = "creating document", dataProvider = "incorrectCreateData", expectedExceptions = {IllegalArgumentException.class})
    public void failedFindDocTest(Document document, boolean result) throws ServiceException {
        assertEquals(documentService.createDocument(document), result);
    }

    @DataProvider(name = "correctUpdateData")
    public Object[][] updateData(){
        return new Object[][]{
                {2, "/filepath", true},

        };
    }

    @Test(description = "updating path", dataProvider = "correctUpdateData")
    public void updateDoc(Integer id, String path, boolean result) throws ServiceException {
        assertEquals(documentService.addFile(path, id), result);
    }

    @DataProvider(name = "incorrectUpdateData")
    public Object[][] incorrectUpdateData(){
        return new Object[][]{
                {1, "/filepath", false},
                {null, "/filepath", false},
        };
    }

    @Test(description = "updating path", dataProvider = "incorrectUpdateData", expectedExceptions = {IllegalArgumentException.class})
    public void failedUpdateDoc(Integer id, String path, boolean result) throws ServiceException {
        assertEquals(documentService.addFile(path, id), result);
    }
}
