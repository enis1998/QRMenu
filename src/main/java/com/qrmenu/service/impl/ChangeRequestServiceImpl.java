package com.qrmenu.service.impl;

import com.qrmenu.entity.ChangeRequest;
import com.qrmenu.entity.Product;
import com.qrmenu.enums.RequestStatus;
import com.qrmenu.repository.ChangeRequestRepository;
import com.qrmenu.service.ChangeRequestService;
import com.qrmenu.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ChangeRequestServiceImpl implements ChangeRequestService {

    private final ChangeRequestRepository changeRequestRepository;
    private final ProductService productService;

    public ChangeRequestServiceImpl(ChangeRequestRepository changeRequestRepository,
                                    ProductService productService) {
        this.changeRequestRepository = changeRequestRepository;
        this.productService = productService;
    }

    @Override
    public ChangeRequest createChangeRequest(ChangeRequest changeRequest) {
        changeRequest.setStatus(RequestStatus.PENDING);
        return changeRequestRepository.save(changeRequest);
    }

    @Override
    public Long countByPending() {
        return changeRequestRepository.countByStatus(RequestStatus.PENDING);
    }

    @Override
    public List<ChangeRequest> findAllPending() {
        return changeRequestRepository.findByStatus(RequestStatus.PENDING);
    }

    @Transactional
    @Override
    public ChangeRequest approveRequest(UUID id) {
        ChangeRequest request = changeRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(RequestStatus.APPROVED);
        // Ürün güncellemesini gerçekleştir:
        Product product = request.getProduct();
        product.setName(request.getNewName());
        product.setDescription(request.getNewDescription());
        product.setPrice(request.getNewPrice());

        productService.save(product);
        return changeRequestRepository.save(request);
    }

    @Override
    public ChangeRequest rejectRequest(UUID id) {
        ChangeRequest request = changeRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(RequestStatus.REJECTED);
        return changeRequestRepository.save(request);
    }

    @Override
    public ChangeRequest findById(UUID id) {
        return changeRequestRepository.findById(id).orElse(null);
    }
}
