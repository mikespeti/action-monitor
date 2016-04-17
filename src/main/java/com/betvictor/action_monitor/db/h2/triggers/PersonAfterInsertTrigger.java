package com.betvictor.action_monitor.db.h2.triggers;

import com.betvictor.action_monitor.domain.Person;
import com.betvictor.action_monitor.services.jms.TableChangeMessage;
import com.betvictor.action_monitor.services.jms.TableChangeMessageProducer;
import com.betvictor.action_monitor.utils.SpringContext;

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

        messageProducer.sendMessage(new TableChangeMessage(newRow.getString("ID"), System.nanoTime(), getTableName(), TableChangeMessageProducer.DB_ACTIONS.INSERT));
    }
}
