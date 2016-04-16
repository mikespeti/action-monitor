package com.betvictor.action_monitor.services.triggers;

import com.betvictor.action_monitor.domain.Person;
import org.apache.commons.lang3.StringUtils;
import org.h2.tools.TriggerAdapter;

import javax.persistence.Entity;

/**
 * Created by mikes on 2016. 04. 16..
 */
public abstract class AbstractEntityTriggerAdapter extends TriggerAdapter {

    private final String tableName;

    public abstract Class<?> getEntityClass();
    public String getTableName(){ return tableName;}

    /**
     * Calculate the tableName automatically by the JPA-managed class, given by the implementor of the abstract method
     * Results: <br/>
     * - If @Entity interface is given, without "name" parameter => simple name of the given class <br/>
     * - If @Entity interface is given, with "name" parameter => value of the name parameter <br/>
     * - If @Entity interface is not present,  => <unknown> <br/>
     */
    public AbstractEntityTriggerAdapter() {
        Entity entityAnnotation = getEntityClass().getAnnotation(Entity.class);
        if (entityAnnotation != null) {
            if (StringUtils.isNotBlank(entityAnnotation.name())) {
                this.tableName = entityAnnotation.name();
            } else {
                this.tableName = Person.class.getSimpleName();

            }
        } else {
            this.tableName = "<unknown>";

        }
    }
}
