package by.training.finaltask.bean.entities;

import by.training.finaltask.bean.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Document extends Entity {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    private String orderDate;
    private String documentType;
    private Boolean status;
    private Boolean deliveryType;
    private String documentPath;
    private String receiverName;
    private String receiverMail;
    private String comment;
    private Integer studentId;
    private Integer typeId;

    public Document() {
    }

    public Document(Integer id, String orderDate, String documentType, boolean status, boolean deliveryType,
                    String receiverName, String receiverMail, String comment, Integer studentId) {
        super(id);
        this.orderDate = orderDate;
        this.status = status;
        this.deliveryType = deliveryType;
        this.receiverName = receiverName;
        this.documentType = documentType;
        this.receiverMail = receiverMail;
        this.comment = comment;
        this.studentId = studentId;
    }

    public Document(Integer typeId, boolean deliveryType, String receiverName, String receiverMail,
                    String comment, Integer studentId) {
        this.typeId = typeId;
        this.deliveryType = deliveryType;
        status = false;
        this.receiverName = receiverName;
        this.receiverMail = receiverMail;
        this.comment = comment;
        this.studentId = studentId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(boolean deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverMail() {
        return receiverMail;
    }

    public void setReceiverMail(String receiverMail) {
        this.receiverMail = receiverMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return status == document.status && deliveryType == document.deliveryType && Objects.equals(orderDate, document.orderDate) && Objects.equals(documentType, document.documentType) && Objects.equals(documentPath, document.documentPath) && Objects.equals(receiverName, document.receiverName) && Objects.equals(receiverMail, document.receiverMail) && Objects.equals(comment, document.comment) && Objects.equals(studentId, document.studentId) && Objects.equals(typeId, document.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentType, status, deliveryType, documentPath, receiverName, receiverMail, comment, studentId, typeId);
    }

    @Override
    public String toString() {
        return "Document{" + super.getId() + " " +
                "orderDate='" + orderDate + '\'' +
                ", documentType='" + documentType + '\'' +
                ", status=" + status +
                ", deliveryType=" + deliveryType +
                ", documentPath='" + documentPath + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverMail='" + receiverMail + '\'' +
                ", comment='" + comment + '\'' +
                ", studentId=" + studentId +
                ", typeID=" + typeId +
                '}';
    }
}
