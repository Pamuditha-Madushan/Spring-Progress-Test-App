package com.progress.test.util;

import com.progress.test.entity.Customer;
import com.progress.test.entity.Order;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.security.core.parameters.P;

import java.io.Serializable;
import java.util.Random;

public class CustomIdGenerator implements IdentifierGenerator {


    private static final int ID_LENGTH = 10;

    public static String generateCustomerId() {
        return generateId("Cus");
    }

    public static String generateOrderId() {
        return generateId("Ord");
    }

    private static String generateId(String prefix) {
        String randomAlphaNumericId = getRandomAlphaNumericId(ID_LENGTH);
        return prefix + "-" + randomAlphaNumericId;
    }

    private static String getRandomAlphaNumericId(int length) {
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());
            char randomChar = allowedCharacters.charAt(randomIndex);
            stringBuffer.append(randomChar);
        }
        return stringBuffer.toString();
    }


    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        if (o instanceof Customer) {
            return generateCustomerId();
        } else if (o instanceof Order) {
            return generateOrderId();
        } else {
            throw new IllegalArgumentException("Unsupported entity type !!");
        }
    }

}
