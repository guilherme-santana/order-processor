package com.order_service.infrastructure.config.kafka;

import com.payments.avro.OrderPaymentProcessed;
import com.shipping.avro.ShippingProcessed;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.properties.schema.registry.url}")
    private String schemaRegistryUrl;

    private Map<String, Object> commonConsumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "order-service-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        return props;
    }

    @Bean
    public ConsumerFactory<String, OrderPaymentProcessed> orderPaymentProcessedConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(commonConsumerProps());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderPaymentProcessed> orderPaymentProcessedKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderPaymentProcessed> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderPaymentProcessedConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, ShippingProcessed> shippingProcessedConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(commonConsumerProps());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShippingProcessed> shippingProcessedKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ShippingProcessed> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(shippingProcessedConsumerFactory());
        return factory;
    }
}
