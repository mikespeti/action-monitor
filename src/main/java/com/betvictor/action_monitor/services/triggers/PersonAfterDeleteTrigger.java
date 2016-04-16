package com.betvictor.action_monitor.services.triggers;

import com.betvictor.action_monitor.database.SpringContext;
import com.betvictor.action_monitor.services.jms.TableChangeMessageProducer;
import com.google.common.collect.ImmutableMap;
import org.h2.tools.TriggerAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonAfterDeleteTrigger extends TriggerAdapter {

    @Override
    public void fire(Connection conn, ResultSet oldRow, ResultSet newRow) throws SQLException {
        TableChangeMessageProducer messageProducer = SpringContext.getApplicationContext().getBean(TableChangeMessageProducer.class);
        messageProducer.sendMessage(ImmutableMap.<String, Object>builder()
                .put("timestamp", System.nanoTime())
                .put("action", TableChangeMessageProducer.DB_ACTIONS.DELETE)
                .put("id", oldRow.getString("ID"))
                .put("table", "T_PERSON")
                .build()
        );
    }

}
