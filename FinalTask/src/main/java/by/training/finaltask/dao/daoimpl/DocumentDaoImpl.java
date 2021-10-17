package by.training.finaltask.dao.daoimpl;

import by.training.finaltask.bean.entities.Document;
import by.training.finaltask.dao.DocumentDao;
import by.training.finaltask.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentDaoImpl extends BaseDao implements DocumentDao {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    private static final String SELECT = "SELECT document.id, order_date, dt.type, status, delivery_type, comment, document, student_id, receiver_name, receiver_mail " +
            " FROM document INNER JOIN document_type dt on document.type_id = dt.id";
    private static final String CREATE = "INSERT INTO document(order_date, type_id, status, delivery_type, comment, student_id, receiver_name, receiver_mail) VALUES (?,?,?,?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM document WHERE id = ?";
    private static final String UPDATE = "UPDATE document SET document = ? WHERE id = ?";
    private static final String SELECT_BY_DEAN = "SELECT document.id, order_date, dt.type, status, delivery_type, comment, document, student_id, receiver_name, receiver_mail" +
            " FROM  document INNER JOIN student s ON document.student_id = s.user_id JOIN document_type dt on document.type_id = dt.id  WHERE dean_id = ?";
    private static final String SELECT_BY_USER = SELECT + " WHERE student_id = ?";
    private static final String SELECT_TYPES = "SELECT id, type FROM document_type";
    private static final String UPDATE_STUD = "UPDATE document SET student_id = NULL WHERE student_id = ?";

    @Override
    public List<Document> findAll() throws DaoException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            List<Document> documents = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SELECT);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String orderDate = resultSet.getString("order_date");
                String type = resultSet.getString("dt.type");
                boolean status = resultSet.getBoolean("status");
                boolean deliveryType = resultSet.getBoolean("delivery_type");
                String comment = resultSet.getString("comment");
                Integer studentId = resultSet.getInt("student_id");
                String receiverName = resultSet.getString("receiver_name");
                String receiverMail = resultSet.getString("receiver_mail");
                Document document = new Document(id, orderDate, type, status, deliveryType, receiverName, receiverMail, comment, studentId);
                documents.add(document);
            }
            return documents;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }



    @Override
    public Document findById(Integer id) throws DaoException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT + " WHERE id =" + id);
            if (resultSet.next()) {
                String orderDate = resultSet.getString("order_date");
                String type = resultSet.getString("dt.type");
                boolean status = resultSet.getBoolean("status");
                boolean deliveryType = resultSet.getBoolean("delivery_type");
                String comment = resultSet.getString("comment");
                Integer studentId = resultSet.getInt("student_id");
                String receiverName = resultSet.getString("receiver_name");
                String receiverMail = resultSet.getString("receiver_mail");
//              TODO  document.setDocumentType(resultSet.getString("document_type"));
                Document document = new Document(id, orderDate, type, status, deliveryType, receiverName, receiverMail, comment, studentId);
                return document;
            }
            return null;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean create(Document document) throws DaoException {

        try {
            PreparedStatement statement = connection.prepareStatement(CREATE);
            statement.setDate(1, Date.valueOf(document.getOrderDate()));
            statement.setInt(2, document.getTypeId());
            statement.setBoolean(3, document.getStatus());
            statement.setBoolean(4, document.getDeliveryType());
            statement.setString(5, document.getComment());
            statement.setInt(6, document.getStudentId());
            statement.setString(7, document.getReceiverName());
            statement.setString(8, document.getReceiverMail());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(Document document) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, document.getDocumentPath());
            statement.setInt(2, document.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<Document> findByNotDocId(Integer id, boolean isDean) throws DaoException {
        List<Document> documents = new ArrayList<>();
        try {
            PreparedStatement statement;
            if(isDean) {
                 statement = connection.prepareStatement(SELECT_BY_DEAN);
            }else {
                statement = connection.prepareStatement(SELECT_BY_USER);
            }
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer docId = rs.getInt("id");
                String orderDate = String.valueOf(rs.getDate("order_date"));
                String type = rs.getString("dt.type");
                boolean status = rs.getBoolean("status");
                boolean deliveryType = rs.getBoolean("delivery_type");
                String comment = rs.getString("comment");
                Integer studentId = rs.getInt("student_id");
                String receiverName = rs.getString("receiver_name");
                String receiverMail = rs.getString("receiver_mail");
                Document document = new Document(docId, orderDate, type, status, deliveryType, receiverName, receiverMail, comment, studentId);
                documents.add(document);
            }
            return documents;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<Document> getTypes() throws DaoException {
        List<Document> documents = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_TYPES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Document document = new Document();
                document.setTypeId(resultSet.getInt("id"));
                document.setDocumentType(resultSet.getString("type"));
                documents.add(document);
            }
        } catch (SQLException throwables) {
           throw  new DaoException(throwables);
        }
        return documents;
    }

    @Override
    public boolean deleteUserReferences(Integer id) throws DaoException {
        PreparedStatement statement;
        try{
            statement = connection.prepareStatement(UPDATE_STUD);
            statement.setInt(1, id);
            debugLog.debug("deleting references" + statement);
            int rows = statement.executeUpdate();
            debugLog.debug(rows);
            return rows > 0;
        } catch (SQLException throwables) {
            throw  new DaoException(throwables);
        }
    }
}
