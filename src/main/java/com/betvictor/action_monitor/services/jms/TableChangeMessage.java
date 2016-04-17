package com.betvictor.action_monitor.services.jms;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

public class TableChangeMessage implements Serializable {

    private String id;
    private Long timestamp;
    private String tableName;
    private TableChangeMessageProducer.DB_ACTIONS action;

    public TableChangeMessage() {
    }

    public TableChangeMessage(String id, Long timestamp, String tableName, TableChangeMessageProducer.DB_ACTIONS action) {
        this.id = id;
        this.timestamp = timestamp;
        this.tableName = tableName;
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public TableChangeMessageProducer.DB_ACTIONS getAction() {
        return action;
    }

    public void setAction(TableChangeMessageProducer.DB_ACTIONS action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableChangeMessage that = (TableChangeMessage) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(timestamp, that.timestamp) &&
                Objects.equal(tableName, that.tableName) &&
                action == that.action;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, timestamp, tableName, action);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", id)
                .add("timestamp", timestamp)
                .add("tableName", tableName)
                .add("action", action.toString())
                .toString();
    }
}
