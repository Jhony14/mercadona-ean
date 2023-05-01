package com.mercadona.ean.service;

import com.mercadona.ean.model.Product;
import com.mercadona.ean.model.Supplier;
import com.mercadona.ean.model.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EanService {

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private DestinationService destinationService;

    public Product getProductDetailsByEan(String ean) {
        validateEan(ean);
        Long productId = parseProductIdFromEan(ean);
        Long supplierId = parseSupplierIdFromEan(ean);
        int destinationDigit = parseDestinationDigitFromEan(ean);

        Optional<Product> productOpt = productService.getProductById(productId);
        Optional<Supplier> supplierOpt = supplierService.getSupplierById(supplierId);
        Optional<Destination> destinationOpt = destinationService.getDestinationById((long) destinationDigit);

        if (productOpt.isEmpty() || supplierOpt.isEmpty() || destinationOpt.isEmpty()) {
            throw new IllegalArgumentException("Product, supplier, or destination does not exist for given EAN.");
        }

        Product product = productOpt.get();
        product.setSupplier(supplierOpt.get());
        product.setDestination(destinationOpt.get());

        return product;
    }

    private void validateEan(String ean) {
        if (!ean.matches("^\\d{13}$")) {
            throw new IllegalArgumentException("Invalid EAN format. EAN must be a 13-digit number.");
        }
    }

    private Long parseProductIdFromEan(String ean) {
        // According to the specified EAN format, the product ID is the 7th to 11th digits of the EAN
        String productIdStr = ean.substring(7, 12);
        return Long.parseLong(productIdStr);
    }

    private Long parseSupplierIdFromEan(String ean) {
        // According to the specified EAN format, the supplier ID is the 1st to 7th digits of the EAN
        String supplierIdStr = ean.substring(0, 7);
        return Long.parseLong(supplierIdStr);
    }

    private int parseDestinationDigitFromEan(String ean) {
        // According to the specified EAN format, the destination digit is the last digit of the EAN
        return Character.getNumericValue(ean.charAt(12));
    }
}
