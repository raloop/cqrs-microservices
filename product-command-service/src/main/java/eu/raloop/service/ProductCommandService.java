package eu.raloop.service;

import eu.raloop.dto.ProductEvent;
import eu.raloop.entity.Product;
import eu.raloop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Product createProduct(ProductEvent productEvent) {
        Product productDO = repository.save(productEvent.getProduct());
        ProductEvent event = new ProductEvent("CreateProduct", productDO);
        kafkaTemplate.send("product-event-topic", event);

        return productDO;
    }

    public Product updateProduct(Long id, ProductEvent productEvent) {
        Product existingProduct = repository.findById(id).get();
        Product newProduct = productEvent.getProduct();
        existingProduct.setName(newProduct.getName());
        existingProduct.setDescription(newProduct.getDescription());
        existingProduct.setPrice(newProduct.getPrice());

        Product productDO = repository.save(existingProduct);
        ProductEvent event = new ProductEvent("UpdateProduct", productDO);
        kafkaTemplate.send("product-event-topic", event);

        return productDO;
    }
}
