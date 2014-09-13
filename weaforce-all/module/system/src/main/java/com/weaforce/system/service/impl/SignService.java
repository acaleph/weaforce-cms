package com.weaforce.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.weaforce.system.service.ISignService;

@Service("signService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SignService implements ISignService {

}
