package com.betvictor.action_monitor.db.h2.triggers;

import com.betvictor.action_monitor.services.jms.TableChangeMessage;
import com.betvictor.action_monitor.utils.SpringContext;
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

        messageProducer.sendMessage(new TableChangeMessage(oldRow.getString("ID"), System.nanoTime(), getTableName(), TableChangeMessageProducer.DB_ACTIONS.DELETE));
    }
}
