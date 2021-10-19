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
    private static final Logger daoLog = LogManager.getLogger(DeanDaoImpl.class.getName());

    private static final String SELECT = "SELECT document.id, order_date, type_id, status, delivery_type, comment, document.document, student_id, receiver_name, receiver_mail, type " +
            " FROM document INNER JOIN document_type dt on document.type_id = dt.id";
    private static final String SELECT_FOR_UPDATE = "SELECT id, document FROM document WHERE id = ?";
    private static final String CREATE = "INSERT INTO document(type_id, status, delivery_type, comment, student_id, receiver_name, receiver_mail, order_date)" +
            " VALUES (?,?,?,?,?,?,?, CURRENT_DATE)";
    private static final String DELETE = "DELETE FROM document WHERE id = ?";
    private static final String UPDATE = "UPDATE document SET document = ?, status = true WHERE id = ?";
    private static final String SELECT_BY_DEAN = "SELECT document.id, order_date, type, status, delivery_type,type_id, comment, document.document, student_id, receiver_name, receiver_mail" +
            " FROM  document INNER JOIN student s ON document.student_id = s.user_id JOIN document_type dt on document.type_id = dt.id" +
            "  WHERE dean_id = ? AND status = false ORDER BY order_date";
    private static final String SELECT_BY_USER = SELECT + " WHERE student_id = ?";
    private static final String SELECT_TYPES = "SELECT id, type FROM document_type";
    private static final String UPDATE_STUD = "UPDATE document SET student_id = NULL WHERE student_id = ?";

    @Override
    public List<Document> findAll() throws DaoException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            List<Document> documents = new ArrayList<>();
            resultSet = statement.executeQuery(SELECT);
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
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException | NullPointerException throwables) {
            }
        }
    }


    @Override
    public Document findById(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_FOR_UPDATE);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Document document = new Document();
                document.setId(resultSet.getInt("id"));
                document.setDocumentPath(resultSet.getString("document"));
                return document;
            }
            return null;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException | NullPointerException throwables) {
            }
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
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
            }
        }
    }

    @Override
    public boolean create(Document document) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE);
            statement.setInt(1, document.getTypeId());
            statement.setBoolean(2, document.getStatus());
            statement.setBoolean(3, document.getDeliveryType());
            statement.setString(4, document.getComment());
            statement.setInt(5, document.getStudentId());
            statement.setString(6, document.getReceiverName());
            statement.setString(7, document.getReceiverMail());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException throwables) {
            }
        }
    }

    @Override
    public boolean update(Document document) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, document.getDocumentPath());
            statement.setInt(2, document.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException throwables) {
            }
        }
    }

    @Override
    public List<Document> findByUserId(Integer id) throws DaoException {
        List<Document> documents = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_USER);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                documents.add(setDoc(rs, true));
            }
            return documents;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        } finally {
            try {
                statement.close();
                rs.close();
            } catch (SQLException throwables) {
            }
        }
    }

    @Override
    public List<Document> findByDeanId(Integer id) throws DaoException {
        List<Document> documents = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_DEAN);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                documents.add(setDoc(resultSet, true));
            }
            return documents;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException | NullPointerException throwables) {
            }
        }
    }

    @Override
    public List<Document> getTypes() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Document> documents = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_TYPES);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Document document = new Document();
                document.setTypeId(resultSet.getInt("id"));
                document.setDocumentType(resultSet.getString("type"));
                documents.add(document);
            }
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException | NullPointerException throwables) {
            }
        }
        return documents;
    }

    @Override
    public boolean deleteUserReferences(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_STUD);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException throwables) {
            }
        }
    }

    private Document setDoc(ResultSet resultSet, boolean isFull) throws SQLException {
        Document document = new Document();
        if (isFull) {
            document.setId(resultSet.getInt("document.id"));
        }
        document.setOrderDate(resultSet.getString("order_date"));
        document.setDocumentType(resultSet.getString("dt.type"));
        document.setStatus(resultSet.getBoolean("status"));
        document.setDeliveryType(resultSet.getBoolean("delivery_type"));
        document.setComment(resultSet.getString("comment"));
        document.setReceiverName(resultSet.getString("receiver_name"));
        document.setReceiverMail(resultSet.getString("receiver_mail"));
        document.setStudentId(resultSet.getInt("student_id"));
        document.setTypeId(resultSet.getInt("type_id"));
        document.setDocumentPath(resultSet.getString("document.document"));
        daoLog.info("filled doc " + document);
        return document;
    }
}
