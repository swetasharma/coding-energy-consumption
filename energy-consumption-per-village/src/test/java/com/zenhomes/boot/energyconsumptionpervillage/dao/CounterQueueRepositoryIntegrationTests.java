package com.zenhomes.boot.energyconsumptionpervillage.dao;

import com.zenhomes.boot.energyconsumptionpervillage.models.CounterQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureDataJdbc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CounterQueueRepositoryIntegrationTests {

    @Autowired
    private TestEntityManager entityManager;

    //We are injecting or autowiring CounterQueueDaoMySqlImpl component because thats the component we want to test
    @Autowired
    private CounterQueueDaoMySqlImpl counterQueueDaoMySql;

    @Test
    public void saveCounterQueue() {
        CounterQueue counterQueue = new CounterQueue(1, 1000, Byte.parseByte("0"));
        entityManager.persist(counterQueue);
        Assertions.assertEquals(counterQueue, entityManager.getId(1));
    }
}
