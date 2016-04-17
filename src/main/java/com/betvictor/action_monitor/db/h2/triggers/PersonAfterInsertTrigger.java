package com.betvictor.action_monitor.db.h2.triggers;

import com.betvictor.action_monitor.utils.SpringContext;
import com.betvictor.action_monitor.domain.Person;
import com.betvictor.action_monitor.services.jms.TableChangeMessageProducer;
import com.google.common.collect.ImmutableMap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonAfterInsertTrigger extends AbstractEntityTriggerAdapter {

    @Override
    public Class<?> getEntityClass() {
        return Person.class;
    }

    @Override
    public void fire(Connection conn, ResultSet oldRow, ResultSet newRow) throws SQLException {
        TableChangeMessageProducer messageProducer = SpringContext.getApplicationContext().getBean(TableChangeMessageProducer.class);

        if (newRow == null) {
            throw new SQLException("newRow result set was NULL at INSERT action");
        }

        messageProducer.sendMessage(ImmutableMap.<String, Object>builder()
                .put("timestamp", System.nanoTime())
                .put("action", TableChangeMessageProducer.DB_ACTIONS.INSERT)
                .put("id", newRow.getString("ID"))
                .put("table", getTableName())
                .build()
        );
    }
}
