package com.qrmenu.repository;


import com.qrmenu.entity.ChangeRequest;
import com.qrmenu.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChangeRequestRepository extends JpaRepository<ChangeRequest, UUID> {
    List<ChangeRequest> findByStatus(RequestStatus status);
    Long countByStatus(RequestStatus status);
}