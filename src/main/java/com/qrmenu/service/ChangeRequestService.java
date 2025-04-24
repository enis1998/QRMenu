package com.qrmenu.service;


import com.qrmenu.entity.ChangeRequest;

import java.util.List;

public interface ChangeRequestService {
    ChangeRequest createChangeRequest(ChangeRequest changeRequest);
    List<ChangeRequest> findAllPending();
    ChangeRequest approveRequest(Long id);
    ChangeRequest rejectRequest(Long id);
    ChangeRequest findById(Long id);
}