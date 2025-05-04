package com.qrmenu.service;


import com.qrmenu.entity.ChangeRequest;

import java.util.List;
import java.util.UUID;

public interface ChangeRequestService {
    ChangeRequest createChangeRequest(ChangeRequest changeRequest);
    Long countByPending();
    List<ChangeRequest> findAllPending();
    ChangeRequest approveRequest(UUID id);
    ChangeRequest rejectRequest(UUID id);
    ChangeRequest findById(UUID id);
}