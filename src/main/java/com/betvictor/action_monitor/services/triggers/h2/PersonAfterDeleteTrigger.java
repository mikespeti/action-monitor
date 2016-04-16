package com.betvictor.action_monitor.services.triggers.h2;

import com.betvictor.action_monitor.database.SpringContext;
import com.betvictor.action_monitor.domain.Person;
import com.betvictor.action_monitor.services.jms.TableChangeMessageProducer;
import com.google.common.collect.ImmutableMap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonAfterDeleteTrigger extends AbstractEntityTriggerAdapter {

    @Override
    public Class<?> getEntityClass() {
        return Person.class;
    }

    @Override
    public void fire(Connection conn, ResultSet oldRow, ResultSet newRow) throws SQLException {
        TableChangeMessageProducer messageProducer = SpringContext.getApplicationContext().getBean(TableChangeMessageProducer.class);

        if (oldRow == null) {
            throw new SQLException("oldRow result set was NULL at DELETE action");
        }

        messageProducer.sendMessage(ImmutableMap.<String, Object>builder()
                .put("timestamp", System.nanoTime())
                .put("action", TableChangeMessageProducer.DB_ACTIONS.INSERT)
                .put("id", oldRow.getString("ID"))
                .put("table", getTableName())
                .build()
        );
    }
}
